package com.ruoyi.web.controller.system.BaoBiao;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.BaoBiao.ChatMessage;
import com.ruoyi.system.service.BaoBiao.IChatMessageService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Api(tags = "聊天")
@Anonymous
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dev-api/system/chat")
public class ChatController extends BaseController {

    @Resource
    private IChatMessageService chatMessageService;

    @Anonymous
    @CrossOrigin(origins = "*")
    @Operation(summary = "发送文本")
    @PostMapping("/sendText")
    public AjaxResult sendText(@RequestParam String senderId,
                               @RequestParam String receiverId,
                               @RequestParam String content) {
        int n = chatMessageService.sendText(senderId, receiverId, content);
        return AjaxResult.toAjax(n);
    }

    @Anonymous
    @CrossOrigin(origins = "*")
    @Operation(summary = "上传文件/图片，并自动生成消息（返回消息对象）")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AjaxResult upload(@RequestParam("file") MultipartFile file,
                             @RequestParam String senderId,
                             @RequestParam String receiverId) {
        if (file == null || file.isEmpty()) {
            return AjaxResult.error("文件为空");
        }
        try {
            // 1) 物理保存到 ruoyi.profile/chat/yyyy/MM/dd
            String baseDir = RuoYiConfig.getProfile(); // 如 E:/ruoyi/uploadPath
            String datePath = new java.text.SimpleDateFormat("yyyy/MM/dd").format(new Date());
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String relative = "chat/" + datePath + "/" + uuid + (StringUtils.hasText(ext) ? ("." + ext) : "");
            Path dest = Paths.get(baseDir, relative);
            Files.createDirectories(dest.getParent());
            file.transferTo(dest.toFile());

            // 2) 计算 URL（/profile/** 映射到 baseDir）
            String url = "/profile/" + relative.replace(File.separatorChar, '/');

            // 3) 组装消息
            String mime = Optional.ofNullable(file.getContentType()).orElse("application/octet-stream");
            ChatMessage m = new ChatMessage();
            m.setSenderId(senderId);
            m.setReceiverId(receiverId);
            m.setFileUrl(url);
            m.setFileName(file.getOriginalFilename());
            m.setFileSize(file.getSize());
            m.setMimeType(mime);

            // 判断类型
            String type = "FILE";
            if (mime.startsWith("image/")) type = "IMAGE";
            else if (mime.startsWith("audio/")) type = "AUDIO";
            else if (mime.startsWith("video/")) type = "VIDEO";
            m.setMsgType(type);

            // 如果是图片，读宽高
            if ("IMAGE".equals(type)) {
                try {
                    BufferedImage bi = ImageIO.read(dest.toFile());
                    if (bi != null) {
                        m.setWidth(bi.getWidth());
                        m.setHeight(bi.getHeight());
                    }
                } catch (Exception ignore) {}
            }

            chatMessageService.sendFile(m);
            return AjaxResult.success(m);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("上传失败：" + e.getMessage());
        }
    }

    @Anonymous
    @CrossOrigin(origins = "*")
    @Operation(summary = "分页拉取历史（按 create_time DESC，从最新开始），roomId 或 (senderId,receiverId)")
    @GetMapping("/history")
    public TableDataInfo history(@RequestParam(required = false) String roomId,
                                 @RequestParam(required = false) String senderId,
                                 @RequestParam(required = false) String receiverId) {
        if (!StringUtils.hasText(roomId) && StringUtils.hasText(senderId) && StringUtils.hasText(receiverId)) {
            roomId = IChatMessageService.computeRoomId(senderId, receiverId);
        }
        ChatMessage q = new ChatMessage();
        q.setRoomId(roomId);
        // 开分页：pageNum/pageSize/orderByColumn/isAsc 由请求参数自动注入
        startPage();
        List<ChatMessage> list = chatMessageService.list(q);
        return getDataTable(list);
    }

    @Anonymous
    @CrossOrigin(origins = "*")
    @Operation(summary = "批量标记已读（untilId 可选：标记到某条为止）")
    @PostMapping("/readAck")
    public AjaxResult readAck(@RequestParam String roomId,
                              @RequestParam String receiverId,
                              @RequestParam(required = false) Long untilId) {
        int n = chatMessageService.markRead(roomId, receiverId, untilId);
        return AjaxResult.toAjax(n);
    }

    @Anonymous
    @CrossOrigin(origins = "*")
    @Operation(summary = "单条查询（调试）")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable Long id) {
        return AjaxResult.success(chatMessageService.get(id));
    }
}

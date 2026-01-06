package com.ruoyi.web.controller.system.BaoBiao;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 简易 REST 信令（内存版），避免引入 STOMP。上线后可替换为 WebSocket/消息队列。
 */
@Api(tags = "视频接口")
@RestController
@Anonymous
@CrossOrigin(origins = "*")
@RequestMapping("/dev-api/system/rtc123")
public class SignalController {

    static class Msg {
        public long id;
        public String type;     // offer/answer/ice/hangup
        public String roomId;
        public String from;
        public String to;
        public String sdp;
        public String candidate;
        public String sdpMid;
        public Integer sdpMLineIndex;
        public long ts;
    }

    private final Map<String, Deque<Msg>> buckets = new ConcurrentHashMap<>();
    private final AtomicLong idGen = new AtomicLong(1000L);
    private static final int MAX_PER_ROOM = 500;

    @PostConstruct
    public void init() {}

    private Deque<Msg> bucket(String room) {
        return buckets.computeIfAbsent(room, k -> new ArrayDeque<>());
    }

    private AjaxResult ok(Object data) {
        return AjaxResult.success(data);
    }

    @GetMapping("/ping")
    @Operation(summary = "健康检查")
    public AjaxResult ping() {
        return ok("pong");
    }

    @PostMapping("/offer")
    @Operation(summary = "发送 Offer")
    public AjaxResult offer(@RequestBody Map<String, Object> body) {
        return push("offer", body);
    }

    @PostMapping("/answer")
    @Operation(summary = "发送 Answer")
    public AjaxResult answer(@RequestBody Map<String, Object> body) {
        return push("answer", body);
    }

    @PostMapping("/ice")
    @Operation(summary = "发送 ICE 候选")
    public AjaxResult ice(@RequestBody Map<String, Object> body) {
        return push("ice", body);
    }

    @PostMapping("/hangup")
    @Operation(summary = "挂断")
    public AjaxResult hangup(@RequestBody Map<String, Object> body) {
        return push("hangup", body);
    }

    @GetMapping("/poll")
    @Operation(summary = "拉取信令（增量）")
    public Object poll(@RequestParam String roomId,
                       @RequestParam String userId,
                       @RequestParam(required = false, defaultValue = "0") long lastId) {
        final Deque<Msg> q = bucket(roomId);
        if (q.isEmpty()) return Collections.emptyList();
        List<Msg> out = new ArrayList<>();
        for (Msg m : q) {
            if (m.id > lastId && (m.to == null || userId.equals(m.to))) {
                out.add(m);
                if (out.size() >= 50) break;
            }
        }
        return out;
    }

    private AjaxResult push(String type, Map<String, Object> body) {
        String roomId = String.valueOf(body.get("roomId"));
        String from = String.valueOf(body.get("from"));
        String to = body.get("to") == null ? null : String.valueOf(body.get("to"));
        String sdp = body.get("sdp") == null ? null : String.valueOf(body.get("sdp"));
        String candidate = body.get("candidate") == null ? null : String.valueOf(body.get("candidate"));
        String sdpMid = body.get("sdpMid") == null ? null : String.valueOf(body.get("sdpMid"));
        Integer sdpMLineIndex = null;
        if (body.get("sdpMLineIndex") != null) {
            try { sdpMLineIndex = Integer.parseInt(String.valueOf(body.get("sdpMLineIndex"))); } catch (Exception ignore) {}
        }

        Msg m = new Msg();
        m.id = idGen.incrementAndGet();
        m.type = type;
        m.roomId = roomId;
        m.from = from;
        m.to = to;
        m.sdp = sdp;
        m.candidate = candidate;
        m.sdpMid = sdpMid;
        m.sdpMLineIndex = sdpMLineIndex;
        m.ts = System.currentTimeMillis();

        Deque<Msg> q = bucket(roomId);
        q.addLast(m);
        while (q.size() > MAX_PER_ROOM) q.pollFirst();

        Map<String, Object> resp = new HashMap<>();
        resp.put("ok", true);
        resp.put("id", m.id);
        resp.put("ts", m.ts);
        return ok(resp);
    }
}

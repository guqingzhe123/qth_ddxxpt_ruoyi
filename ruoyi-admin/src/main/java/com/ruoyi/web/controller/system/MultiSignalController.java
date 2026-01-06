package com.ruoyi.web.controller.system;


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
 * 多人视频信令控制器（内存版，不持久化）
 */
@Api(tags = "多人视频接口")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dev-api/system/rtc/multi")
@Anonymous
public class MultiSignalController {

    // 房间信息: roomId -> 房间内用户列表
    private final Map<String, Set<String>> rooms = new ConcurrentHashMap<>();
    // 信号队列: roomId -> 信号列表
    private final Map<String, Deque<Signal>> signals = new ConcurrentHashMap<>();
    private final AtomicLong signalIdGen = new AtomicLong(1);
    private static final int MAX_SIGNALS_PER_ROOM = 1000;

    static class Signal {
        public long id;
        public String roomId;
        public String from;       // 发送者ID
        public String fromName;   // 发送者名称
        public String to;         // 接收者ID（null表示广播）
        public String type;       // offer/answer/ice/leave
        public String sdp;
        public Map<String, Object> candidate;
        public long timestamp;
    }

    @PostConstruct
    public void init() {
        // 定时清理长时间无活动的房间（可选）
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long now = System.currentTimeMillis();
                signals.forEach((roomId, signalDeque) -> {
                    if (signalDeque.isEmpty()) {
                        rooms.remove(roomId);
                        signals.remove(roomId);
                    }
                });
            }
        }, 3600000, 3600000); // 每小时清理一次
    }

    @PostMapping("/join")
    @Operation(summary = "加入房间")
    public AjaxResult join(@RequestBody Map<String, String> params) {
        String roomId = params.get("roomId");
        String username = params.get("username");
        if (roomId == null || username == null) {
            return AjaxResult.error("房间ID和用户名不能为空");
        }

        // 加入房间
        rooms.computeIfAbsent(roomId, k -> ConcurrentHashMap.newKeySet()).add(username);

        // 通知房间内其他用户有新用户加入
        broadcastSignal(roomId, username, null, "join", null, null);

        // 返回当前房间内的其他用户
        List<Map<String, String>> peers = new ArrayList<>();
        rooms.get(roomId).forEach(peer -> {
            if (!peer.equals(username)) {
                peers.add(Collections.singletonMap("username", peer));
            }
        });

        return AjaxResult.success(Collections.singletonMap("peers", peers));
    }

    @PostMapping("/leave")
    @Operation(summary = "离开房间")
    public AjaxResult leave(@RequestBody Map<String, String> params) {
        String roomId = params.get("roomId");
        String username = params.get("username");
        if (roomId == null || username == null) {
            return AjaxResult.error("参数不能为空");
        }

        // 从房间移除
        Set<String> users = rooms.get(roomId);
        if (users != null) {
            users.remove(username);
            if (users.isEmpty()) {
                rooms.remove(roomId);
                signals.remove(roomId);
            } else {
                // 广播离开信号
                broadcastSignal(roomId, username, null, "leave", null, null);
            }
        }
        return AjaxResult.success();
    }

    @PostMapping("/signal")
    @Operation(summary = "发送信号")
    public AjaxResult sendSignal(@RequestBody Map<String, Object> params) {
        String roomId = (String) params.get("roomId");
        String from = (String) params.get("from");
        String to = (String) params.get("to");
        String type = (String) params.get("type");
        String sdp = (String) params.get("sdp");
        Map<String, Object> candidate = (Map<String, Object>) params.get("candidate");

        if (roomId == null || from == null || type == null) {
            return AjaxResult.error("参数不能为空");
        }

        // 验证发送者是否在房间内
        Set<String> users = rooms.get(roomId);
        if (users == null || !users.contains(from)) {
            return AjaxResult.error("请先加入房间");
        }

        // 保存信号
        Signal signal = new Signal();
        signal.id = signalIdGen.incrementAndGet();
        signal.roomId = roomId;
        signal.from = from;
        signal.fromName = from;
        signal.to = to;
        signal.type = type;
        signal.sdp = sdp;
        signal.candidate = candidate;
        signal.timestamp = System.currentTimeMillis();

        Deque<Signal> signalDeque = signals.computeIfAbsent(roomId, k -> new ArrayDeque<>());
        signalDeque.add(signal);
        // 限制队列大小
        while (signalDeque.size() > MAX_SIGNALS_PER_ROOM) {
            signalDeque.pollFirst();
        }

        return AjaxResult.success();
    }

    @GetMapping("/poll")
    @Operation(summary = "轮询信号")
    public AjaxResult pollSignals(
            @RequestParam String roomId,
            @RequestParam String username,
            @RequestParam(defaultValue = "0") long lastId) {

        Deque<Signal> signalDeque = signals.get(roomId);
        if (signalDeque == null) {
            return AjaxResult.success(Collections.emptyList());
        }

        List<Signal> result = new ArrayList<>();
        for (Signal signal : signalDeque) {
            // 只返回新的、目标是自己或广播的信号
            if (signal.id > lastId &&
                    (signal.to == null || signal.to.equals(username)) &&
                    !signal.from.equals(username)) { // 过滤自己发送的信号
                result.add(signal);
            }
        }
        return AjaxResult.success(result);
    }

    // 广播信号给房间内所有用户
    private void broadcastSignal(String roomId, String from, String to, String type, String sdp, Map<String, Object> candidate) {
        Signal signal = new Signal();
        signal.id = signalIdGen.incrementAndGet();
        signal.roomId = roomId;
        signal.from = from;
        signal.fromName = from;
        signal.to = to;
        signal.type = type;
        signal.sdp = sdp;
        signal.candidate = candidate;
        signal.timestamp = System.currentTimeMillis();

        Deque<Signal> signalDeque = signals.computeIfAbsent(roomId, k -> new ArrayDeque<>());
        signalDeque.add(signal);
        while (signalDeque.size() > MAX_SIGNALS_PER_ROOM) {
            signalDeque.pollFirst();
        }
    }
}
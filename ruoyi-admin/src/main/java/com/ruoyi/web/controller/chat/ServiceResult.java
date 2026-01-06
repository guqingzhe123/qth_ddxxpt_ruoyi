package com.ruoyi.web.controller.chat;

// 建议放在 com.ruoyi.common.core.domain 包下，与其他通用类统一管理

import lombok.Data;

import java.io.Serializable;

/**
 * 服务层通用返回结果封装
 * 用于统一接口返回格式：{code: 200, msg: "成功", data: {...}}
 */
@Data
public class ServiceResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    // 状态码：200-成功，500-失败，其他自定义
    private int code;
    // 消息提示
    private String msg;
    // 业务数据
    private T data;

    // 私有构造，避免直接实例化
    private ServiceResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功（无数据）
     */
    public static <T> ServiceResult<T> success() {
        return new ServiceResult<>(200, "操作成功", null);
    }

    /**
     * 成功（带数据）
     */
    public static <T> ServiceResult<T> success(T data) {
        return new ServiceResult<>(200, "操作成功", data);
    }

    /**
     * 失败（自定义消息）
     */
    public static <T> ServiceResult<T> error(String msg) {
        return new ServiceResult<>(500, msg, null);
    }

    /**
     * 失败（自定义状态码和消息）
     */
    public static <T> ServiceResult<T> error(int code, String msg) {
        return new ServiceResult<>(code, msg, null);
    }
}

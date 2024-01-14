package com.chenzx.project.common.data;

import lombok.Data;

import java.io.Serializable;

/**
 * 接口通用响应包装对象
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-14 15:00
 **/
@Data
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求成功时为 true
     */
    private Boolean success;

    /**
     * 失败时 状态码
     */
    private String errorCode;

    /**
     * 失败时 错误信息
     */
    private String errorMessage;

    /**
     * 响应数据
     */
    private T data;

    public static <T> R<T> success(T data) {
        return new R<>(true, null, null, data);
    }

    public static R<String> success() {
        return new R<>(true, null, null, "操作成功");
    }

    public static <T> R<T> error(String errorCode, String errorMessage) {
        return new R<>(false, errorCode, errorMessage, null);
    }

    public static <T> R<T> error(String errorCode, String errorMessage, T data) {
        return new R<>(false, errorCode, errorMessage, data);
    }

    private R(Boolean success, String errorCode, String errorMessage, T data) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }
}

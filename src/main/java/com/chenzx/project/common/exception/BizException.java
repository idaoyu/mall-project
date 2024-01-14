package com.chenzx.project.common.exception;

/**
 * 业务逻辑异常
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-14 15:53
 **/
public class BizException extends RuntimeException {

    public BizException(String message) {
        super(message);
    }
}

package com.chenzx.project.module.mall.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 属性类型枚举
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-20 16:36
 **/
@Getter
@AllArgsConstructor
public enum PropertyTypeConstant {

    /**
     * 主要属性
     * 用户端会在该属性的选项中展示sku图片
     */
    PRIMARY_PROPERTY(1),
    /**
     * 次要属性 例如 尺码、大小等
     */
    SECONDARY_PROPERTY(2),
    ;

    private final Integer type;

}

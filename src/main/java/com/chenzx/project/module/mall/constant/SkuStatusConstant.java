package com.chenzx.project.module.mall.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * sku状态枚举
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-20 16:26
 **/
@Getter
@AllArgsConstructor
public enum SkuStatusConstant {

    /**
     * 上架
     */
    SHELVE(1),
    /**
     * 下架
     */
    OFF_SHELVE(2),
    ;

    private final int value;

}

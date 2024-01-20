package com.chenzx.project.module.mall.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * spu 状态 常量
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-15 21:02
 **/
@Getter
@AllArgsConstructor
public enum SpuStatusConstant {

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

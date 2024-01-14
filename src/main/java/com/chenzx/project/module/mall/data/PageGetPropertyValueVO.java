package com.chenzx.project.module.mall.data;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 分页查询属性 vo
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-14 20:39
 **/
@Data
@AllArgsConstructor
public class PageGetPropertyValueVO {

    /**
     * 属性值 id
     */
    private String id;

    /**
     * 属性值
     */
    private String value;

}

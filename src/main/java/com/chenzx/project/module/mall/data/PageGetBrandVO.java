package com.chenzx.project.module.mall.data;

import lombok.Builder;
import lombok.Data;

/**
 * 分页查询品牌 vo
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-15 20:08
 **/
@Data
@Builder
public class PageGetBrandVO {

    /**
     * 品牌id
     */
    private String id;

    /**
     * 品牌名字
     */
    private String name;

    /**
     * 品牌logo
     */
    private String logoUrl;

}

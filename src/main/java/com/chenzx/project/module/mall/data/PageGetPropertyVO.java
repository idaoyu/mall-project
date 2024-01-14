package com.chenzx.project.module.mall.data;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 分页查询属性 vo
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-14 20:36
 **/
@Data
@Builder
public class PageGetPropertyVO {

    private String id;

    /**
     * 属性名字
     */
    private String name;

    /**
     * 属性值 list
     */
    private List<PageGetPropertyValueVO> valueList;

}

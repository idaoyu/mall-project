package com.chenzx.project.module.mall.data;

import com.chenzx.project.module.mall.entity.MallCategory;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 类目管理分页查询 vo
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-14 15:08
 **/
@Data
@Builder
public class CategoryManagePageGetVO {

    /**
     * 类目id
     */
    private String id;

    /**
     * 父级主键
     */
    private String parentId;

    /**
     * 类目名字
     */
    private String name;

    /**
     * 排序字段
     */
    private Integer sortNum;

    private List<CategoryManagePageGetVO> children;

    public static CategoryManagePageGetVO mallCategory2CategoryManagePageGetVO(MallCategory mallCategory) {
        CategoryManagePageGetVO.CategoryManagePageGetVOBuilder builder = CategoryManagePageGetVO.builder();
        builder.id(mallCategory.getId());
        builder.parentId(mallCategory.getParentId());
        builder.name(mallCategory.getName());
        builder.sortNum(mallCategory.getSortNum());
        return builder.build();
    }
}

package com.chenzx.project.module.mall.data;

import com.chenzx.project.utils.ValidatedGroup;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 操作 商品类目 params
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-14 15:12
 **/
@Data
public class OperatingCategoryParams {

    /**
     * 类目id
     */
    @NotEmpty(message = "类目id不能为空", groups = ValidatedGroup.UpdateGroup.class)
    private String id;

    /**
     * 父级主键 增加二级类目时不能为空
     */
    @NotEmpty(message = "一级菜单id不能为空", groups = ValidatedGroup.CreateGroup.class)
    private String parentId;

    /**
     * 类目名字
     */
    @NotEmpty(message = "类目名字不能为空", groups = {ValidatedGroup.CreateGroup.class, ValidatedGroup.UpdateGroup.class})
    private String name;

    /**
     * 排序字段
     */
    @NotNull(message = "排序字段不能为空", groups = {ValidatedGroup.CreateGroup.class, ValidatedGroup.UpdateGroup.class})
    private Integer sortNum;

}

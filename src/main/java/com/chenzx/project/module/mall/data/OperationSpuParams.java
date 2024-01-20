package com.chenzx.project.module.mall.data;

import com.chenzx.project.utils.ValidatedGroup;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 新增/删除 spu params
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-15 20:30
 **/
@Data
public class OperationSpuParams {

    /**
     * spu id
     */
    @NotEmpty(message = "商品id不能为空", groups = ValidatedGroup.UpdateGroup.class)
    private String id;

    /**
     * 品牌id
     */
    @NotEmpty(message = "品牌id不能为空", groups = {ValidatedGroup.UpdateGroup.class, ValidatedGroup.CreateGroup.class})
    private String brandId;

    /**
     * 类目id
     */
    @NotEmpty(message = "类目id不能为空", groups = {ValidatedGroup.UpdateGroup.class, ValidatedGroup.CreateGroup.class})
    private String categoryId;

    /**
     * 商品名字
     */
    @NotEmpty(message = "商品名字不能为空", groups = {ValidatedGroup.UpdateGroup.class, ValidatedGroup.CreateGroup.class})
    private String name;

    /**
     * 描述
     */
    @NotEmpty(message = "商品描述不能为空", groups = {ValidatedGroup.UpdateGroup.class, ValidatedGroup.CreateGroup.class})
    private String description;

    /**
     * 排序字段
     */
    @NotNull(message = "排序字段不能为空", groups = {ValidatedGroup.UpdateGroup.class, ValidatedGroup.CreateGroup.class})
    private Integer sortNum;

    /**
     * 是否为多规格商品
     */
    @NotNull(message = "是否为多规格商品不能为空", groups = {ValidatedGroup.UpdateGroup.class, ValidatedGroup.CreateGroup.class})
    private Boolean multiSpecification;

}

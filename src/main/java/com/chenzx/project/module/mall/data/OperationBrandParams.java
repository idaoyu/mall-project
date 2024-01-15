package com.chenzx.project.module.mall.data;

import com.chenzx.project.utils.ValidatedGroup;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 创建/修改 品牌 params
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-15 19:57
 **/
@Data
public class OperationBrandParams {

    /**
     * 品牌id
     */
    @NotEmpty(message = "品牌id不能为空", groups = ValidatedGroup.UpdateGroup.class)
    private String id;

    /**
     * 品牌名字
     */
    @NotEmpty(message = "品牌名字不能为空", groups = {ValidatedGroup.CreateGroup.class, ValidatedGroup.UpdateGroup.class})
    private String name;

    /**
     * 品牌logo
     */
    @NotEmpty(message = "品牌logo不能为空", groups = {ValidatedGroup.CreateGroup.class, ValidatedGroup.UpdateGroup.class})
    private String logoUrl;

}

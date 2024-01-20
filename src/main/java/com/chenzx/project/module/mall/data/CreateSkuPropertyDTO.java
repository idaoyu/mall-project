package com.chenzx.project.module.mall.data;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 用来表示一个 sku 中的属性
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-20 12:22
 **/
@Data
public class CreateSkuPropertyDTO {

    /**
     * 属性id
     */
    @NotEmpty(message = "sku属性id不能为空")
    private String propertyId;

    /**
     * 属性值id
     */
    @NotEmpty(message = "sku属性值id不能为空")
    private String propertyValueId;

}

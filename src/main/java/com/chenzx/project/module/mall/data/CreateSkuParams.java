package com.chenzx.project.module.mall.data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * 创建商品 sku params
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-15 21:30
 **/
@Data
public class CreateSkuParams {

    @NotEmpty(message = "商品id不能为空")
    private String spuId;

    @Valid
    @NotEmpty(message = "商品sku不能为空")
    private List<CreateSkuInfoDTO> skuPropertyList;

}

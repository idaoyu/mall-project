package com.chenzx.project.module.mall.data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 创建 sku 库存 dto
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-15 21:32
 **/
@Data
public class CreateSkuInfoDTO {

    /**
     * 该 sku 包含的属性和属性值
     */
    @Valid
    @NotEmpty(message = "sku包含的属性不能为空")
    private List<CreateSkuPropertyDTO> propertyList;

    /**
     * 库存数量
     */
    @NotNull(message = "sku库存不能为空")
    private Long stock;

    /**
     * sku 价格
     */
    @NotNull(message = "sku价格不能为空")
    private BigDecimal price;

}

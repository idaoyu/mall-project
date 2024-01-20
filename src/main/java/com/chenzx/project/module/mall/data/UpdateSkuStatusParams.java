package com.chenzx.project.module.mall.data;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 修改sku状态 params
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-20 16:53
 **/
@Data
public class UpdateSkuStatusParams {

    /**
     * sku id
     */
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * sku状态
     */
    @NotNull(message = "状态不能为空")
    private Integer status;

}

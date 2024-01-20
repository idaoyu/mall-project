package com.chenzx.project.module.mall.data;

import com.chenzx.project.module.mall.entity.MallProperty;
import com.chenzx.project.module.mall.entity.MallPropertyValue;
import lombok.Builder;
import lombok.Data;

/**
 * 属性、属性值 dto
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-20 15:45
 **/
@Data
@Builder
public class PropertyDTO {

    /**
     * 属性
     */
    private MallProperty property;

    /**
     * 属性值
     */
    private MallPropertyValue propertyValue;

}

package com.chenzx.project.module.mall.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 商城属性值表
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024/1/14 20:01
 **/
@Data
@Builder
@TableName(value = "mall_property_value")
public class MallPropertyValue implements Serializable {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 属性id
     */
    @TableField(value = "property_id")
    private String propertyId;

    /**
     * 属性值名称
     */
    @TableField(value = "`value`")
    private String value;

    /**
     * 逻辑删除标记
     */
    @TableLogic
    @TableField(value = "del_flag")
    private Boolean delFlag;

    private static final long serialVersionUID = 1L;
}
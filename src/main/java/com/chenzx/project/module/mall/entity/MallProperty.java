package com.chenzx.project.module.mall.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 商城属性表
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024/1/14 20:00
 **/
@Data
@Builder
@TableName(value = "mall_property")
public class MallProperty implements Serializable {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 属性名字
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 属性类型
     */
    @TableField(value = "`type`")
    private Integer type;

    /**
     * 逻辑删除标记
     */
    @TableLogic
    @TableField(value = "del_flag")
    private Boolean delFlag;

    private static final long serialVersionUID = 1L;
}
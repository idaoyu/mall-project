package com.chenzx.project.module.mall.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商城品牌表
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024/1/15 19:53
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mall_brand")
public class MallBrand implements Serializable {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 品牌名字
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 品牌logo
     */
    @TableField(value = "logo_url")
    private String logoUrl;

    /**
     * 逻辑删除标记
     */
    @TableLogic
    @TableField(value = "del_flag")
    private Boolean delFlag;

    private static final long serialVersionUID = 1L;
}
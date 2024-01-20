package com.chenzx.project.module.mall.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 商城 spu 表
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024/1/15 20:26
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mall_spu")
public class MallSpu implements Serializable {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 品牌id
     */
    @TableField(value = "brand_id")
    private String brandId;

    /**
     * 类目id
     */
    @TableField(value = "category_id")
    private String categoryId;

    /**
     * 商品名字
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 商品图片（冗余字段）
     */
    @TableField(value = "spu_image_url")
    private String spuImageUrl;

    /**
     * spu 销量
     */
    @TableField(value = "spu_sales_volume")
    private Long spuSalesVolume;

    /**
     * spu状态(1:上架 2:下架)
     */
    @TableField(value = "spu_status")
    private Integer spuStatus;

    /**
     * 多规格 spu
     */
    @TableField(value = "multi_specification")
    private Boolean multiSpecification;

    /**
     * 排序字段
     */
    @TableField(value = "sort_num")
    private Integer sortNum;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 逻辑删除标记
     */
    @TableLogic
    @TableField(value = "del_flag")
    private Boolean delFlag;

    private static final long serialVersionUID = 1L;
}
package com.chenzx.project.module.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商城sku表
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024/1/15 21:27
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mall_sku")
public class MallSku implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * spu id
     */
    @TableField(value = "spu_id")
    private String spuId;

    /**
     * sku信息
     * 用来指明该sku对应的各个规格
     * 例如 颜色：红色 尺码：XL 款式：男
     */
    @TableField(value = "sku_info_str")
    private String skuInfoStr;

    /**
     * sku信息 zh
     */
    @TableField(value = "sku_info_zh_str")
    private String skuInfoZhStr;

    /**
     * 价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 库存
     */
    @TableField(value = "stock")
    private Long stock;

    /**
     * sku状态
     */
    @TableField(value = "sku_status")
    private Integer skuStatus;

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
    @TableField(value = "del_flag")
    private Boolean delFlag;

    private static final long serialVersionUID = 1L;
}
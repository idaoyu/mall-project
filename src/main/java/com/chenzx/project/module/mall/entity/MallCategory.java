package com.chenzx.project.module.mall.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 商城类目表
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024/1/14 14:55
 **/
@Data
@TableName(value = "mall_category")
public class MallCategory implements Serializable {

    /**
     * 类目id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 父级主键
     */
    @TableField(value = "parent_id")
    private String parentId;

    /**
     * 类目名字
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 逻辑删除标记
     */
    @TableLogic
    @TableField(value = "del_flag")
    private Boolean delFlag;

    /**
     * 排序字段
     */
    @TableField(value = "sort_num")
    private Integer sortNum;

    private static final long serialVersionUID = 1L;

}
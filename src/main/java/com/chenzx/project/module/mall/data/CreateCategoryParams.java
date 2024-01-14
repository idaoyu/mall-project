package com.chenzx.project.module.mall.data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 创建类目 params
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-14 16:00
 **/
@Data
public class CreateCategoryParams {

    /**
     * 一级类目名字
     */
    @NotEmpty(message = "类目名字不能为空")
    private String name;

    /**
     * 一级类目排序字段
     */
    @NotNull(message = "排序字段不能为空")
    private Integer sortNum;

    /**
     * 二级类目
     */
    @Valid
    private List<CreateCategoryParams> children;

}

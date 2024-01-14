package com.chenzx.project.module.mall.data;

import com.chenzx.project.utils.ValidatedGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * 创建/修改 属性 params
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-14 20:02
 **/
@Data
public class OperationPropertyParams {

    /**
     * 属性id
     */
    @NotEmpty(message = "要修改的属性id不能为空", groups = ValidatedGroup.UpdateGroup.class)
    private String id;

    /**
     * 属性名字
     * 例如 颜色
     */
    @NotEmpty(message = "属性名字不能为空", groups = {ValidatedGroup.CreateGroup.class, ValidatedGroup.UpdateGroup.class})
    private String name;

    /**
     * 属性值集合
     * 例如 深空灰、暗夜黑、珍珠白 等
     */
    @Valid
    @NotEmpty(message = "属性值列表不能为空", groups = {ValidatedGroup.CreateGroup.class, ValidatedGroup.UpdateGroup.class})
    private List<OperationPropertyValueDTO> valueList;

}

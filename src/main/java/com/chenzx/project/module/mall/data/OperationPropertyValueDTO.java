package com.chenzx.project.module.mall.data;

import com.chenzx.project.utils.ValidatedGroup;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 新增/修改 属性值 dto
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-14 20:16
 **/
@Data
public class OperationPropertyValueDTO {

    private String id;

    @NotEmpty(message = "属性值不能为空", groups = {ValidatedGroup.CreateGroup.class, ValidatedGroup.UpdateGroup.class})
    private String value;

}

package com.chenzx.project.common.data;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 通用分页查询请求入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-14 14:59
 **/
@Data
public class PageParams {

    /**
     * 页码
     */
    @NotNull(message = "current 不能为空")
    private Integer current;

    /**
     * 一页大小
     */
    @NotNull(message = "size 不能为空")
    private Integer size;

}

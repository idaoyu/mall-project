package com.chenzx.project.module.mall.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chenzx.project.common.data.R;
import com.chenzx.project.module.mall.business.PropertyManageBusinessService;
import com.chenzx.project.module.mall.data.OperationPropertyParams;
import com.chenzx.project.module.mall.data.PageGetPropertyParams;
import com.chenzx.project.module.mall.data.PageGetPropertyVO;
import com.chenzx.project.utils.ValidatedGroup;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商品属性管理接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-14 19:57
 **/
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mall/manage/property")
public class PropertyManageController {

    private final PropertyManageBusinessService propertyManageBusinessService;

    /**
     * 新增属性
     *
     * @param params OperationPropertyParams
     * @return R<?>
     */
    @PostMapping
    public R<?> createProperty(@RequestBody @Validated(ValidatedGroup.CreateGroup.class) OperationPropertyParams params) {
        propertyManageBusinessService.createProperty(params);
        return R.success();
    }

    /**
     * 删除属性
     *
     * @param id 要删除的属性id
     * @return R<?>
     */
    @DeleteMapping
    public R<?> removeProperty(@RequestParam @NotEmpty(message = "要删除的属性不能为空") String id) {
        propertyManageBusinessService.removeProperty(id);
        return R.success();
    }

    /**
     * 修改属性
     *
     * @param params OperationPropertyParams
     * @return R<?>
     */
    @PutMapping
    public R<?> updateProperty(
            @RequestBody @Validated(ValidatedGroup.UpdateGroup.class) OperationPropertyParams params) {
        propertyManageBusinessService.updateProperty(params);
        return R.success();
    }

    /**
     * 查询属性
     *
     * @param params PageGetPropertyParams
     * @return R<IPage < PageGetPropertyVO>>
     */
    @GetMapping
    public R<IPage<PageGetPropertyVO>> pageGetProperty(@Validated PageGetPropertyParams params) {
        return propertyManageBusinessService.pageGetProperty(params);
    }


}

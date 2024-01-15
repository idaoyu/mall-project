package com.chenzx.project.module.mall.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chenzx.project.common.data.R;
import com.chenzx.project.module.mall.business.BrandManageBusinessService;
import com.chenzx.project.module.mall.data.OperationBrandParams;
import com.chenzx.project.module.mall.data.PageGetBrandParams;
import com.chenzx.project.module.mall.data.PageGetBrandVO;
import com.chenzx.project.utils.ValidatedGroup;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 品牌管理接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-15 19:54
 **/
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mall/manage/brand")
public class BrandManageController {

    private final BrandManageBusinessService brandManageBusinessService;

    /**
     * 新增品牌
     *
     * @param params OperationBrandParams
     * @return R<?>
     */
    @PostMapping
    public R<?> createBrand(@RequestBody @Validated(ValidatedGroup.CreateGroup.class) OperationBrandParams params) {
        brandManageBusinessService.createBrand(params);
        return R.success();
    }

    /**
     * 删除品牌
     *
     * @param id 品牌id
     * @return R<?>
     */
    @DeleteMapping
    public R<?> removeBrand(@NotEmpty(message = "品牌id不能为空") String id) {
        brandManageBusinessService.removeBrand(id);
        return R.success();
    }

    /**
     * 修改品牌
     *
     * @param params OperationBrandParams
     * @return R<?>
     */
    @PutMapping
    public R<?> updateBrand(@RequestBody @Validated(ValidatedGroup.UpdateGroup.class) OperationBrandParams params) {
        brandManageBusinessService.updateBrand(params);
        return R.success();
    }

    /**
     * 分页查询品牌
     *
     * @param params PageGetBrandParams
     * @return R<IPage < PageGetBrandVO>>
     */
    @GetMapping
    public R<IPage<PageGetBrandVO>> pageGetBrand(@Validated PageGetBrandParams params) {
        return brandManageBusinessService.pageGetBrand(params);
    }

}

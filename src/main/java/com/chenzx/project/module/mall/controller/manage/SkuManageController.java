package com.chenzx.project.module.mall.controller.manage;

import com.chenzx.project.common.data.R;
import com.chenzx.project.module.mall.business.SkuManageBusinessService;
import com.chenzx.project.module.mall.data.CreateSkuParams;
import com.chenzx.project.module.mall.data.UpdateSkuStatusParams;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商品sku管理接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-15 21:28
 **/
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mall/manage/sku")
public class SkuManageController {

    private final SkuManageBusinessService skuManageBusinessService;

    /**
     * 创建sku
     *
     * @param params CreateSkuParams
     * @return R<?>
     */
    @PostMapping
    public R<?> createSku(@RequestBody @Validated CreateSkuParams params) {
        skuManageBusinessService.createSku(params);
        return R.success();
    }

    /**
     * 修改sku状态（上架/下架）
     *
     * @param params UpdateSkuStatusParams
     * @return R<?>
     */
    @PutMapping("/updateStatus")
    public R<?> updateSkuStatus(@RequestBody @Validated UpdateSkuStatusParams params) {
        skuManageBusinessService.updateSkuStatus(params);
        return R.success();
    }

}

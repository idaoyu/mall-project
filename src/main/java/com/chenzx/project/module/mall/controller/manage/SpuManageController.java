package com.chenzx.project.module.mall.controller.manage;

import com.chenzx.project.common.data.R;
import com.chenzx.project.module.mall.business.SpuManageBusinessService;
import com.chenzx.project.module.mall.data.OperationSpuParams;
import com.chenzx.project.utils.ValidatedGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品spu管理接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-15 20:27
 **/
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mall/manage/spu")
public class SpuManageController {

    private final SpuManageBusinessService spuManageBusinessService;

    /**
     * 创建商品
     *
     * @param params OperationSpuParams
     * @return R<String>
     */
    @PostMapping
    public R<String> createSpu(@RequestBody @Validated(ValidatedGroup.CreateGroup.class) OperationSpuParams params) {
        return spuManageBusinessService.createSpu(params);
    }


}

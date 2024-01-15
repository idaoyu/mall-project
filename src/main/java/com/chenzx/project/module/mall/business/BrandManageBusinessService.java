package com.chenzx.project.module.mall.business;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chenzx.project.common.data.R;
import com.chenzx.project.common.exception.BizException;
import com.chenzx.project.module.mall.data.OperationBrandParams;
import com.chenzx.project.module.mall.data.PageGetBrandParams;
import com.chenzx.project.module.mall.data.PageGetBrandVO;
import com.chenzx.project.module.mall.entity.MallBrand;
import com.chenzx.project.module.mall.service.MallBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 品牌管理接口 业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-15 19:55
 **/
@Service
@RequiredArgsConstructor
public class BrandManageBusinessService {

    private final MallBrandService mallBrandService;

    public void createBrand(OperationBrandParams params) {
        boolean existed = mallBrandService.existBrandByName(params.getName());
        if (existed) {
            throw new BizException("品牌名字:" + params.getName() + " 已存在");
        }
        MallBrand brand = MallBrand.builder()
                .name(params.getName())
                .logoUrl(params.getLogoUrl())
                .build();
        boolean save = mallBrandService.save(brand);
        if (!save) {
            throw new BizException("创建品牌失败，请稍后重试");
        }

    }

    public void removeBrand(String id) {
        mallBrandService.getOptById(id).orElseThrow(() -> new BizException("要删除的品牌不存在"));
        boolean rm = mallBrandService.removeById(id);
        if (!rm) {
            throw new BizException("删除品牌失败，请稍后重试");
        }
    }

    public void updateBrand(OperationBrandParams params) {
        MallBrand brand = mallBrandService.getOptById(params.getId())
                .orElseThrow(() -> new BizException("要修改的品牌不存在"));
        // 设置新的品牌名字 和 logo
        brand.setName(params.getName());
        brand.setLogoUrl(params.getLogoUrl());
        boolean updated = mallBrandService.updateById(brand);
        if (!updated) {
            throw new BizException("修改品牌失败，请稍后重试");
        }
    }

    public R<IPage<PageGetBrandVO>> pageGetBrand(PageGetBrandParams params) {
        return R.success(mallBrandService.pageGetBrand(params).convert(brand -> {
            PageGetBrandVO.PageGetBrandVOBuilder builder = PageGetBrandVO.builder();
            builder.id(brand.getId());
            builder.name(brand.getName());
            builder.logoUrl(brand.getLogoUrl());
            return builder.build();
        }));
    }
}

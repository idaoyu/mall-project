package com.chenzx.project.module.mall.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenzx.project.module.mall.data.PageGetBrandParams;
import com.chenzx.project.module.mall.entity.MallBrand;
import com.chenzx.project.module.mall.mapper.MallBrandMapper;
import org.springframework.stereotype.Service;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024/1/15 19:53
 **/
@Service
public class MallBrandService extends ServiceImpl<MallBrandMapper, MallBrand> {

    /**
     * 是否存在指定品牌名字
     *
     * @param name 品牌名字
     * @return 存在返回 true
     */
    public boolean existBrandByName(String name) {
        LambdaQueryWrapper<MallBrand> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(MallBrand::getName, name);
        return super.exists(wrapper);
    }


    public IPage<MallBrand> pageGetBrand(PageGetBrandParams params) {
        LambdaQueryWrapper<MallBrand> wrapper = Wrappers.lambdaQuery();
        return super.page(Page.of(params.getCurrent(), params.getPageSize()), wrapper);
    }
}

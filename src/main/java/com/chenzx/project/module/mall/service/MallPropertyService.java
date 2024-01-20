package com.chenzx.project.module.mall.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenzx.project.module.mall.data.PageGetPropertyParams;
import com.chenzx.project.module.mall.entity.MallProperty;
import com.chenzx.project.module.mall.mapper.MallPropertyMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024/1/14 20:00
 **/
@Service
public class MallPropertyService extends ServiceImpl<MallPropertyMapper, MallProperty> {

    /**
     * 根据属性 id 更新属性 name
     *
     * @param name 属性名字
     * @param id   属性id
     * @return 成功返回 true
     */
    public boolean updateNameById(String name, String id) {
        LambdaUpdateWrapper<MallProperty> wrapper = Wrappers.lambdaUpdate();
        wrapper.set(MallProperty::getName, name);
        wrapper.eq(MallProperty::getId, id);
        return super.update(wrapper);
    }

    /**
     * 分页查询属性
     *
     * @param params PageGetPropertyParams
     * @return IPage<MallProperty>
     */
    public IPage<MallProperty> pageByParams(PageGetPropertyParams params) {
        LambdaQueryWrapper<MallProperty> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StringUtils.isNotBlank(params.getId()), MallProperty::getId, params.getId());
        wrapper.like(StringUtils.isNotBlank(params.getName()), MallProperty::getName, params.getName());
        return super.page(Page.of(params.getCurrent(), params.getPageSize()), wrapper);
    }

}

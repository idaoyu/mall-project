package com.chenzx.project.module.mall.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenzx.project.module.mall.data.CategoryManagePageGetParams;
import com.chenzx.project.module.mall.entity.MallCategory;
import com.chenzx.project.module.mall.mapper.MallCategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024/1/14 14:56
 **/
@Service
public class MallCategoryService extends ServiceImpl<MallCategoryMapper, MallCategory> {

    /**
     * 分页查询全部一级类目
     *
     * @param params CategoryManagePageGetParams
     * @return IPage<MallCategory>
     */
    public IPage<MallCategory> pageGetTopCategory(CategoryManagePageGetParams params) {
        LambdaQueryWrapper<MallCategory> wrapper = Wrappers.lambdaQuery();
        wrapper.orderByAsc(MallCategory::getSortNum);
        wrapper.isNull(MallCategory::getParentId);
        return super.page(Page.of(params.getCurrent(), params.getSize()), wrapper);
    }

    /**
     * 根据一级类目id 查询二级类目
     *
     * @param parentId 一级类目id
     * @return List<MallCategory>
     */
    public List<MallCategory> listByParentId(String parentId) {
        LambdaQueryWrapper<MallCategory> wrapper = Wrappers.lambdaQuery();
        wrapper.orderByAsc(MallCategory::getSortNum);
        wrapper.eq(MallCategory::getParentId, parentId);
        return super.list(wrapper);
    }

    /**
     * 根据父id删除二级类目
     *
     * @param parentId 一级类目id
     * @return 成功返回 true
     */
    public boolean removeByParentId(String parentId) {
        LambdaQueryWrapper<MallCategory> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(MallCategory::getParentId, parentId);
        return super.remove(wrapper);
    }

    /**
     * 一级类目是否存在指定名字
     *
     * @param name 类目名字
     * @return 存在返回 true
     */
    public boolean existFirstCategoryName(String name) {
        LambdaQueryWrapper<MallCategory> wrapper = Wrappers.lambdaQuery();
        wrapper.isNull(MallCategory::getParentId);
        wrapper.eq(MallCategory::getName, name);
        return super.exists(wrapper);
    }


}

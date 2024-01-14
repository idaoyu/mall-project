package com.chenzx.project.module.mall.business;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chenzx.project.common.data.R;
import com.chenzx.project.common.exception.BizException;
import com.chenzx.project.module.mall.data.CategoryManagePageGetParams;
import com.chenzx.project.module.mall.data.CategoryManagePageGetVO;
import com.chenzx.project.module.mall.data.CreateCategoryParams;
import com.chenzx.project.module.mall.data.OperatingCategoryParams;
import com.chenzx.project.module.mall.entity.MallCategory;
import com.chenzx.project.module.mall.service.MallCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品类目管理接口 业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-14 14:57
 **/
@Service
@RequiredArgsConstructor
public class CategoryManageBusinessService {

    private final MallCategoryService mallCategoryService;

    public R<IPage<CategoryManagePageGetVO>> pageGet(CategoryManagePageGetParams params) {
        // 查询最上层类目
        IPage<MallCategory> mallCategoryPage = mallCategoryService.pageGetTopCategory(params);
        return R.success(
                mallCategoryPage.convert(mallCategory -> {
                    // MallCategory -> CategoryManagePageGetParams
                    CategoryManagePageGetVO item =
                            CategoryManagePageGetVO.mallCategory2CategoryManagePageGetVO(mallCategory);
                    // 查询二级类目 并转换为 CategoryManagePageGetParams
                    List<CategoryManagePageGetVO> list = mallCategoryService.listByParentId(mallCategory.getId())
                            .stream().map(CategoryManagePageGetVO::mallCategory2CategoryManagePageGetVO).toList();
                    item.setChildren(list);
                    return item;
                }));
    }

    public void createCategory(CreateCategoryParams params) {
        boolean existed = mallCategoryService.existFirstCategoryName(params.getName());
        if (existed) {
            throw new BizException("一级类目名字不能与其他一级类目名字重复");
        }
        MallCategory mallCategory = new MallCategory();
        mallCategory.setName(params.getName());
        mallCategory.setSortNum(params.getSortNum());
        boolean save = mallCategoryService.save(mallCategory);
        if (!save) {
            throw new BizException("新增类目时产生异常，请稍后重试");
        }
        if (params.getChildren() == null || params.getChildren().isEmpty()) {
            return;
        }
        for (CreateCategoryParams child : params.getChildren()) {
            MallCategory childCategory = new MallCategory();
            childCategory.setParentId(mallCategory.getId());
            childCategory.setName(child.getName());
            childCategory.setSortNum(child.getSortNum());
            boolean saveChild = mallCategoryService.save(childCategory);
            if (!saveChild) {
                throw new BizException("新增类目时产生异常，请稍后重试");
            }
        }
    }

    public void updateCategory(OperatingCategoryParams params) {
        MallCategory mallCategory = mallCategoryService.getOptById(params.getId())
                .orElseThrow(() -> new BizException("要修改的类目不存在"));
        mallCategory.setName(params.getName());
        mallCategory.setSortNum(params.getSortNum());
        boolean success = mallCategoryService.updateById(mallCategory);
        if (!success) {
            throw new BizException("修改类目时产生异常，请稍后重试");
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public void removeCategory(String id) {
        MallCategory mallCategory = mallCategoryService.getOptById(id)
                .orElseThrow(() -> new BizException("要删除的类目不存在"));
        if (mallCategory.getParentId() == null) {
            // 如果要删除的类目是一级类目，则删除下面的所有二级类目
            boolean rmChild = mallCategoryService.removeByParentId(id);
            if (!rmChild) {
                throw new BizException("删除类目时产生异常，请稍后重试");
            }
        }
        boolean rmParent = mallCategoryService.removeById(id);
        if (!rmParent) {
            throw new BizException("删除类目时产生异常，请稍后重试");
        }
    }

    public void addSecondaryCategory(OperatingCategoryParams params) {
        mallCategoryService.getOptById(params.getParentId()).orElseThrow(() -> new BizException("一级类目不存在"));
        MallCategory childCategory = new MallCategory();
        childCategory.setParentId(params.getParentId());
        childCategory.setName(params.getName());
        childCategory.setSortNum(params.getSortNum());
        boolean save = mallCategoryService.save(childCategory);
        if (!save) {
            throw new BizException("添加二级类目失败，请稍后重试");
        }
    }
}

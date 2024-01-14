package com.chenzx.project.module.mall.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chenzx.project.common.data.R;
import com.chenzx.project.module.mall.business.CategoryManageBusinessService;
import com.chenzx.project.module.mall.data.CategoryManagePageGetParams;
import com.chenzx.project.module.mall.data.CategoryManagePageGetVO;
import com.chenzx.project.module.mall.data.CreateCategoryParams;
import com.chenzx.project.module.mall.data.OperatingCategoryParams;
import com.chenzx.project.utils.ValidatedGroup;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商品类目管理接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-14 14:56
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mall/manage/category")
public class CategoryManageController {

    private final CategoryManageBusinessService categoryManageBusinessService;

    /**
     * 分页查询类目
     *
     * @param params CategoryManagePageGetParams
     * @return CategoryManagePageGetVO
     */
    @GetMapping
    public R<IPage<CategoryManagePageGetVO>> pageGet(@Validated CategoryManagePageGetParams params) {
        return categoryManageBusinessService.pageGet(params);
    }

    /**
     * 创建类目
     *
     * @param params OperationCategoryParams
     * @return R<?>
     */
    @PostMapping
    public R<?> createCategory(@RequestBody @Validated CreateCategoryParams params) {
        categoryManageBusinessService.createCategory(params);
        return R.success();
    }

    /**
     * 添加二级类目
     *
     * @param params OperatingCategoryParams
     * @return R<?>
     */
    @PostMapping("/addSecondaryCategory")
    public R<?> addSecondaryCategory(
            @RequestBody @Validated(ValidatedGroup.CreateGroup.class) OperatingCategoryParams params) {
        categoryManageBusinessService.addSecondaryCategory(params);
        return R.success();
    }

    /**
     * 修改类目
     *
     * @param params OperationCategoryParams
     * @return R<?>
     */
    @PutMapping
    public R<?> updateCategory(@RequestBody @Validated(ValidatedGroup.UpdateGroup.class) OperatingCategoryParams params) {
        categoryManageBusinessService.updateCategory(params);
        return R.success();
    }

    /**
     * 删除类目（如果是父级类目，会将该类目的子级类目同时删除）
     *
     * @param id 类目id
     * @return R<?>
     */
    @DeleteMapping
    public R<?> removeCategory(@RequestParam @NotEmpty(message = "要删除的类目id不能为空") String id) {
        categoryManageBusinessService.removeCategory(id);
        return R.success();
    }

}

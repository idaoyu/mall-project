package com.chenzx.project.module.mall.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenzx.project.module.mall.entity.MallPropertyValue;
import com.chenzx.project.module.mall.mapper.MallPropertyValueMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024/1/14 20:02
 **/
@Service
public class MallPropertyValueService extends ServiceImpl<MallPropertyValueMapper, MallPropertyValue> {

    /**
     * 根据属性id删除属性值
     *
     * @param propertyId 属性id
     * @return 成功返回 true
     */
    public boolean removeByPropertyId(String propertyId) {
        LambdaQueryWrapper<MallPropertyValue> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(MallPropertyValue::getPropertyId, propertyId);
        return super.remove(wrapper);
    }

    /**
     * 根据 属性id 查询属性值
     *
     * @param propertyId 属性id
     * @return 属性值集合
     */
    public List<MallPropertyValue> listByPropertyId(String propertyId) {
        LambdaQueryWrapper<MallPropertyValue> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(MallPropertyValue::getPropertyId, propertyId);
        return super.list(wrapper);
    }

    /**
     * 根据 属性值id 更新属性值
     *
     * @param value 新的 value
     * @param id    要更新的属性值id
     * @return 成功返回 true
     */
    public boolean updateValueById(String value, String id) {
        LambdaUpdateWrapper<MallPropertyValue> wrapper = Wrappers.lambdaUpdate();
        wrapper.set(MallPropertyValue::getValue, value);
        wrapper.eq(MallPropertyValue::getId, id);
        return super.update(wrapper);
    }

}

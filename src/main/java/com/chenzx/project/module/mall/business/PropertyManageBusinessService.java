package com.chenzx.project.module.mall.business;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chenzx.project.common.data.R;
import com.chenzx.project.common.exception.BizException;
import com.chenzx.project.module.mall.data.*;
import com.chenzx.project.module.mall.entity.MallProperty;
import com.chenzx.project.module.mall.entity.MallPropertyValue;
import com.chenzx.project.module.mall.service.MallPropertyService;
import com.chenzx.project.module.mall.service.MallPropertyValueService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品属性管理接口 业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-14 19:58
 **/
@Service
@RequiredArgsConstructor
public class PropertyManageBusinessService {

    private final MallPropertyService mallPropertyService;
    private final MallPropertyValueService mallPropertyValueService;

    @Transactional
    public void createProperty(OperationPropertyParams params) {
        MallProperty property = MallProperty.builder().name(params.getName()).build();
        boolean saveProperty = mallPropertyService.save(property);
        if (!saveProperty) {
            throw new BizException("创建属性时产生错误，请稍后重试");
        }
        for (OperationPropertyValueDTO dto : params.getValueList()) {
            MallPropertyValue propertyValue = MallPropertyValue
                    .builder().propertyId(property.getId()).value(dto.getValue()).build();
            boolean savePropertyValue = mallPropertyValueService.save(propertyValue);
            if (!savePropertyValue) {
                throw new BizException("创建属性时产生错误，请稍后重试");
            }
        }
    }

    @Transactional
    public void removeProperty(String id) {
        mallPropertyService.getOptById(id).orElseThrow(() -> new BizException("要删除的属性不存在"));
        boolean rmProperty = mallPropertyService.removeById(id);
        if (!rmProperty) {
            throw new BizException("删除属性时产生错误，请稍后重试");
        }
        boolean rmPropertyValue = mallPropertyValueService.removeByPropertyId(id);
        if (!rmPropertyValue) {
            throw new BizException("删除属性时产生错误，请稍后重试");
        }
    }

    @Transactional
    public void updateProperty(OperationPropertyParams params) {
        MallProperty property = mallPropertyService.getOptById(params.getId())
                .orElseThrow(() -> new BizException("要修改的属性值不存在"));
        if (!property.getName().equals(params.getName())) {
            boolean updated = mallPropertyService.updateNameById(params.getName(), params.getId());
            if (!updated) {
                throw new BizException("修改属性时产生错误，请稍后重试");
            }
        }
        List<MallPropertyValue> mallPropertyValueList = mallPropertyValueService.listByPropertyId(params.getId());
        Map<String, MallPropertyValue> valueMap = mallPropertyValueList
                .stream().collect(Collectors.toMap(MallPropertyValue::getId, v -> v));
        for (OperationPropertyValueDTO valueDTO : params.getValueList()) {
            // 传入属性值没有id 则代表新增
            if (StringUtils.isBlank(valueDTO.getId())) {
                MallPropertyValue addPropertyValue = MallPropertyValue
                        .builder().propertyId(params.getId()).value(valueDTO.getValue()).build();
                boolean save = mallPropertyValueService.save(addPropertyValue);
                if (!save) {
                    throw new BizException("修改属性时产生错误，请稍后重试");
                }
                continue;
            }
            MallPropertyValue waitUpdatePropertyValue = valueMap.get(valueDTO.getId());
            if (waitUpdatePropertyValue == null) {
                throw new BizException("属性值id " + valueDTO.getId() + " 无效");
            }
            // 如果传入的值和数据库中的值不想等
            if (!waitUpdatePropertyValue.getValue().equals(valueDTO.getValue())) {
                boolean updated = mallPropertyValueService.updateValueById(valueDTO.getValue(), valueDTO.getId());
                if (!updated) {
                    throw new BizException("修改属性时产生错误，请稍后重试");
                }
            }
        }
        List<String> newValueIdList = params.getValueList().stream().map(OperationPropertyValueDTO::getId).toList();
        mallPropertyValueList.stream().filter(v -> !newValueIdList.contains(v.getId()))
                .forEach(v -> {
                    boolean rmItem = mallPropertyValueService.removeById(v.getId());
                    if (!rmItem) {
                        throw new BizException("修改属性时产生错误，请稍后重试");
                    }
                });
    }

    public R<IPage<PageGetPropertyVO>> pageGetProperty(PageGetPropertyParams params) {
        IPage<MallProperty> mallPropertyPage = mallPropertyService.pageByParams(params);
        return R.success(
                mallPropertyPage.convert(property -> PageGetPropertyVO.builder().id(property.getId())
                        .name(property.getName())
                        .valueList(
                                mallPropertyValueService.listByPropertyId(property.getId())
                                        .stream().map(v -> new PageGetPropertyValueVO(v.getId(), v.getValue())).toList()
                        )
                        .build())
        );
    }
}

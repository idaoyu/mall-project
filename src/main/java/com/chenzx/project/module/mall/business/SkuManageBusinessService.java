package com.chenzx.project.module.mall.business;

import com.chenzx.project.common.exception.BizException;
import com.chenzx.project.module.mall.constant.SkuStatusConstant;
import com.chenzx.project.module.mall.data.*;
import com.chenzx.project.module.mall.entity.MallProperty;
import com.chenzx.project.module.mall.entity.MallPropertyValue;
import com.chenzx.project.module.mall.entity.MallSku;
import com.chenzx.project.module.mall.entity.MallSpu;
import com.chenzx.project.module.mall.service.MallPropertyService;
import com.chenzx.project.module.mall.service.MallPropertyValueService;
import com.chenzx.project.module.mall.service.MallSkuService;
import com.chenzx.project.module.mall.service.MallSpuService;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 商品sku管理接口 业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-15 21:29
 **/
@Service
@RequiredArgsConstructor
public class SkuManageBusinessService {

    private final MallSpuService mallSpuService;
    private final MallPropertyService mallPropertyService;
    private final MallPropertyValueService mallPropertyValueService;
    private final MallSkuService mallSkuService;

    public void createSku(CreateSkuParams params) {
        MallSpu mallSpu = mallSpuService.getOptById(params.getSpuId())
                .orElseThrow(() -> new BizException("商品信息不存在"));
        // 如果商品不是多规格商品
        if (!mallSpu.getMultiSpecification()) {
            throw new BizException("单规格商品不需要指定sku");
        }

        // 缓存属性与属性值 避免循环查db
        Map<String, MallProperty> propertyMap = Maps.newHashMap();
        Map<String, MallPropertyValue> propertyValueMap = Maps.newHashMap();
        for (CreateSkuInfoDTO skuInfoDTO : params.getSkuPropertyList()) {
            MallSku.MallSkuBuilder builder = MallSku.builder();
            // 设置sku对应的商品id
            builder.spuId(mallSpu.getId());
            // 设置sku库存
            builder.stock(skuInfoDTO.getStock());
            // 设置sku价格
            builder.price(skuInfoDTO.getPrice());
            // sku 默认设置为上架
            builder.skuStatus(SkuStatusConstant.SHELVE.getValue());
            StringBuilder skuInfoZhStringBuilder = new StringBuilder();
            StringBuilder skuInfoStringBuilder = new StringBuilder();
            // 循环该sku包含的属性
            for (CreateSkuPropertyDTO createSkuPropertyDTO : skuInfoDTO.getPropertyList()) {
                // 查询属性、属性值
                PropertyDTO dto = getPropertyAndValue(
                        createSkuPropertyDTO.getPropertyId(), createSkuPropertyDTO.getPropertyValueId(),
                        propertyMap, propertyValueMap);
                // 使用 [颜色:蓝色] 这种规格 拼接 sku
                skuInfoZhStringBuilder.append("[")
                        .append(dto.getProperty().getName()).append(":").append(dto.getPropertyValue().getValue())
                        .append("]").append(",");
                skuInfoStringBuilder.append("[")
                        .append(dto.getProperty().getId()).append(":").append(dto.getPropertyValue().getId())
                        .append("]").append(",");
            }
            builder.skuInfoZhStr(skuInfoZhStringBuilder.toString());
            builder.skuInfoStr(skuInfoStringBuilder.toString());
            builder.createTime(new Date());
            builder.updateTime(new Date());
            MallSku sku = builder.build();
            boolean save = mallSkuService.save(sku);
            if (!save) {
                throw new BizException("创建商品sku失败，请稍后重试");
            }
        }


    }

    /**
     * 根据属性id与属性值id 获取属性与属性值
     *
     * @param propertyId       属性id
     * @param propertyValueId  属性值id
     * @param propertyMap      属性map 用来缓存属性数据
     * @param propertyValueMap 属性值map 用来缓存属性值数据
     * @return PropertyDTO
     */
    private PropertyDTO getPropertyAndValue(String propertyId, String propertyValueId, Map<String, MallProperty> propertyMap
            , Map<String, MallPropertyValue> propertyValueMap) {
        MallProperty resultProperty;
        MallPropertyValue resultPropertyValue;
        // 查询属性
        if (propertyMap.containsKey(propertyId)) {
            resultProperty = propertyMap.get(propertyId);
        } else {
            resultProperty = mallPropertyService.getOptById(propertyId)
                    .orElseThrow(() -> new BizException("id: " + propertyId + " 对应的属性不存在"));
            propertyMap.put(propertyId, resultProperty);
        }
        // 查询属性值
        if (propertyValueMap.containsKey(propertyValueId)) {
            resultPropertyValue = propertyValueMap.get(propertyValueId);
        } else {
            resultPropertyValue = mallPropertyValueService.getOptById(propertyValueId)
                    .orElseThrow(() -> new BizException("id: " + propertyValueId + " 对应的属性值不存在"));
            if (!resultPropertyValue.getPropertyId().equals(propertyId)) {
                throw new BizException("属性与属性值不匹配,属性值id: " + propertyValueId + ",期望的属性id为: " + propertyId + ",但实际为: " + resultPropertyValue.getPropertyId());
            }
            propertyValueMap.put(propertyValueId, resultPropertyValue);
        }
        return PropertyDTO.builder().property(resultProperty).propertyValue(resultPropertyValue).build();
    }

    public void updateSkuStatus(UpdateSkuStatusParams params) {
        mallSkuService.getOptById(params.getId()).orElseThrow(() -> new BizException("sku不存在"));
        boolean updated = mallSkuService.updateSkuStatus(params.getId(), params.getStatus());
        if (!updated) {
            throw new BizException("上架/下架 sku失败，请稍后重试");
        }
    }
}

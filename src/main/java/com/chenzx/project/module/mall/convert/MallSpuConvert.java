package com.chenzx.project.module.mall.convert;

import com.chenzx.project.module.mall.data.OperationSpuParams;
import com.chenzx.project.module.mall.entity.MallSpu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 * 对 MallSpu 实体的转换
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-15 20:53
 **/
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MallSpuConvert {

    /**
     * 将 OperationSpuParams 转换为 MallSpu
     *
     * @param params OperationSpuParams
     * @return MallSpu
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "brandId", target = "brandId")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "sortNum", target = "sortNum")
    @Mapping(source = "multiSpecification", target = "multiSpecification")
    @Mapping(target = "spuImageUrl", ignore = true)
    @Mapping(target = "spuSalesVolume", ignore = true)
    @Mapping(target = "spuStatus", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "delFlag", ignore = true)
    MallSpu operationSpuParams2MallSpu(OperationSpuParams params);

}

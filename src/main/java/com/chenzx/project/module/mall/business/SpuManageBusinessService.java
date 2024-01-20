package com.chenzx.project.module.mall.business;

import com.chenzx.project.common.data.R;
import com.chenzx.project.common.exception.BizException;
import com.chenzx.project.module.mall.constant.SpuStatusConstant;
import com.chenzx.project.module.mall.convert.MallSpuConvert;
import com.chenzx.project.module.mall.data.OperationSpuParams;
import com.chenzx.project.module.mall.entity.MallSpu;
import com.chenzx.project.module.mall.service.MallBrandService;
import com.chenzx.project.module.mall.service.MallCategoryService;
import com.chenzx.project.module.mall.service.MallSpuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 商品spu管理接口 业务逻辑
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024-01-15 20:28
 **/
@Service
@RequiredArgsConstructor
public class SpuManageBusinessService {

    private final MallSpuService mallSpuService;
    private final MallBrandService mallBrandService;
    private final MallCategoryService mallCategoryService;
    private final MallSpuConvert mallSpuConvert;

    public R<String> createSpu(OperationSpuParams params) {
        // 校验 类目、品牌是否存在
        mallBrandService.getOptById(params.getBrandId()).orElseThrow(() -> new BizException("品牌不存在"));
        mallCategoryService.getOptById(params.getCategoryId()).orElseThrow(() -> new BizException("类目不存在"));
        // 将 params -> entity
        MallSpu mallSpu = mallSpuConvert.operationSpuParams2MallSpu(params);
        // 设置创建、修改事件 上架状态等
        // 默认为下架状态
        mallSpu.setSpuStatus(SpuStatusConstant.OFF_SHELVE.getValue());
        mallSpu.setCreateTime(new Date());
        mallSpu.setUpdateTime(new Date());
        // 主图在创建 spu 时滞空 后续增加主图时 更新
        boolean save = mallSpuService.save(mallSpu);
        if (!save) {
            throw new BizException("创建商品失败，请稍后重试");
        }
        return R.success("创建商品成功,当前为下架状态,待完善其他配置后,请手动上架该商品");
    }
}

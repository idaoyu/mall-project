package com.chenzx.project.module.mall.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenzx.project.module.mall.entity.MallSku;
import com.chenzx.project.module.mall.mapper.MallSkuMapper;
import org.springframework.stereotype.Service;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2024/1/15 21:28
 **/
@Service
public class MallSkuService extends ServiceImpl<MallSkuMapper, MallSku> {

    /**
     * 更新 sku 的状态
     *
     * @param id           skuId
     * @param newSkuStatus 状态
     * @return 成功返回 true
     */
    public boolean updateSkuStatus(Long id, Integer newSkuStatus) {
        LambdaUpdateWrapper<MallSku> wrapper = Wrappers.lambdaUpdate();
        wrapper.set(MallSku::getSkuStatus, newSkuStatus);
        wrapper.eq(MallSku::getId, id);
        return super.update(wrapper);
    }

}

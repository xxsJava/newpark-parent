package com.newpark.pay.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newpark.pay.service.entity.UsrWater;
import com.newpark.pay.service.entity.vo.WaterParamsVo;
import com.newpark.pojo.vo.PageInfoVo;

import java.util.List;

/**
 * <p>
 * 支付流水表 服务类
 * </p>
 *
 * @author xxs18
 * @since 2023-12-18
 */
public interface IUsrWaterService extends IService<UsrWater> {

    List<UsrWater> usrWaterFindAll(PageInfoVo pageInfoVo, WaterParamsVo waterParamsVo,String token);

}

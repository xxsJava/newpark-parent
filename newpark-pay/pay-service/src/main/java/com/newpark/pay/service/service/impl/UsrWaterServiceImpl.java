package com.newpark.pay.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newpark.core.utils.StringUtils;
import com.newpark.pay.service.entity.UsrWater;
import com.newpark.pay.service.entity.vo.WaterParamsVo;
import com.newpark.pay.service.mapper.UsrWaterMapper;
import com.newpark.pay.service.service.IUsrWaterService;
import com.newpark.pojo.vo.PageInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 支付流水表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-12-18
 */
@Service
public class UsrWaterServiceImpl extends ServiceImpl<UsrWaterMapper, UsrWater> implements IUsrWaterService {

    @Resource
    private UsrWaterMapper usrWaterMapper;

    @Override
    public List<UsrWater> usrWaterFindAll(PageInfoVo pageInfoVo, WaterParamsVo waterParamsVo,String uId) {
        PageHelper.startPage(pageInfoVo.getPageNo(), pageInfoVo.getPageSize());

        //查询流水
        QueryWrapper<UsrWater> queryWrap = new QueryWrapper<>();
        queryWrap.eq(StringUtils.isNotEmpty(waterParamsVo.getWaterType()),"water_type",waterParamsVo.getWaterType());
        queryWrap.eq(StringUtils.isNotEmpty(waterParamsVo.getWaterManner()),"water_manner",waterParamsVo.getWaterManner());
        queryWrap.eq(StringUtils.isNotEmpty(uId),"u_id",uId);

        return PageInfo.of(usrWaterMapper.selectList(queryWrap)).getList();
    }
}

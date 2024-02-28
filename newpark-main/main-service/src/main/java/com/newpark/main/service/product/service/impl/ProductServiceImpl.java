package com.newpark.main.service.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newpark.base.enums.SortEnum;
import com.newpark.core.utils.StringUtils;
import com.newpark.main.service.config.IdGeneratorSnowflake;
import com.newpark.main.service.entity.Product;
import com.newpark.main.service.entity.vo.ProductParamVo;
import com.newpark.main.service.entity.vo.ProductUptVo;
import com.newpark.main.service.product.mapper.ProductMapper;
import com.newpark.main.service.product.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newpark.pojo.vo.PageInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-11-30
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private IdGeneratorSnowflake idGeneratorSnowflake;

    @Override
    public List<Product> productsByFind(PageInfoVo pageInfoVo, ProductParamVo productParamDto) {
        PageHelper.startPage(pageInfoVo.getPageNo(), pageInfoVo.getPageSize());

        QueryWrapper<Product> queryProduct = new QueryWrapper<>();

        queryProduct.eq(StringUtils.isNotBlank(productParamDto.getPStatus()),"p_status",productParamDto.getPStatus());

        //排序
        queryProduct.orderByAsc(SortEnum.ASC.toString().equals(productParamDto.getPriceSort()),"p_price");
        queryProduct.orderByDesc(SortEnum.DESC.toString().equals(productParamDto.getPriceSort()),"p_price");
        queryProduct.orderByAsc(SortEnum.ASC.toString().equals(productParamDto.getTimeSort()),"p_pub_time");
        queryProduct.orderByDesc(SortEnum.DESC.toString().equals(productParamDto.getTimeSort()),"p_pub_time");

        return PageInfo.of(productMapper.selectList(queryProduct)).getList();
    }

    @Override
    public Boolean productDel(Long pId) {
        return productMapper.deleteById(pId) >= 1;
    }

    @Override
    public Boolean productUpt(ProductUptVo productUptDto) {
        UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("p_id",productUptDto.getPId());
        updateWrapper.set(StringUtils.isNotBlank(productUptDto.getPName()),"p_name",productUptDto.getPName());
        updateWrapper.set(StringUtils.isNotBlank(productUptDto.getPDesc()),"p_desc",productUptDto.getPDesc());
        updateWrapper.set(productUptDto.getPPrice() > 0,"p_price",productUptDto.getPPrice());
        updateWrapper.set(StringUtils.isNotBlank(productUptDto.getPOther()),"p_other",productUptDto.getPOther());

        return productMapper.update(null,updateWrapper) >= 1;
    }

    @Override
    public Boolean productIns(Product product) {
        product.setPId(idGeneratorSnowflake.snowflakeId());
        return productMapper.insert(product) >= 1;
    }

}

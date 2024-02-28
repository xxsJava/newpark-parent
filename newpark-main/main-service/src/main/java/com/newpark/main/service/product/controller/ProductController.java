package com.newpark.main.service.product.controller;

import com.newpark.base.model.vo.R;
import com.newpark.main.service.entity.Product;
import com.newpark.main.service.entity.vo.ProductParamVo;
import com.newpark.main.service.entity.vo.ProductUptVo;
import com.newpark.main.service.product.service.IProductService;
import com.newpark.pojo.vo.PageInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author xxs18
 * @since 2023-11-30
 */
@RestController
@RequestMapping("/product")
@Api(value = "product", tags = "商品管理")
public class ProductController {

    @Resource
    private IProductService iProductService;

    @GetMapping("/productApi")
    @ApiOperation(notes = "浏览商品API", value = "浏览商品API")
    public R<?> productByFind(@Validated PageInfoVo pageInfoVo,@Validated ProductParamVo productParamDto){
        return R.ok(iProductService.productsByFind(pageInfoVo,productParamDto));
    }

    @PostMapping("/productApi")
    @ApiOperation(notes = "发布商品API", value = "发布商品API")
    public R<?> productIns(@RequestBody @Validated Product product){

        return R.ok(iProductService.productIns(product));
    }

    @PutMapping("/productApi")
    @ApiOperation(notes = "商品编辑API", value = "商品编辑API")
    public R<?> productUpt(@RequestBody @Validated ProductUptVo productUptDto){
        return R.ok(iProductService.productUpt(productUptDto));
    }

    @DeleteMapping("/productApi/{pId}")
    @ApiOperation(notes = "商品删除API", value = "商品删除API")
    public R<?> productDel(@PathVariable("pId") Long pId){
        return R.ok(iProductService.productDel(pId));
    }
}

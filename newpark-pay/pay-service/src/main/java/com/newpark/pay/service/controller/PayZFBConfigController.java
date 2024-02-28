package com.newpark.pay.service.controller;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayUserInfoAuthModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayUserInfoAuthRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayUserInfoAuthResponse;
import com.newpark.base.enums.ResponseCodeEnum;
import com.newpark.base.model.vo.R;
import com.newpark.pay.service.config.AlipayConfigs;
import com.newpark.pay.service.config.IdGeneratorSnowflake;
import com.newpark.pay.service.entity.vo.AppPayResultVo;
import com.newpark.pay.service.entity.vo.PayOrderVo;
import com.newpark.pay.service.enums.ProductCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xxs18
 * @Description 支付配置
 * @Date 2023/12/5 15:42
 **/
@Slf4j
@RestController
@RequestMapping("/payConfig")
@Api(value = "payConfig", tags = "支付宝支付功能")
public class PayZFBConfigController {

    @Resource
    private AlipayConfigs alipayConfig;

    @Resource
    private IdGeneratorSnowflake idGeneratorSnowflake;

    /**
     * 1. 返回支付信息给客户端 2. 创建订单 3.
     *
     * @param payOrderVo
     * @return
     * @throws AlipayApiException
     */
    @PostMapping("/scheme")
    @ApiOperation(notes = "生成支付INFO API", value = "生成支付INFO API")
    public R<?> payInfo(@RequestBody @Validated PayOrderVo payOrderVo) throws AlipayApiException {
        payOrderVo.setOutTradeNo(DateTime.now().toString("yyyyMMddHHmmss") + idGeneratorSnowflake.snowflakeId());
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        request.setNotifyUrl("");
        JSONObject bizContent = new JSONObject();
        // 订单号
        bizContent.put("out_trade_no", payOrderVo.getOutTradeNo());
        // 订单总金额
        bizContent.put("total_amount", payOrderVo.getTotalAmount());
        // 订单标题
        bizContent.put("subject", payOrderVo.getSubject());
        // 产品码
        bizContent.put("product_code", ProductCodeEnum.QUICK_MSECURITY_PAY.toString());
        request.setBizContent(bizContent.toString());

        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);

        log.info("ZFB-Pay------>\n" + "订单号: {}\n" + "订单金额: {}\n" + "商品名称: {}\n" + "商品ID: {}\n",
            payOrderVo.getOutTradeNo(), payOrderVo.getTotalAmount(), payOrderVo.getSubject(),
            payOrderVo.getProductId());

        if (response.isSuccess()) {
            return R.ok(response.getBody());
        }
        return R.failed(ResponseCodeEnum.PAY_CODE);
    }

    @PostMapping("/payCallback")
    @ApiOperation(notes = "支付回调API", value = "支付回调API")
    public R<?> payCallback(@RequestBody AppPayResultVo appPayResultVo) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        this.isPay(alipayClient,request,"221732295396510273536","2023120622001437780502131736");

        return R.ok();
    }

    @GetMapping("/authInfo")
    @ApiOperation(notes = "支付登录授权API", value = "支付登录授权API")
    public R<?> payAuthInfo() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        AlipayUserInfoAuthRequest request = new AlipayUserInfoAuthRequest();
        AlipayUserInfoAuthModel model = new AlipayUserInfoAuthModel();
        List<String> scopes = new ArrayList<String>();
        scopes.add("https://openapi-sandbox.dl.alipaydev.com/gateway.do");
        model.setScopes(scopes);
        model.setState("init");
        request.setBizModel(model);
        AlipayUserInfoAuthResponse response = alipayClient.pageExecute(request);

        if (response.isSuccess()) {
            return R.ok(response.getBody());
        }
        return R.failed(ResponseCodeEnum.PAY_CODE);
    }

    /**
     * 1. 对账
     * @param alipayClient
     * @param request
     * 2. 示范数据：
     * @param outTradeNo "221732295396510273536"
     * @param tradeNo "2023120622001437780502131736"
     * @return
     * @throws AlipayApiException
     */
    private Boolean isPay(AlipayClient alipayClient,AlipayTradeQueryRequest request,String outTradeNo,String tradeNo) throws AlipayApiException {

        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(outTradeNo);
        model.setTradeNo(tradeNo);
        request.setBizModel(model);
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());

        return response.isSuccess();
    }

//    @GetMapping("/billFind")
//    @ApiOperation(notes = "支付账单查询API", value = "支付账单查询API")
//    public R<?> payBillFind() throws AlipayApiException {
//        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
//        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
//        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
//        model.setOutTradeNo("221732295396510273536");
//        model.setTradeNo("2023120622001437780502131736");
//        request.setBizModel(model);
//        AlipayTradeQueryResponse response = alipayClient.execute(request);
//        if (response.isSuccess()) {
//            return R.ok(response.getBody());
//        }
//        return R.failed(ResponseCodeEnum.PAY_CODE);
//    }

}

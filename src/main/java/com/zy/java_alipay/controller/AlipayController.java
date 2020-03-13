package com.zy.java_alipay.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.zy.java_alipay.config.AlipayConfig;
import com.zy.java_alipay.entity.AlipayVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhangyao
 * @create:2020-03-02 16:04
 **/
@RestController
public class AlipayController {

    private Logger logger = LoggerFactory.getLogger(AlipayController.class);

    @Autowired
    private AlipayConfig alipayConfig;

    /**
     * 支付
     */
    @GetMapping("/pay")
    public String pay() {
        //实例化订单对象
        AlipayVo alipayVo = new AlipayVo();
        alipayVo.setOut_trade_no("11");
        alipayVo.setProduct_no("22");
        alipayVo.setSubject("33");
        alipayVo.setTime_expire("2020-03-03 16:18:00");
        alipayVo.setTotal_amount("100.00");

        //获取json
        String json = JSON.toJSONString(alipayVo);
        //输出json
        logger.info(json);

        //创建支付请求对象
        AlipayClient alipayClient = new DefaultAlipayClient
                (alipayConfig.getGATWARY_URL(), alipayConfig.getAPP_ID(),
                        alipayConfig.getRSA_PRIVATE_KEY(),
                        alipayConfig.getFORMAT(),
                        alipayConfig.getCHARSET(),
                        alipayConfig.getRSA_PUBLIC_KEY(),
                        alipayConfig.getSIGN_TYPE());

        //设置请求参数 异步通知地址 回调地址
        AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
        //异步通知地址
        alipayTradePagePayRequest.setNotifyUrl(alipayConfig.getNOTIFY_URL());
        //确认支付后同步 跳转地址
        alipayTradePagePayRequest.setReturnUrl(alipayConfig.getRETURN_URL());
        //订单对象详细参数
        alipayTradePagePayRequest.setBizContent(json);
        //发起请求
        String body = "";
        try {
            body = alipayClient.pageExecute(alipayTradePagePayRequest).getBody();
            logger.info(body);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            logger.error("调用支付接口失败", e.toString());
        }
        return body;
    }


    /**
     * 用户确认支付回调
     * 同步回调
     */
    @PostMapping("/return")
    public String returnCall(){
        return "回调";
    }
    /**
     * 交易成功异步回调
     */
    @PostMapping("/notify")
    public String notifyCall(){
        return "异步回调";
    }
}

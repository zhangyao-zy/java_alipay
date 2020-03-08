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
//        alipayTradePagePayRequest.setBizContent(json);
        alipayTradePagePayRequest.setBizContent("{" +
                "\"out_trade_no\":\"20150320010101001\"," +
                "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "\"total_amount\":88.88," +
                "\"subject\":\"Iphone6 16G\"," +
                "\"body\":\"Iphone6 16G\"," +
                "\"time_expire\":\"2016-12-31 10:05:01\"," +
                "      \"goods_detail\":[{" +
                "        \"goods_id\":\"apple-01\"," +
                "\"alipay_goods_id\":\"20010001\"," +
                "\"goods_name\":\"ipad\"," +
                "\"quantity\":1," +
                "\"price\":2000," +
                "\"goods_category\":\"34543238\"," +
                "\"categories_tree\":\"124868003|126232002|126252004\"," +
                "\"body\":\"特价手机\"," +
                "\"show_url\":\"http://www.alipay.com/xxx.jpg\"" +
                "        }]," +
                "\"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "\"extend_params\":{" +
                "\"sys_service_provider_id\":\"2088511833207846\"," +
                "\"hb_fq_num\":\"3\"," +
                "\"hb_fq_seller_percent\":\"100\"," +
                "\"industry_reflux_info\":\"{\\\\\\\"scene_code\\\\\\\":\\\\\\\"metro_tradeorder\\\\\\\",\\\\\\\"channel\\\\\\\":\\\\\\\"xxxx\\\\\\\",\\\\\\\"scene_data\\\\\\\":{\\\\\\\"asset_name\\\\\\\":\\\\\\\"ALIPAY\\\\\\\"}}\"," +
                "\"card_type\":\"S0JP0000\"" +
                "    }," +
                "\"goods_type\":\"0\"," +
                "\"timeout_express\":\"90m\"," +
                "\"promo_params\":\"{\\\"storeIdType\\\":\\\"1\\\"}\"," +
                "\"royalty_info\":{" +
                "\"royalty_type\":\"ROYALTY\"," +
                "        \"royalty_detail_infos\":[{" +
                "          \"serial_no\":1," +
                "\"trans_in_type\":\"userId\"," +
                "\"batch_no\":\"123\"," +
                "\"out_relation_id\":\"20131124001\"," +
                "\"trans_out_type\":\"userId\"," +
                "\"trans_out\":\"2088101126765726\"," +
                "\"trans_in\":\"2088101126708402\"," +
                "\"amount\":0.1," +
                "\"desc\":\"分账测试1\"," +
                "\"amount_percentage\":\"100\"" +
                "          }]" +
                "    }," +
                "\"sub_merchant\":{" +
                "\"merchant_id\":\"19023454\"," +
                "\"merchant_type\":\"alipay: 支付宝分配的间连商户编号, merchant: 商户端的间连商户编号\"" +
                "    }," +
                "\"merchant_order_no\":\"20161008001\"," +
                "\"enable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
                "\"store_id\":\"NJ_001\"," +
                "\"disable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
                "\"qr_pay_mode\":\"1\"," +
                "\"qrcode_width\":100," +
                "\"settle_info\":{" +
                "        \"settle_detail_infos\":[{" +
                "          \"trans_in_type\":\"cardAliasNo\"," +
                "\"trans_in\":\"A0001\"," +
                "\"summary_dimension\":\"A0001\"," +
                "\"settle_entity_id\":\"2088xxxxx;ST_0001\"," +
                "\"settle_entity_type\":\"SecondMerchant、Store\"," +
                "\"amount\":0.1" +
                "          }]" +
                "    }," +
                "\"invoice_info\":{" +
                "\"key_info\":{" +
                "\"is_support_invoice\":true," +
                "\"invoice_merchant_name\":\"ABC|003\"," +
                "\"tax_num\":\"1464888883494\"" +
                "      }," +
                "\"details\":\"[{\\\"code\\\":\\\"100294400\\\",\\\"name\\\":\\\"服饰\\\",\\\"num\\\":\\\"2\\\",\\\"sumPrice\\\":\\\"200.00\\\",\\\"taxRate\\\":\\\"6%\\\"}]\"" +
                "    }," +
                "\"agreement_sign_params\":{" +
                "\"personal_product_code\":\"GENERAL_WITHHOLDING_P\"," +
                "\"sign_scene\":\"INDUSTRY|CARRENTAL\"," +
                "\"external_agreement_no\":\"test\"," +
                "\"external_logon_id\":\"13852852877\"," +
                "\"sign_validity_period\":\"2m\"," +
                "\"third_party_type\":\"PARTNER\"," +
                "\"buckle_app_id\":\"1001164\"," +
                "\"buckle_merchant_id\":\"268820000000414397785\"," +
                "\"promo_params\":\"{\\\"key\\\",\\\"value\\\"}\"" +
                "    }," +
                "\"integration_type\":\"PCWEB\"," +
                "\"request_from_url\":\"https://\"," +
                "\"business_params\":\"{\\\"data\\\":\\\"123\\\"}\"," +
                "\"ext_user_info\":{" +
                "\"name\":\"李明\"," +
                "\"mobile\":\"16587658765\"," +
                "\"cert_type\":\"IDENTITY_CARD\"," +
                "\"cert_no\":\"362334768769238881\"," +
                "\"min_age\":\"18\"," +
                "\"fix_buyer\":\"F\"," +
                "\"need_check_info\":\"F\"" +
                "    }" +
                "  }");

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

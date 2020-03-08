package com.zy.java_alipay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhangyao
 * @create:2020-03-02 15:16
 **/
@Configuration
@ConfigurationProperties(prefix = "alipay")
@Data
public class AlipayConfig {
    /**
     * 应用id
     */
    private String APP_ID;
    /**
     * 密钥
     */
    private String RSA_PRIVATE_KEY;
    /**
     * 公钥
     */
    private String RSA_PUBLIC_KEY;
    /**
     * 支付包网关
     */
    private String GATWARY_URL;
    /**
     * 交易成功支付宝异步通知地址
     */
    private String NOTIFY_URL;
    /**
     * 确认支付后同步跳转路径
     */
    private String RETURN_URL;
    /**
     * 签名类型 RSA2
     */
    private String SIGN_TYPE;
    /**
     * 请求编码 utf8
     */
    private String CHARSET;
    /**
     * 参数格式 json
     */
    private String FORMAT;
}

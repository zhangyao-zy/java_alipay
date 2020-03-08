package com.zy.java_alipay.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhangyao
 * @create:2020-03-02 16:07
 **/
@Data
public class AlipayVo implements Serializable {
    private static final long serialVersionUID = -630746489733055016L;
    /**
     * 订单名称
     */
    private String subject;
    /**
     * 订单编号 唯一
     */
    private String out_trade_no;

    /**
     * 产品编码,与支付宝签约编码相同
     */
    private String product_no;

    /**
     * 订单金额
     */
    private String total_amount;

    /**
     * 绝对超时时间 付款的最晚时间 格式为yyyy-MM-dd HH:mm:ss
     */
    private String time_expire;
}

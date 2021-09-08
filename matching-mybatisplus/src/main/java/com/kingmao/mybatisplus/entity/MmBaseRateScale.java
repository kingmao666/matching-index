package com.kingmao.mybatisplus.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * MM-btc等-趋势图
 * </p>
 *
 * @author kingmao
 * @since 2021-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MmBaseRateScale implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 币种，例如btc，fil
     */
    private String baseCoin;

    /**
     * 当日涨跌幅
     */
    private BigDecimal rate;

    /**
     * 时间刻度
     */
    private Long idx;

    /**
     * 日期
     */
    private Date updateDate;

    private Date ctime;


}

package disruptor.entity;

import com.google.common.base.Preconditions;
import disruptor.enums.OrderInternalType;
import disruptor.enums.Side;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TreeSet;

@Data
@Log4j2
public class Order implements Cloneable {
    private Long id;

    private Long userId;
    /**
     * 买卖方向
     */
    private Side side;
    /**
     * 限价单挂单价格
     */
    private BigDecimal price;
    /**
     * 挂单总数量
     */
    private BigDecimal volume;
    /**
     * 挂单手续费率
     */
    private Double feeRateMaker;
    /**
     * 吃单手续费率
     */
    private Double feeRateTaker;
    /**
     * 付手续费币种折扣，0为关闭该币种支付手续费
     */
    private Double feeCoinRate;

    private BigDecimal dealVolume = new BigDecimal("0");

    private BigDecimal dealMoney = new BigDecimal("0");
    /**
     * 成交均价
     */
    private BigDecimal avgPrice;

    private int status;
    /**
     * 委托类型：1 limit，2 market，3 stop
     */
    private int type;

    private Date ctime;
    /**
     * 订单来源：1web，2app，3api
     */
    private int source;

    private String symbol;

    /** 1:常规订单，2 杠杆订单  (默认1) */
    private int orderType;

    /** 公司ID */
    private Integer companyId;

    /** 是否是最后一条Order */
    private Long rebuildOffset;

    /** 是否为从数据库加载的订单 */
    private boolean isRebuild = false;

    /** 是否为取消单 */
    private boolean isCancel = false;

    /** 取消单列表 */
    private TreeSet<Order> cancelSet;

    /** 主动单定序ID */
    private Long offset;

    /** 订单剩余数量 */
    private BigDecimal remainAmount = new BigDecimal("0");

    @Override
    public Order clone(){
        Order o = null;
        try{
            o = (Order)super.clone();
        }catch(CloneNotSupportedException e) {
            log.error("clone order error, e=",e);
        }
        return o;
    }

    public BigDecimal getUnfilledQuantity() {
        return this.volume.subtract(this.dealVolume);
    }

    public boolean isLimitOrder() {
        return this.type == OrderInternalType.LIMIT.getValue();
    }

    public boolean isMarketOrder() {
        return this.type == OrderInternalType.MARKET.getValue();
    }

    /**
     * 判断两个价格是否有交叉，交叉是成交的条件
     * @param thatPrice
     * @return
     */
    public boolean crossesAt(BigDecimal thatPrice) {
        if (getSide() == Side.BUY) {
            return this.getPrice().compareTo(thatPrice) >= 0;
        } else {
            return this.getPrice().compareTo(thatPrice) <= 0;
        }
    }

    /**
     * 成交时调用：增加当前订单已成交数量 deal_volume
     *
     * @param tradedQuantity
     * @return
     */
    public void addFilledQuntity(BigDecimal tradedQuantity) {
        // todo 暂时未发现问题，直接修改原订单属性，不clone新订单了
        Preconditions.checkArgument(!tradedQuantity.equals(BigDecimal.ZERO), "cannot decrease by zero");
        this.dealVolume = this.dealVolume.add(tradedQuantity);
    }

    /**
     * 成交时调用：增加当前订单已成交金额 deal_money
     *
     * @param tradedQuoteAmount
     * @return
     */
    public void addFilledMoney(BigDecimal tradedQuoteAmount) {
        Preconditions.checkArgument(!tradedQuoteAmount.equals(BigDecimal.ZERO), "cannot decrease by zero");
        this.dealMoney = this.dealMoney.add(tradedQuoteAmount);
    }
}

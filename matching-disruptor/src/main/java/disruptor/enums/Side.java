package disruptor.enums;

import com.google.common.base.Preconditions;
import org.apache.logging.log4j.util.Strings;

/**
 * @Author: X-Men
 * @CreateDate: 2019-10-28 00:32
 */
public enum Side {
    /**
     * 买单方向
     */
    BUY,

    /**
     * 卖单方向
     */
    SELL
    ;

    public static Side fromString(String side) {
        Preconditions.checkArgument(!Strings.isBlank(side), "side must not be null or empty");
        String upperCaseSide = side.toUpperCase();
        Preconditions.checkArgument(
                upperCaseSide.equals(Side.BUY.toString()) ||
                        upperCaseSide.equals(Side.SELL.toString()), "side must be BUY or SELL");

        return Side.valueOf(upperCaseSide);
    }

    public static Side getOpposite(Side oneSide) {
        return Side.BUY.equals(oneSide) ? Side.SELL : Side.BUY;
    }
}

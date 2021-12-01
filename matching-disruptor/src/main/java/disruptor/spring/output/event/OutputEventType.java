package disruptor.spring.output.event;

public enum OutputEventType {

    //异常
    ORDER_EXCEPTION,

    //没有任何成交的订单进入深度
    ORDER_DEPTH,
    //订单取消
    ORDER_CANCELLED,
    //重建全量深度
    REBUILD_DEPTH,
    //正常成交
    ORDER_TRADED,

    //退残渣：限价单更优价残渣，市价单残渣
    ORDER_REMAIN,

    REBUILD_TRADE,

    //流水线处理异常，设置为EXCEPTION的事件下游处理器不再可用
    EXCEPTION,
}

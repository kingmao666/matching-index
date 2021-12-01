package disruptor.spring.output.event;

import lombok.Data;

@Data
public class OutputEvent {

    /**
     * 输入事件类型。发送不同的消息
     */
    private OutputEventType outputEventType;

    /**
     * 流水线处理日志
     */
    private String flowLog = "";

    /**
     * 当前线程币对
     */
    private String symbol;

}

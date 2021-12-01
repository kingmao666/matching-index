package disruptor.quickstart;

import lombok.Data;

@Data
public class OrderEvent {

	private Integer id;

	private long value; //订单的价格

}

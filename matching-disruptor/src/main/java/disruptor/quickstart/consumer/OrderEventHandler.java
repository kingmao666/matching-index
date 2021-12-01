package disruptor.quickstart.consumer;

import com.lmax.disruptor.EventHandler;
import disruptor.quickstart.OrderEvent;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class OrderEventHandler implements EventHandler<OrderEvent> {


    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {

        log.info("接收到 event = {}", event.toString());

        log.info("sequence = {}", sequence);

        log.info("endOfBatch = {}", endOfBatch);

    }
}

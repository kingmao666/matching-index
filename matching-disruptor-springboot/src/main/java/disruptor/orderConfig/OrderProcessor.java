package disruptor.orderConfig;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventProcessor;
import com.lmax.disruptor.Sequence;

public class OrderProcessor implements EventHandler<OrderEvent> {


    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {

    }
}

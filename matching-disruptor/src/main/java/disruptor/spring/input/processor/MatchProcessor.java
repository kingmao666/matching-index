package disruptor.spring.input.processor;

import com.lmax.disruptor.EventHandler;
import disruptor.entity.Order;
import disruptor.spring.input.event.InputEvent;
import disruptor.spring.input.event.InputEventType;
import lombok.extern.log4j.Log4j2;

//@Log4j2
public class MatchProcessor implements EventHandler<InputEvent> {

    @Override
    public void onEvent(InputEvent event, long sequence, boolean endOfBatch) throws Exception {

        Long id = event.getExecutedOrder().getId();

        System.out.println("MatchProcessor 接受到请求event orderId=" + id + "message=" + event.getMessage());

        String symbol = event.getSymbol();
        Order executedOrder = event.getExecutedOrder();
        InputEventType inputEventType = event.getInputEventType();

       /* if (id == 10L) {
            System.out.println(event.getMessage());
        }*/
        //Thread.sleep(1000L);

    }
}

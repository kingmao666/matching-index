package disruptor.spring.input.processor;

import com.lmax.disruptor.EventHandler;
import disruptor.spring.input.event.InputEvent;
import lombok.extern.log4j.Log4j2;

//@Log4j2
public class ValidateProcessor implements EventHandler<InputEvent> {

    @Override
    public void onEvent(InputEvent event, long sequence, boolean endOfBatch) throws Exception {

        //Thread.sleep(1000L);

        //前置校验
        Long id = event.getExecutedOrder().getId();
        System.out.println("ValidateProcessor 收到event orderId=" + id);

        //Thread.sleep(1000L);



    }
}

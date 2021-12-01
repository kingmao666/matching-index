package disruptor.spring;

import com.lmax.disruptor.dsl.Disruptor;
import disruptor.entity.Order;
import disruptor.spring.input.event.InputEvent;
import disruptor.spring.input.event.InputEventFactory;
import disruptor.spring.input.processor.MatchProcessor;
import disruptor.spring.input.processor.ValidateProcessor;
import disruptor.spring.input.translator.InputEventTranslator;
import org.junit.Test;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ThreadFactory;

public class MainTest2 {

    @Test
    public void t1() {

        Disruptor<InputEvent> disruptor = makeDisruptor();

        for (long i = 0; i < 100; i++) {
            Order order = new Order();
            order.setId(i);
            order.setSymbol("BTCUSDT");
            //InputEventType inputEventType = InputEventType.NORMAL;

            InputEvent inputEvent = new InputEvent();
            inputEvent.setExecutedOrder(order);
            inputEvent.setSymbol(order.getSymbol());
            disruptor.publishEvent(new InputEventTranslator(inputEvent));
        }
    }

    public Disruptor<InputEvent> makeDisruptor(){

        //InputEventFactory inputEventFactory = new InputEventFactory();

        ThreadFactory customizableThreadFactory = new CustomizableThreadFactory("aaaaaaa");

        Disruptor<InputEvent> disruptor = new Disruptor<>(new InputEventFactory(),
                1024,
                customizableThreadFactory);

        disruptor.handleEventsWith(new ValidateProcessor()).then(new MatchProcessor());
        disruptor.start();

        return disruptor;
    }

}
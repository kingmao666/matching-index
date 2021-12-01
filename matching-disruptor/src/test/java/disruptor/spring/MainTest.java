package disruptor.spring;


import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import disruptor.entity.Order;
import disruptor.spring.input.event.InputEvent;
import disruptor.spring.input.event.InputEventFactory;
import disruptor.spring.input.event.InputEventType;
import disruptor.spring.input.processor.MatchProcessor;
import disruptor.spring.input.processor.ValidateProcessor;
import disruptor.spring.input.publisher.InputEventPublisher;
import disruptor.spring.input.translator.InputEventTranslator;
import org.anair.disruptor.DefaultDisruptorConfig;
import org.anair.disruptor.EventHandlerChain;
import org.anair.disruptor.WaitStrategyType;
import org.junit.Test;

import java.util.concurrent.Executors;

public class MainTest {

    @Test
    public void t1() {

        DefaultDisruptorConfig<InputEvent> defaultDisruptorConfig = makeDefaultDisruptorConfig();

        for (long i = 0; i < 100; i++) {
            Order order = new Order();
            order.setId(i);
            order.setSymbol("BTCUSDT");
            //InputEventType inputEventType = InputEventType.NORMAL;

            InputEvent inputEvent = new InputEvent();
            inputEvent.setExecutedOrder(order);
            inputEvent.setSymbol(order.getSymbol());

            defaultDisruptorConfig.publish(new InputEventTranslator(inputEvent));
        }
    }

    public DefaultDisruptorConfig<InputEvent> makeDefaultDisruptorConfig(){

        EventHandlerChain<InputEvent> eventHandlerChain = new EventHandlerChain(new EventHandler[]{new ValidateProcessor()}, new EventHandler[]{new MatchProcessor()});

        DefaultDisruptorConfig<InputEvent> defaultDisruptorConfig = new DefaultDisruptorConfig<>();

        defaultDisruptorConfig.setEventHandlerChain(new EventHandlerChain[]{eventHandlerChain});

        defaultDisruptorConfig.setProducerType(ProducerType.SINGLE);

        defaultDisruptorConfig.setRingBufferSize(1024);

        defaultDisruptorConfig.setThreadName("Input_".concat("BTCUSDT"));

        defaultDisruptorConfig.setEventFactory(new InputEventFactory());

        defaultDisruptorConfig.setWaitStrategyType(WaitStrategyType.BLOCKING);

        defaultDisruptorConfig.init();

        return defaultDisruptorConfig;
    }


}
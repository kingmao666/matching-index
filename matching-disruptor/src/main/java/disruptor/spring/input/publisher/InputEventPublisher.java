package disruptor.spring.input.publisher;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import disruptor.spring.input.event.InputEvent;
import disruptor.spring.input.event.InputEventFactory;
import disruptor.spring.input.processor.MatchProcessor;
import disruptor.spring.input.processor.ValidateProcessor;
import disruptor.spring.input.translator.InputEventTranslator;
import org.anair.disruptor.DefaultDisruptorConfig;
import org.anair.disruptor.EventHandlerChain;
import org.anair.disruptor.WaitStrategyType;

import java.util.concurrent.Executors;

public class InputEventPublisher {

    public void publish(InputEvent inputEvent){

        /**订单输入处理链*/
        EventHandlerChain<InputEvent> eventHandlerChain = new EventHandlerChain(new EventHandler[]{new ValidateProcessor()}, new EventHandler[]{new MatchProcessor()});

        DefaultDisruptorConfig<InputEvent> defaultDisruptorConfig = new DefaultDisruptorConfig<>();

        defaultDisruptorConfig.setEventHandlerChain(new EventHandlerChain[]{eventHandlerChain});

        defaultDisruptorConfig.setProducerType(ProducerType.SINGLE);

        defaultDisruptorConfig.setRingBufferSize(1024);

        defaultDisruptorConfig.setThreadName("Input_".concat(inputEvent.getSymbol()));

        defaultDisruptorConfig.setEventFactory(new InputEventFactory());

        defaultDisruptorConfig.setWaitStrategyType(WaitStrategyType.BLOCKING);

        defaultDisruptorConfig.init();

        defaultDisruptorConfig.publish(new InputEventTranslator(inputEvent));

    }


    public void publish2(InputEvent inputEvent){

        InputEventFactory inputEventFactory = new InputEventFactory();

        Disruptor<InputEvent> disruptor = new Disruptor<>(inputEventFactory, 1024, Executors.defaultThreadFactory());

        disruptor.handleEventsWith(new ValidateProcessor()).handleEventsWith(new MatchProcessor());

        disruptor.publishEvent(new InputEventTranslator(inputEvent));

    }
}

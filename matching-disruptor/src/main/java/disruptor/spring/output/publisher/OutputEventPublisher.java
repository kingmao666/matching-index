package disruptor.spring.output.publisher;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.dsl.ProducerType;
import disruptor.spring.input.processor.MatchProcessor;
import disruptor.spring.output.event.OutputEvent;
import disruptor.spring.output.event.OutputEventFactory;
import disruptor.spring.output.processor.MessageProcessor;
import disruptor.spring.output.translator.OutputEventTranslator;
import org.anair.disruptor.DefaultDisruptorConfig;
import org.anair.disruptor.EventHandlerChain;
import org.anair.disruptor.WaitStrategyType;

public class OutputEventPublisher {

    public void publish(OutputEvent outputEvent){

        /**订单输入处理链*/
        EventHandlerChain<OutputEvent> eventHandlerChain = new EventHandlerChain(new EventHandler[]{new MatchProcessor()}, new EventHandler[]{new MessageProcessor()});

        DefaultDisruptorConfig<OutputEvent> defaultDisruptorConfig = new DefaultDisruptorConfig<>();

        defaultDisruptorConfig.setEventHandlerChain(new EventHandlerChain[]{eventHandlerChain});

        defaultDisruptorConfig.setProducerType(ProducerType.SINGLE);

        defaultDisruptorConfig.setRingBufferSize(1024);

        defaultDisruptorConfig.setThreadName("Output_".concat(outputEvent.getSymbol()));

        defaultDisruptorConfig.setEventFactory(new OutputEventFactory());

        defaultDisruptorConfig.setWaitStrategyType(WaitStrategyType.BLOCKING);

        defaultDisruptorConfig.init();

        defaultDisruptorConfig.publish(new OutputEventTranslator(outputEvent));



    }
}

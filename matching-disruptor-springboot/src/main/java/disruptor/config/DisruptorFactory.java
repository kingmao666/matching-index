package disruptor.config;

import com.lmax.disruptor.dsl.Disruptor;
import disruptor.orderConfig.OrderEvent;
import disruptor.orderConfig.OrderEventFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadFactory;

@Component
public class DisruptorFactory {

    private static final int ringbuffersize = 1024;


}

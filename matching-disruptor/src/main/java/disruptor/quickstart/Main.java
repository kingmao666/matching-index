package disruptor.quickstart;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import disruptor.quickstart.consumer.OrderEventHandler;
import disruptor.quickstart.producer.OrderEventProducer;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;

public class Main {


    public static void main(String[] args) {

        OrderEventFactory orderEventFactory = new OrderEventFactory();

        int ringBufferSize = 1024;

        //ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Disruptor<OrderEvent> orderDisruptor = new Disruptor<>(orderEventFactory, ringBufferSize, Executors.defaultThreadFactory());

        orderDisruptor.handleEventsWith(new OrderEventHandler());

        orderDisruptor.start();

        RingBuffer<OrderEvent> ringBuffer = orderDisruptor.getRingBuffer();
        OrderEventProducer orderProducer = new OrderEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);

        for(long i = 0 ; i < 5; i ++){
            bb.putLong(0, i);
            orderProducer.send(bb);

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        orderDisruptor.shutdown();

    }
}

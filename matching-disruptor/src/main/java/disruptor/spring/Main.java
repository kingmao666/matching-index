package disruptor.spring;

import disruptor.entity.Order;
import disruptor.spring.input.event.InputEvent;
import disruptor.spring.input.event.InputEventType;
import disruptor.spring.input.publisher.InputEventPublisher;

public class Main {


    public static void main(String[] args) {
        InputEventPublisher inputEventPublisher = new InputEventPublisher();
        for (long i = 0; i < 100; i++) {
            Order order = new Order();
            order.setId(i);

            order.setSymbol("BTCUSDT");

            InputEventType inputEventType = InputEventType.NORMAL;

            InputEvent inputEvent = new InputEvent();
            inputEvent.setExecutedOrder(order);
            inputEvent.setSymbol(order.getSymbol());
            inputEventPublisher.publish(inputEvent);
        }
    }
}

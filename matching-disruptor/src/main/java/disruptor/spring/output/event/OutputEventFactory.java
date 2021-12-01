package disruptor.spring.output.event;

import com.lmax.disruptor.EventFactory;

public class OutputEventFactory implements EventFactory<OutputEvent> {

    @Override
    public OutputEvent newInstance() {
        return new OutputEvent();
    }
}

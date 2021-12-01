package disruptor.spring.output.processor;


import com.lmax.disruptor.EventHandler;
import disruptor.spring.output.event.OutputEvent;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MessageProcessor implements EventHandler<OutputEvent> {


    @Override
    public void onEvent(OutputEvent outputEvent, long sequence, boolean endOfBatch) throws Exception {

    }
}

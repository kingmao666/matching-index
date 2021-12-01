package disruptor.spring.output.translator;

import com.lmax.disruptor.EventTranslator;
import disruptor.spring.output.event.OutputEvent;

public class OutputEventTranslator implements EventTranslator<OutputEvent> {
    private OutputEvent outputEvent;

    public OutputEventTranslator(OutputEvent outputEvent){
        this.outputEvent = outputEvent;
    }

    @Override
    public void translateTo(OutputEvent outputEvent, long sequence) {
        outputEvent.setFlowLog(this.outputEvent.getFlowLog());
        outputEvent.setSymbol(this.outputEvent.getSymbol());
        outputEvent.setOutputEventType(this.outputEvent.getOutputEventType());
    }
}

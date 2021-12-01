package disruptor.spring.input.translator;

import com.lmax.disruptor.EventTranslator;
import disruptor.spring.input.event.InputEvent;

public class InputEventTranslator implements EventTranslator<InputEvent> {

    private final InputEvent inputEvent;

    public InputEventTranslator(InputEvent inputEvent){
        this.inputEvent = inputEvent;
    }

    @Override
    public void translateTo(InputEvent inputEvent, long sequence) {
        inputEvent.clean();
        inputEvent.setExecutedOrder(this.inputEvent.getExecutedOrder());
        inputEvent.setFlowLog(this.inputEvent.getFlowLog());
        inputEvent.setMessage(this.inputEvent.getMessage());
        inputEvent.setSymbol(this.inputEvent.getSymbol());
        inputEvent.setInputEventType(this.inputEvent.getInputEventType());
    }
}

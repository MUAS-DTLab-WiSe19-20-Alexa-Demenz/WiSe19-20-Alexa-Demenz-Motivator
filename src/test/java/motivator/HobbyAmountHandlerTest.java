package motivator;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import motivator.handler.ourhandlers.HobbyAmountHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class HobbyAmountHandlerTest {

    private HobbyAmountHandler handler;

    @Before
    public void setup() {
        handler = new HobbyAmountHandler();
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));

    }

    @Test
    public void testHandle() {
//        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
//        when(inputMock.matches(any())).thenReturn(true);

        // no hobby saved yet
        Response response = TestHelp.noAttributesTestForHandle(handler);
        assertTrue(response.getOutputSpeech().toString().contains(String.format(PhrasesAndConstants.AMOUNT_SAVED, 0)));

        Map<String, Object> persistentAttributes = new HashMap<>();
        persistentAttributes.put("zumba", "name: zumba; inside: true; minutes: 30");

        response = TestHelp.testForHandle(handler, persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(String.format(PhrasesAndConstants.AMOUNT_SAVED, persistentAttributes.size())));
    }

}


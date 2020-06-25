package motivator;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import motivator.handler.ourhandlers.InformationHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class InformationHandlerTest {
    private InformationHandler handler;

    @Before
    public void setup() {
        handler = new InformationHandler();
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    @Test
    public void testHandle() {
        Response response = TestHelp.testForHandle(handler, "eis essen", PhrasesAndConstants.ACTIVITY_SLOT, null);
        assertTrue(response.getOutputSpeech().toString().contains(PhrasesAndConstants.NO_ACTIVITIES_YET));

        Map<String, Object> persistentAttributes = new HashMap<>();
        persistentAttributes.put("zumba", "name: zumba; inside: true; minutes: 30");

        // no hobby by that name
        response = TestHelp.testForHandle(handler, "eis essen", PhrasesAndConstants.ACTIVITY_SLOT, persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(PhrasesAndConstants.NO_HOBBY_BY_THAT_NAME));

        // no hobby by that name
        response = TestHelp.testForHandle(handler, "zumba", PhrasesAndConstants.ACTIVITY_SLOT, persistentAttributes);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(PhrasesAndConstants.INFORMATION_NAME, "zumba"));
        sb.append(String.format(PhrasesAndConstants.INFORMATION_DURATION, 30));
        sb.append(String.format(PhrasesAndConstants.INFORMATION_PLACE, "drinnen"));

        assertTrue(response.getOutputSpeech().toString().contains(sb.toString()));

        persistentAttributes.put("laufen", "name: laufen; inside: false; minutes: 30");

        sb.delete(0, sb.toString().length());
        sb.append(String.format(PhrasesAndConstants.INFORMATION_NAME, "laufen"));
        sb.append(String.format(PhrasesAndConstants.INFORMATION_DURATION, 30));
        sb.append(String.format(PhrasesAndConstants.INFORMATION_PLACE, "draußen"));

        response = TestHelp.testForHandle(handler, "laufen", PhrasesAndConstants.ACTIVITY_SLOT, persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(sb.toString()));
    }
}
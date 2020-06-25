package motivator;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import motivator.handler.ourhandlers.GetAllActivitiesHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GetAllActivitiesHandlerTest {

    private GetAllActivitiesHandler handler;

    @Before
    public void setup() {
        handler = new GetAllActivitiesHandler();
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
        assertTrue(response.getOutputSpeech().toString().contains(PhrasesAndConstants.NO_ACTIVITIES_YET));

        Map<String, Object> persistentAttributes = new HashMap<>();
        persistentAttributes.put("zumba", "name: zumba; inside: true; minutes: 30");

        // one hobby
        response = TestHelp.testForHandle(handler, persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(String.format(PhrasesAndConstants.ACTIVITIES_OUTPUT, "zumba ")));

        // more hobbies
        persistentAttributes.put("billard", "name: billard; inside: true; minutes: 30");
        response = TestHelp.testForHandle(handler, persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(String.format(PhrasesAndConstants.ACTIVITIES_OUTPUT, "billard zumba ")));
    }

}


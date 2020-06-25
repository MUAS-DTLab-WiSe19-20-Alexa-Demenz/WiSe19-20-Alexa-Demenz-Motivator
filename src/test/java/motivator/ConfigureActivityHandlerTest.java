package motivator;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import motivator.handler.ourhandlers.ConfigureActivityHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ConfigureActivityHandlerTest {

    private ConfigureActivityHandler handler;

    @Before
    public void setup() {
        handler = new ConfigureActivityHandler();
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
        Response response = TestHelp.testForHandle(handler,
                "eis essen", PhrasesAndConstants.ACTIVITY_SLOT,
                "sport", PhrasesAndConstants.ACTIVITY_NEW_SLOT,
                null, PhrasesAndConstants.PLACE_SLOT,
                null, PhrasesAndConstants.NUMBER_SLOT,
                null);
        assertTrue(response.getOutputSpeech().toString().contains(PhrasesAndConstants.NO_ACTIVITIES_YET));

        Map<String, Object> persistentAttributes = new HashMap<>();
        persistentAttributes.put("zumba", "name: zumba; inside: true; minutes: 30");

        // no hobby by that name
        response = TestHelp.testForHandle(handler,
                "eis essen", PhrasesAndConstants.ACTIVITY_SLOT,
                "sport", PhrasesAndConstants.ACTIVITY_NEW_SLOT,
                null, PhrasesAndConstants.PLACE_SLOT,
                null, PhrasesAndConstants.NUMBER_SLOT,
                persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(PhrasesAndConstants.NO_HOBBY_BY_THAT_NAME));

        // change Place
        response = TestHelp.testForHandle(handler,
                "zumba", PhrasesAndConstants.ACTIVITY_SLOT,
                null, PhrasesAndConstants.ACTIVITY_NEW_SLOT,
                "draußen", PhrasesAndConstants.PLACE_SLOT,
                null, PhrasesAndConstants.NUMBER_SLOT,
                persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(String.format(PhrasesAndConstants.CHANGE_SUCCESSFULL, "zumba")));

        // change duration
        response = TestHelp.testForHandle(handler,
                "zumba", PhrasesAndConstants.ACTIVITY_SLOT,
                null, PhrasesAndConstants.ACTIVITY_NEW_SLOT,
                null, PhrasesAndConstants.PLACE_SLOT,
                "5", PhrasesAndConstants.NUMBER_SLOT,
                persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(String.format(PhrasesAndConstants.CHANGE_SUCCESSFULL, "zumba")));

        // change name
        response = TestHelp.testForHandle(handler,
                "zumba", PhrasesAndConstants.ACTIVITY_SLOT,
                "sport", PhrasesAndConstants.ACTIVITY_NEW_SLOT,
                null, PhrasesAndConstants.PLACE_SLOT,
                null, PhrasesAndConstants.NUMBER_SLOT,
                persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(String.format(PhrasesAndConstants.CHANGE_SUCCESSFULL, "sport")));

    }

}


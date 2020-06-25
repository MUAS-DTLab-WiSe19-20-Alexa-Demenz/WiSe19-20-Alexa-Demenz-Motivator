package motivator;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import motivator.handler.ourhandlers.DeleteActivityHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DeleteActivityHandlerTest {

    private DeleteActivityHandler handler;

    @Before
    public void setup() {
        handler = new DeleteActivityHandler();
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
                null);
        assertTrue(response.getOutputSpeech().toString().contains(PhrasesAndConstants.NO_ACTIVITIES_YET));

        Map<String, Object> persistentAttributes = new HashMap<>();
        persistentAttributes.put("zumba", "name: zumba; inside: true; minutes: 30");

        // no hobby by that name
        response = TestHelp.testForHandle(handler,
                "billard", PhrasesAndConstants.ACTIVITY_SLOT,
                persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(PhrasesAndConstants.NO_HOBBY_BY_THAT_NAME));

        // delete successful
        response = TestHelp.testForHandle(handler,
                "zumba", PhrasesAndConstants.ACTIVITY_SLOT,
                persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(String.format(PhrasesAndConstants.DELETED_SUCCESSFULL, "zumba")));


        persistentAttributes.put("sport", "name: sport; inside: true; minutes: 30");
        response = TestHelp.testForHandle(handler,
                "alle", PhrasesAndConstants.ACTIVITY_SLOT,
                persistentAttributes);
        assertTrue(response.getOutputSpeech().toString().contains(String.format(PhrasesAndConstants.DELETED_ALL_SUCCESSFULL, "alle")));
    }

}


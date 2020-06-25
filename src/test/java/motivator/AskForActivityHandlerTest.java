package motivator;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import motivator.handler.ourhandlers.AskForActivityHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AskForActivityHandlerTest {

	private AskForActivityHandler handler;

	@Before
	public void setup() {
		handler = new AskForActivityHandler();
	}

	@Test
	public void testCanHandle() {
		final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
		when(inputMock.matches(any())).thenReturn(true);
		assertTrue(handler.canHandle(inputMock));
	}

	@Test
	public void testHandle() {
//		final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
//		when(inputMock.matches(any())).thenReturn(true);

		Response response = TestHelp.testForHandle(handler, null);
		assertTrue(response.getOutputSpeech().toString().contains(PhrasesAndConstants.NO_ACTIVITIES_YET));

		Map<String, Object> persistentAttributes = new HashMap<>();
		persistentAttributes.put("eis essen", "name: eis essen; inside: true; minutes: 30");

		response = TestHelp.testForHandle(handler, "eis essen", PhrasesAndConstants.ACTIVITY_SLOT, persistentAttributes);
		assertTrue(response.getOutputSpeech().toString().contains(String.format(PhrasesAndConstants.PROPOSED_HOBBY, "eis essen")));

		persistentAttributes.put("eis essen", "name: sport; inside: true; minutes: 30");

		response = TestHelp.testForHandle(handler, "sport", PhrasesAndConstants.ACTIVITY_SLOT, persistentAttributes);
		assertTrue(response.getOutputSpeech().toString().contains(String.format(PhrasesAndConstants.PROPOSED_HOBBY, "sport")));
	}
}

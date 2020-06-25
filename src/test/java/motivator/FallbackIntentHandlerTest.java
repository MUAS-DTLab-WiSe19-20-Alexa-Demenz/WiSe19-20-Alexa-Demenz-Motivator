package motivator;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import motivator.handler.alexahandlers.FallbackIntentHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FallbackIntentHandlerTest {

	private FallbackIntentHandler handler;

	@Before
	public void setup() {
		handler = new FallbackIntentHandler();
	}

	@Test
	public void testCanHandle() {
		final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
		when(inputMock.matches(any())).thenReturn(true);
		assertTrue(handler.canHandle(inputMock));
	}

	@Test
	public void testHandle() {
		final Response response = TestHelp.noAttributesTestForHandle(handler);
		assertTrue(response.getOutputSpeech().toString().contains(PhrasesAndConstants.FALLBACK));
	}


}

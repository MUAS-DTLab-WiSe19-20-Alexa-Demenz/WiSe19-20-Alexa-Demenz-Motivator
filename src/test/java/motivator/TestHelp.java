package motivator;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class TestHelp {

    public static Response testForHandle(RequestHandler handler,
                                         Map<String, Object> persistentAttributes) {

        if (persistentAttributes == null)
            persistentAttributes = new HashMap<>();

        final AttributesManager attributesManagerMock = Mockito.mock(AttributesManager.class);
        when(attributesManagerMock.getPersistentAttributes()).thenReturn(persistentAttributes);

        // Mock Slots
        final RequestEnvelope requestEnvelopeMock = RequestEnvelope.builder()
                .withRequest(IntentRequest.builder()
                        .withIntent(Intent.builder()
                                .build())
                        .build())
                .build();

        // Mock Handler input attributes
        final HandlerInput input = Mockito.mock(HandlerInput.class);
        when(input.getAttributesManager()).thenReturn(attributesManagerMock);
        when(input.getResponseBuilder()).thenReturn(new ResponseBuilder());
        when(input.getRequestEnvelope()).thenReturn(requestEnvelopeMock);
        when(input.getRequest()).thenReturn(requestEnvelopeMock.getRequest());

        final Optional<Response> res = handler.handle(input);
        assertTrue(res.isPresent());

        final Response response = res.get();
        assertNotEquals("Test", response.getReprompt());
        assertNotNull(response.getOutputSpeech());
        return response;

    }

    public static Response testForHandle(RequestHandler handler,
                                         String entry,
                                         String slotName,
                                         Map<String, Object> persistentAttributes) {

        if (persistentAttributes == null)
            persistentAttributes = new HashMap<>();

        final AttributesManager attributesManagerMock = Mockito.mock(AttributesManager.class);
        when(attributesManagerMock.getPersistentAttributes()).thenReturn(persistentAttributes);

        // Mock Slots
        final RequestEnvelope requestEnvelopeMock = RequestEnvelope.builder()
                .withRequest(IntentRequest.builder()
                        .withIntent(Intent.builder()
                                .putSlotsItem(slotName, Slot.builder()
                                        .withName(slotName)
                                        .withValue(entry)
                                        .build())
                                .build())
                        .build())
                .build();

        // Mock Handler input attributes
        final HandlerInput input = Mockito.mock(HandlerInput.class);
        when(input.getAttributesManager()).thenReturn(attributesManagerMock);
        when(input.getResponseBuilder()).thenReturn(new ResponseBuilder());
        when(input.getRequestEnvelope()).thenReturn(requestEnvelopeMock);
        when(input.getRequest()).thenReturn(requestEnvelopeMock.getRequest());

        final Optional<Response> res = handler.handle(input);
        assertTrue(res.isPresent());

        final Response response = res.get();
        assertNotEquals("Test", response.getReprompt());
        assertNotNull(response.getOutputSpeech());
        return response;

    }

    public static Response testForHandle(RequestHandler handler,
                                         String entry1, String slotName1,
                                         String entry2, String slotName2,
                                         Map<String, Object> persistentAttributes) {

        if (persistentAttributes == null)
            persistentAttributes = new HashMap<>();

        final AttributesManager attributesManagerMock = Mockito.mock(AttributesManager.class);
        when(attributesManagerMock.getPersistentAttributes()).thenReturn(persistentAttributes);

        // Mock Slots
        final RequestEnvelope requestEnvelopeMock = RequestEnvelope.builder()
                .withRequest(IntentRequest.builder()
                        .withIntent(Intent.builder()
                                .putSlotsItem(slotName1, Slot.builder()
                                        .withName(slotName1)
                                        .withValue(entry1)
                                        .build())
                                .putSlotsItem(slotName2, Slot.builder()
                                        .withName(slotName2)
                                        .withValue(entry2)
                                        .build())
                                .build())
                        .build())
                .build();

        // Mock Handler input attributes
        final HandlerInput input = Mockito.mock(HandlerInput.class);
        when(input.getAttributesManager()).thenReturn(attributesManagerMock);
        when(input.getResponseBuilder()).thenReturn(new ResponseBuilder());
        when(input.getRequestEnvelope()).thenReturn(requestEnvelopeMock);
        when(input.getRequest()).thenReturn(requestEnvelopeMock.getRequest());

        final Optional<Response> res = handler.handle(input);
        assertTrue(res.isPresent());

        final Response response = res.get();
        assertNotEquals("Test", response.getReprompt());
        assertNotNull(response.getOutputSpeech());
        return response;
    }

    public static Response testForHandle(RequestHandler handler,
                                         String entry1, String slotName1,
                                         String entry2, String slotName2,
                                         String entry3, String slotName3,
                                         String entry4, String slotName4,
                                         Map<String, Object> persistentAttributes) {

        if (persistentAttributes == null)
            persistentAttributes = new HashMap<>();

        final AttributesManager attributesManagerMock = Mockito.mock(AttributesManager.class);
        when(attributesManagerMock.getPersistentAttributes()).thenReturn(persistentAttributes);

        // Mock Slots
        final RequestEnvelope requestEnvelopeMock = RequestEnvelope.builder()
                .withRequest(IntentRequest.builder()
                        .withIntent(Intent.builder()
                                .putSlotsItem(slotName1, Slot.builder()
                                        .withName(slotName1)
                                        .withValue(entry1)
                                        .build())
                                .putSlotsItem(slotName2, Slot.builder()
                                        .withName(slotName2)
                                        .withValue(entry2)
                                        .build())
                                .putSlotsItem(slotName3, Slot.builder()
                                        .withName(slotName3)
                                        .withValue(entry3)
                                        .build())
                                .putSlotsItem(slotName4, Slot.builder()
                                        .withName(slotName4)
                                        .withValue(entry4)
                                        .build())
                                .build())
                        .build())
                .build();

        // Mock Handler input attributes
        final HandlerInput input = Mockito.mock(HandlerInput.class);
        when(input.getAttributesManager()).thenReturn(attributesManagerMock);
        when(input.getResponseBuilder()).thenReturn(new ResponseBuilder());
        when(input.getRequestEnvelope()).thenReturn(requestEnvelopeMock);
        when(input.getRequest()).thenReturn(requestEnvelopeMock.getRequest());

        final Optional<Response> res = handler.handle(input);
        assertTrue(res.isPresent());

        final Response response = res.get();
        assertNotEquals("Test", response.getReprompt());
        assertNotNull(response.getOutputSpeech());
        return response;
    }

    public static HandlerInput mockHandlerInput(String entry,
                                                String slotName,
                                                Map<String, Object> persistentAttributes) {

        final AttributesManager attributesManagerMock = Mockito.mock(AttributesManager.class);
        when(attributesManagerMock.getPersistentAttributes()).thenReturn(persistentAttributes);

        // Mock Slots
        final RequestEnvelope requestEnvelopeMock = RequestEnvelope.builder()
                .withRequest(IntentRequest.builder()
                        .withIntent(Intent.builder()
                                .putSlotsItem(slotName, Slot.builder()
                                        .withName(slotName)
                                        .withValue(entry)
                                        .build())
                                .build())
                        .build())
                .build();

        // Mock Handler input attributes
        final HandlerInput input = Mockito.mock(HandlerInput.class);
        when(input.getAttributesManager()).thenReturn(attributesManagerMock);
        when(input.getResponseBuilder()).thenReturn(new ResponseBuilder());
        when(input.getRequestEnvelope()).thenReturn(requestEnvelopeMock);
        return input;
    }

//    public static Response standardTestForHandle(RequestHandler handler) {
//        final Map<String, Object> sessionAttributes = new HashMap<>();
//        final Map<String, Object> persistentAttributes = new HashMap<>();
//        sessionAttributes.put(PhrasesAndConstants.ENTRYTEXT_KEY_SESSION, "Test");
//        persistentAttributes.put(PhrasesAndConstants.ENTRY_KEY_PERSISTANT, "Test");
//        final HandlerInput inputMock = TestHelp.mockHandlerInput(null, PhrasesAndConstants.ENTRY_SLOT, sessionAttributes, persistentAttributes, null, null);
//        final Optional<Response> res = handler.handle(inputMock);
//        assertTrue(res.isPresent());
//        final Response response = res.get();
//        //assertFalse(response.getShouldEndSession());
//        assertNotEquals("Test", response.getReprompt());
//        assertNotNull(response.getOutputSpeech());
//        return response;
//    }

//    public static Response entryTestForHandle(RequestHandler handler, String testEntry) {
//        final Map<String, Object> sessionAttributes = new HashMap<>();
//        final Map<String, Object> persistentAttributes = new HashMap<>();
//        sessionAttributes.put(PhrasesAndConstants.ENTRYTEXT_KEY_SESSION, "Test");
//        persistentAttributes.put(PhrasesAndConstants.ENTRY_KEY_PERSISTANT, "Test");
//        final HandlerInput inputMock = TestHelp.mockHandlerInput(testEntry, PhrasesAndConstants.ENTRY_SLOT, sessionAttributes, persistentAttributes, null, null);
//        final Optional<Response> res = handler.handle(inputMock);
//        assertTrue(res.isPresent());
//        final Response response = res.get();
//        //assertFalse(response.getShouldEndSession());
//        assertNotEquals("Test", response.getReprompt());
//        assertNotNull(response.getOutputSpeech());
//        return response;
//    }

    public static Response sessionAttributesTestForHandle(RequestHandler handler, String conf) {
        final HandlerInput inputMock = TestHelp.mockHandlerInput(null, PhrasesAndConstants.ACTIVITY_SLOT, null);
        final Optional<Response> res = handler.handle(inputMock);
        assertTrue(res.isPresent());
        final Response response = res.get();

        //in the WhatsMyColorIntentHandler
        //assertTrue(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        return response;
    }

    public static Response persistentAttributesTestForHandle(RequestHandler handler) {
        final Map<String, Object> persistentAttributes = new HashMap<>();
        final HandlerInput inputMock = TestHelp.mockHandlerInput(null, PhrasesAndConstants.ACTIVITY_SLOT, persistentAttributes);
        final Optional<Response> res = handler.handle(inputMock);
        assertTrue(res.isPresent());
        final Response response = res.get();

        //in the WhatsMyColorIntentHandler
        //assertTrue(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        return response;
    }

    public static Response noAttributesTestForHandle(RequestHandler handler) {
        final HandlerInput inputMock = TestHelp.mockHandlerInput(null, PhrasesAndConstants.ACTIVITY_SLOT, new HashMap<>());

        final Optional<Response> res = handler.handle(inputMock);
        assertTrue(res.isPresent());

        final Response response = res.get();
        assertFalse(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        return response;
    }

    public static Response sessionEndedTestForHandle(RequestHandler handler) {
        final HandlerInput inputMock = TestHelp.mockHandlerInput(null, PhrasesAndConstants.ACTIVITY_SLOT, null);

        final Optional<Response> res = handler.handle(inputMock);
        assertTrue(res.isPresent());

        final Response response = res.get();
        assertTrue(response.getShouldEndSession());
        return response;
    }
}

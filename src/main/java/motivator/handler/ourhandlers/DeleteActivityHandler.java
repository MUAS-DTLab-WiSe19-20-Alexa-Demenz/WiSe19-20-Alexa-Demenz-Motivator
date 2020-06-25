package motivator.handler.ourhandlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;
import motivator.PhrasesAndConstants;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class DeleteActivityHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("DeleteActivity"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        AttributesManager attributesManager = handlerInput.getAttributesManager();
        ResponseBuilder responseBuilder = handlerInput.getResponseBuilder();

        Request request = handlerInput.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();

        Slot activitySlot = slots.get(PhrasesAndConstants.ACTIVITY_SLOT);
        String activityValue = activitySlot.getValue();

        if (persistentAttributes.size() == 0) {
            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.CARDTEXT_DELETEACTIVITY)
                    .withSpeech(PhrasesAndConstants.NO_ACTIVITIES_YET)
                    .withShouldEndSession(false);
        } else if (activityValue.equals("alle")) {
            persistentAttributes.clear();

            attributesManager.setPersistentAttributes(persistentAttributes);
            attributesManager.savePersistentAttributes();

            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.CARDTEXT_DELETEACTIVITY)
                    .withSpeech(String.format(PhrasesAndConstants.DELETED_ALL_SUCCESSFULL, activityValue))
                    .withShouldEndSession(false);

        } else if (persistentAttributes.containsKey(activityValue)) {
            persistentAttributes.remove(activityValue);

            attributesManager.setPersistentAttributes(persistentAttributes);
            attributesManager.savePersistentAttributes();

            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.CARDTEXT_DELETEACTIVITY)
                    .withSpeech(String.format(PhrasesAndConstants.DELETED_SUCCESSFULL, activityValue))
                    .withShouldEndSession(false);
        } else {
            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.CARDTEXT_DELETEACTIVITY)
                    .withSpeech(PhrasesAndConstants.NO_HOBBY_BY_THAT_NAME)
                    .withShouldEndSession(false);
        }
        return responseBuilder.build();
    }
}

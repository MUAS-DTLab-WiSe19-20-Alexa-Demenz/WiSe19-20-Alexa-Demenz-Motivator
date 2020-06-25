package motivator.handler.ourhandlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;
import motivator.PhrasesAndConstants;
import motivator.model.Hobby;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class InformationHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("Information"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        ResponseBuilder responseBuilder = handlerInput.getResponseBuilder();
        AttributesManager attributesManager = handlerInput.getAttributesManager();
        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
        Request request = handlerInput.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot activitySlot = slots.get(PhrasesAndConstants.ACTIVITY_SLOT);

        String activityValue = activitySlot.getValue();
        StringBuilder sb = new StringBuilder();

        if (persistentAttributes.size() == 0) {

            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.CARDTEXT_INFORMATION)
                    .withSpeech(PhrasesAndConstants.NO_ACTIVITIES_YET)
                    .withShouldEndSession(false);

        } else if (persistentAttributes.containsKey(activityValue)) {

            Hobby hobby = new Hobby((String) persistentAttributes.get(activityValue));

            sb.append(String.format(PhrasesAndConstants.INFORMATION_NAME, hobby.getName()));
            sb.append(String.format(PhrasesAndConstants.INFORMATION_DURATION, hobby.getMinutes()));
            sb.append(String.format(PhrasesAndConstants.INFORMATION_PLACE, hobby.getInside() ? "drinnen" : "draußen"));

            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.CARDTEXT_INFORMATION)
                    .withSpeech(sb.toString())
                    .withShouldEndSession(false);
        } else {
            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.CARDTEXT_INFORMATION)
                    .withSpeech(PhrasesAndConstants.NO_HOBBY_BY_THAT_NAME)
                    .withShouldEndSession(false);
        }
        return responseBuilder.build();
    }
}


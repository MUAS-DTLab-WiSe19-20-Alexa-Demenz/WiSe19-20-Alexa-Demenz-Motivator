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

public class AddActivityHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AddActivity"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        // all necessary objects
        ResponseBuilder responseBuilder = handlerInput.getResponseBuilder();
        Request request = handlerInput.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        AttributesManager attributesManager = handlerInput.getAttributesManager();
        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();

        // get the activity slot (the hobby to be added)
        Slot activitySlot = slots.get(PhrasesAndConstants.ACTIVITY_SLOT);
        String activityValue = activitySlot.getValue();

        // if hobby already exists in Map
        if (persistentAttributes.containsKey(activityValue)) {
            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.CARDTEXT_ADDACTIVITY)
                    .withSpeech(PhrasesAndConstants.ALREADY_EXISTS)
                    .withShouldEndSession(false);
        } else {
            // create new Hobby and Hobbies
            Hobby newHobby = new Hobby();
            newHobby.setName(activityValue);

            // add this hobby to persistent Attributes
            persistentAttributes.put(newHobby.getName(), newHobby.toString());
            attributesManager.setPersistentAttributes(persistentAttributes);
            attributesManager.savePersistentAttributes();

            String answer = String.format(PhrasesAndConstants.ACTIVITY_ADDED, newHobby.getName());

            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.CARDTEXT_ADDACTIVITY)
                    .withSpeech(answer)
                    .withShouldEndSession(false);
        }

        return responseBuilder.build();
    }
}

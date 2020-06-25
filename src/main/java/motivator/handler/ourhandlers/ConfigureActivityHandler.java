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

public class ConfigureActivityHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("ConfigureActivity"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        // get all neccessary objects
        AttributesManager attributesManager = handlerInput.getAttributesManager();
        ResponseBuilder responseBuilder = handlerInput.getResponseBuilder();

        Request request = handlerInput.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();

        // get all slots (Activity, ActivityNew, Number, Place)
        Slot activitySlot = slots.get(PhrasesAndConstants.ACTIVITY_SLOT);
        String activityValue = activitySlot.getValue();

        Slot activityNewSlot = slots.get(PhrasesAndConstants.ACTIVITY_NEW_SLOT);
        String activityNewValue = activityNewSlot.getValue();

        String numberValue = null;

        Slot numberSlot = slots.get(PhrasesAndConstants.NUMBER_SLOT);
        numberValue = numberSlot.getValue();


        String placeValue = null;

        Slot placeSlot = slots.get(PhrasesAndConstants.PLACE_SLOT);
        placeValue = placeSlot.getValue();


        if (persistentAttributes.size() == 0) {
            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.CARDTEXT_CONFIGUREACTIVITY)
                    .withSpeech(PhrasesAndConstants.NO_ACTIVITIES_YET)
                    .withShouldEndSession(false);
        } else if (persistentAttributes.containsKey(activityValue)) {
            // string representation of the hobby to change fromm the db
            String hobbyChange = (String) persistentAttributes.get(activityValue);

            // change into a real hobby
            Hobby hobby = new Hobby(hobbyChange);
            // if duration is to be changed
            if (numberValue != null) {
                hobby.setMinutes(Integer.parseInt(numberValue));
                persistentAttributes.put(hobby.getName(), hobby.toString());
            }
            // if the place is to be changed
            if (placeValue != null) {
                boolean inside = placeValue.equals("drinnen");
                hobby.setInside(inside);
                persistentAttributes.put(hobby.getName(), hobby.toString());
            }
            // if name is to be changed
            if (activityNewValue != null) {
                hobby.setName(activityNewValue);
                persistentAttributes.remove(activityValue);
                persistentAttributes.put(activityNewValue, hobby.toString());
            }

            // set and save persistent attributes
            attributesManager.setPersistentAttributes(persistentAttributes);
            attributesManager.savePersistentAttributes();

            // response
            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.CARDTEXT_CONFIGUREACTIVITY)
                    .withSpeech(String.format(PhrasesAndConstants.CHANGE_SUCCESSFULL, hobby.getName()))
                    .withShouldEndSession(false);
        } else {
            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.CARDTEXT_CONFIGUREACTIVITY)
                    .withSpeech(PhrasesAndConstants.NO_HOBBY_BY_THAT_NAME)
                    .withShouldEndSession(false);
        }

        return responseBuilder.build();
    }
}

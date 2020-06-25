package motivator.handler.ourhandlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;
import motivator.PhrasesAndConstants;
import motivator.model.Hobby;

import java.util.*;

import static com.amazon.ask.request.Predicates.intentName;

public class AskForActivityHandler implements RequestHandler {

    Random random = new Random();

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AskForActivity"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        AttributesManager attributesManager = handlerInput.getAttributesManager();
        ResponseBuilder responseBuilder = handlerInput.getResponseBuilder();

        // add this entry to db
        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();

        if (persistentAttributes.size() == 0) {
            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.CARDTEXT_ASKFORACTIVITY)
                    .withSpeech(PhrasesAndConstants.NO_ACTIVITIES_YET)
                    .withShouldEndSession(false);
        } else {
            List<Map.Entry<String, Object>> list = new ArrayList(persistentAttributes.keySet());
            String randomHobby = (String) persistentAttributes.get(list.get(
                    list.size() == 1 ? 0 : random.nextInt(list.size() - 1)));

            Hobby hobby = new Hobby(randomHobby);

            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.CARDTEXT_ASKFORACTIVITY)
                    .withSpeech(String.format(PhrasesAndConstants.PROPOSED_HOBBY, hobby.getName()))
                    .withShouldEndSession(false);
        }
        return responseBuilder.build();
    }
}

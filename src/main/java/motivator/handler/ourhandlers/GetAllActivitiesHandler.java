package motivator.handler.ourhandlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;
import motivator.PhrasesAndConstants;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class GetAllActivitiesHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("GetAllActivities"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        AttributesManager attributesManager = handlerInput.getAttributesManager();
        ResponseBuilder responseBuilder = handlerInput.getResponseBuilder();
        StringBuilder sb = new StringBuilder();

        // add this entry to db
        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();

        if (persistentAttributes.size() == 0) {
            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.CARDTEXT_GETALLACTIVITIES)
                    .withSpeech(PhrasesAndConstants.NO_ACTIVITIES_YET)
                    .withShouldEndSession(false);
        } else {
            for (Map.Entry<String, Object> entry : persistentAttributes.entrySet()) {
                sb.append(entry.getKey());
                sb.append(" ");
            }

            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.CARDTEXT_GETALLACTIVITIES)
                    .withSpeech(String.format(PhrasesAndConstants.ACTIVITIES_OUTPUT, sb.toString()))
                    .withShouldEndSession(false);

        }

        return responseBuilder.build();
    }
}

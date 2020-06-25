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

public class HobbyAmountHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("HobbyAmount"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        AttributesManager attributesManager = handlerInput.getAttributesManager();
        ResponseBuilder responseBuilder = handlerInput.getResponseBuilder();

        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();

        String answer = String.format(PhrasesAndConstants.AMOUNT_SAVED, persistentAttributes.size());

        responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.CARDTEXT_HOBBYAMOUNT)
                .withSpeech(answer)
                .withShouldEndSession(false);

        return responseBuilder.build();
    }
}

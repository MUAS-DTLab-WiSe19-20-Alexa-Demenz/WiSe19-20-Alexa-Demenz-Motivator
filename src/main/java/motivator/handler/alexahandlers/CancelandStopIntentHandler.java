/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package motivator.handler.alexahandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import motivator.PhrasesAndConstants;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

/**
 * Copied from Lieblingsfarbe package
 * @author swe-Di-01
 *
 */
public class CancelandStopIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent")));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        return input.getResponseBuilder()
                .withSpeech(PhrasesAndConstants.CANCEL_AND_STOP)
                .withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.CANCEL_AND_STOP)
                .withShouldEndSession(true)
                .build();
    }
}

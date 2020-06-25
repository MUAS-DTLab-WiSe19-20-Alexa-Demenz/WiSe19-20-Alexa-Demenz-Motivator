/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package motivator;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import motivator.handler.alexahandlers.*;
import motivator.handler.ourhandlers.*;

public class MotivatorHandler extends SkillStreamHandler {

    @SuppressWarnings("unchecked")
    public static Skill getSkill() {
        return Skills.standard()
//                .withSkillId("amzn1.ask.skill.3de15287-a46b-42b1-8fbe-32c39a692f6f")
                .addRequestHandlers(
                        // Our Handlers
                        new AddActivityHandler(),
                        new AskForActivityHandler(),
                        new ConfigureActivityHandler(),
                        new DeleteActivityHandler(),
                        new GetAllActivitiesHandler(),
                        new HobbyAmountHandler(),
                        new InformationHandler(),
                        // Alexa Handler
                        new CancelandStopIntentHandler(),
                        new FallbackIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler())
                .withTableName("motivatorData")
                .withAutoCreateTable(true)
                .build();
    }
    
    public MotivatorHandler() {
        super(getSkill());
    }


}

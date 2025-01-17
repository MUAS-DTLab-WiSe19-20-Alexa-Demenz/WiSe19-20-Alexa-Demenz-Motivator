﻿# Alexa Skills Kit SDK Sample - Color Picker - Lieblingsfarbe 
(deutsche Übersetzung)

**on github pages: [https://sweihm-ws2018-19.github.io/skillproject-test/]( https://sweihm-ws2018-19.github.io/skillproject-test/)**

A simple [AWS Lambda](http://aws.amazon.com/lambda) function that demonstrates how to write a color picker skill for the Amazon Echo using the Alexa SDK.

## Concepts
This sample shows how to create a Lambda function for handling Alexa Skill requests that:

- Custom slot type: demonstrates using custom slot types to handle a finite set of known values
- Dialog and Session state: Handles two models, both a one-shot ask and tell model, and a multi-turn dialog model.

## Setup
To run this example skill you need to do two things. The first is to deploy the example code in lambda, and the second is to configure the Alexa skill to use Lambda.

### AWS Lambda Setup
Refer to [Hosting a Custom Skill as an AWS Lambda Function](https://developer.amazon.com/docs/custom-skills/host-a-custom-skill-as-an-aws-lambda-function.html) reference for a walkthrough on creating a AWS Lambda function with the correct role for your skill. When creating the function, select the “Author from scratch” option, and select the Java 8 runtime. 

To build the sample, open a terminal and go to the directory containing pom.xml, and run 'mvn org.apache.maven.plugins:maven-assembly-plugin:2.6:assembly -DdescriptorId=jar-with-dependencies package'. This will generate a zip file named "colorpicker-1.0-jar-with-dependencies.jar" in the target directory.

Once you've created your AWS Lambda function and configured “Alexa Skills Kit” as a trigger, upload the JAR file produced in the previous step and set the handler to the fully qualified class name of your handler function. Finally, copy the ARN for your AWS Lambda function because you’ll need it when configuring your skill in the Amazon Developer console.

### Alexa Skill Setup
Now that the skill code has been uploaded to AWS Lambda we're ready to configure the skill with Alexa. First, navigate to the [Alexa Skills Kit Developer Console](https://developer.amazon.com/alexa/console/ask). Click the “Create Skill” button in the upper right. Enter “ColorPicker” as your skill name. On the next page,  select “Custom” and click “Create skill”.
 
Now we're ready to define the interaction model for the skill. Under “Invocation” tab on the left side, define your Skill Invocation Name to be `lieblingsfarbe`. 
 

The Developer Console  allows you to edit the entire skill model in JSON format by selecting “JSON Editor” on the navigation bar. Use the file [models/de-DE.json](models/de-DE.json).

Once you’re done editing the interaction model don't forget to save and build the model.
 
Let's move on to the skill configuration section. Under “Endpoint” select “AWS Lambda ARN” and paste in the ARN of the function you created previously. The rest of the settings can be left at their default values. Click “Save Endpoints” and proceed to the next section.
 
Finally you're ready to test the skill! In the “Test” tab of the developer console you can simulate requests, in text and voice form, to your skill. Use the invocation name along with one of the sample utterances we just configured as a guide. You should also be able to go to the [Echo webpage](http://echo.amazon.com/#skills) and see your skill listed under “Your Skills”, where you can enable the skill on your account for testing from an Alexa enabled device.
 
At this point, feel free to start experimenting with your Intent Schema as well as the corresponding request handlers in your skill's implementation. Once you're finished iterating, you can optionally choose to move on to the process of getting your skill certified and published so it can be used by Alexa users worldwide.

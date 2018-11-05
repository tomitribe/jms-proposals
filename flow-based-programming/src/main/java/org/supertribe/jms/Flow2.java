package org.supertribe.jms;


import javax.jms.*;

import static javax.jms.MessageConfig.AcknowledgementOptions.DUPS_OK_ACKNOWLEDGE;
import static javax.jms.MessageConfig.DestinationType.QUEUE;
import static javax.jms.MessageConfig.DestinationType.TOPIC;
import static javax.jms.MessageConfig.MessageType.*;



public class Flow2 {

    @InMessage(
            @MessageConfig(
                messageType = TEXT_MESSAGE,
                destination = "XYX.TOPIC",
                destinationType = TOPIC,
                acknowledgement = DUPS_OK_ACKNOWLEDGE
            )
    )
    @OutMessage(
            @MessageConfig(
                destination="ABC.QUEUE",
                destinationType = QUEUE,
                replyTo = "DEF.QUEUE"
            )
    )
    public String helloYou(String messageIn) {

        String messageOut = "Hello, ";
        messageOut += messageIn;
        return messageOut;

    }

    public static void main(String [] args){
        Flow2 temp = new Flow2();
        System.out.println(temp.helloYou("Bob"));
    }
}



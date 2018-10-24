package org.supertribe.jms;


import javax.jms.*;
import javax.jms.IncomingMessage;
import javax.jms.OutgoingMessage;

import static javax.jms.IncomingMessage.AcknowledgementOptions.DUPS_OK_ACKNOWLEDGE;
import static javax.jms.OutgoingMessage.DestinationType.QUEUE;
import static javax.jms.IncomingMessage.DestinationType.TOPIC;
import static javax.jms.IncomingMessage.MessageType.*;



public class Flow {

    @IncomingMessage(
            messageType = TEXT_MESSAGE,
            destination = "XYX.TOPIC",
            destinationType = TOPIC,
            acknowledgement = DUPS_OK_ACKNOWLEDGE
    )
    @OutgoingMessage(
            destination = "ABC.QUEUE",
            destinationType = QUEUE,
            replyTo = "DEF.QUEUE"
    )
    public String helloYou(String messageIn) {

            String messageOut = "Hello, ";
            messageOut += messageIn;
            return messageOut;

    }
}

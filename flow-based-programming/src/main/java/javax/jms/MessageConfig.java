package javax.jms;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 */
@Retention(RUNTIME)
@Target({})
public @interface MessageConfig {

    enum MessageType {
        TEXT_MESSAGE,
        BYTES_MESSAGE,
        MAP_MESSAGE,
        STREAM_MESSAGE,
        OBJECT_MESSAGE
    }
    MessageType messageType() default MessageType.TEXT_MESSAGE;

    // the name of the topic or queue
    String destination() default "";

    // The kind of destination QUEUE or TYPE
    enum DestinationType {
        QUEUE, TOPIC
    }
    DestinationType destinationType() default DestinationType.QUEUE;


    enum AcknowledgementOptions {
        DUPS_OK_ACKNOWLEDGE,
        AUTO_ACKNOWLEDGE,
        CLIENT_ACKNOWLEDGE
    }
    AcknowledgementOptions acknowledgement() default AcknowledgementOptions.AUTO_ACKNOWLEDGE;

    String replyTo() default "";
}

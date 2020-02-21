/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.supertribe.jms;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "maxSessions", propertyValue = "3"),
        @ActivationConfigProperty(propertyName = "maxMessagesPerSessions", propertyValue = "1"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "TASK.QUEUE")
})
public class BuildTasksMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {

            if (!(message instanceof ObjectMessage)) {
                throw new JMSException("Expected ObjectMessage, received " + message.getJMSType());
            }

            final ObjectMessage objectMessage = (ObjectMessage) message;

            final BuildTask buildTask = (BuildTask) objectMessage.getObject();

            doSomethingUseful(buildTask);

        } catch (JMSException e) {
            // Why can't I throw a JMS Exception
            throw new RuntimeException(e);
        }
    }

    // This is the only "useful" code in the class
    private void doSomethingUseful(BuildTask buildTask) {
        System.out.println(buildTask);
    }
}

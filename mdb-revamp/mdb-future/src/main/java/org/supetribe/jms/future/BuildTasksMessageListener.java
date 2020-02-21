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
package org.supetribe.jms.future;

import javax.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.ObjectMessage;
import jakarta.jms.foo.Destination;
import jakarta.jms.foo.DestinationType;
import jakarta.jms.foo.MaxMessagesPerSession;
import jakarta.jms.foo.MaxSessions;
import jakarta.jms.foo.MessageType;

@MessageDriven
@MaxSessions(3)
@MaxMessagesPerSession(1)
public class BuildTasksMessageListener {

    @Destination("TASK.QUEUE")
    @DestinationType(jakarta.jms.Queue.class)
    public void processBuildTask(BuildTask buildTask) throws JMSException {

        System.out.println("Something useful " + buildTask);
    }

    @Destination("BUILD.TOPIC")
    @DestinationType(jakarta.jms.Topic.class)
    @MessageType(ObjectMessage.class)
    public void processBuildTask(BuildNotification notification) throws JMSException {

        System.out.println("Something happened " + notification);
    }

}

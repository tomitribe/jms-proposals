/*
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */
package org.example;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.JMSRuntimeException;
import javax.jms.MessageConsumer;
import javax.jms.MessageHeader;
import javax.jms.ObjectMessage;
import javax.jms.QueueListener;
import javax.jms.Topic;

@MessageConsumer
public class BuildAndNotify {

    @Resource
    private ConnectionFactory connectionFactory;

    @QueueListener("PROJECT.BUILD")
    public void buildProject(@MessageHeader(MessageHeader.Header.JMSReplyTo) final Topic buildNotifications,
                             @MessageHeader(MessageHeader.Header.JMSCorrelationID) final BuildId buildId,
                             final ObjectMessage objectMessage) throws JMSException {

        final Project project = (Project) objectMessage.getObject();

        try {
            build(project);

            try (JMSContext context = connectionFactory.createContext()) {
                final JMSProducer producer = context.createProducer();
                producer.setJMSCorrelationID(buildId.toString());
                producer.send(buildNotifications, "SUCCESS");
            } catch (JMSRuntimeException ex) {
                // handle exception (details omitted)
            }

        } catch (Exception e) {

            try (JMSContext context = connectionFactory.createContext()) {
                final JMSProducer producer = context.createProducer();
                producer.setJMSCorrelationID(buildId.toString());
                producer.send(buildNotifications, "FAIL");
            } catch (JMSRuntimeException ex) {
                // handle exception (details omitted)
            }
        }
    }

    private void build(final Project project) {

    }

}
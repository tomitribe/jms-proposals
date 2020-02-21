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

import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.JMSProducer;
import jakarta.jms.JMSRuntimeException;
import jakarta.jms.MessageConsumer;
import jakarta.jms.MessageHeader;
import jakarta.jms.ObjectMessage;
import jakarta.jms.QueueListener;
import jakarta.jms.Topic;

import javax.annotation.Resource;

@MessageConsumer
public class BuildAndNotify {

    @Resource
    private ConnectionFactory connectionFactory;

    @QueueListener("PROJECT.BUILD")
    public void buildProject(@MessageHeader(MessageHeader.Header.JMSReplyTo) final Topic buildNotifications,
                             @MessageHeader(MessageHeader.Header.JMSCorrelationID) final String buildId,
                             final ObjectMessage objectMessage) throws JMSException {

        final Project project = (Project) objectMessage.getObject();

        try {
            build(project);

            try (JMSContext context = connectionFactory.createContext()) {
                final JMSProducer producer = context.createProducer();
                producer.setJMSCorrelationID(buildId);
                producer.send(buildNotifications, "SUCCESS");
            } catch (JMSRuntimeException ex) {
                // handle exception (details omitted)
            }

        } catch (Exception e) {

            try (JMSContext context = connectionFactory.createContext()) {
                final JMSProducer producer = context.createProducer();
                producer.setJMSCorrelationID(buildId);
                producer.send(buildNotifications, "FAIL");
            } catch (JMSRuntimeException ex) {
                // handle exception (details omitted)
            }
        }
    }

    private void build(final Project project) {

    }

}

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
import jakarta.jms.JMSException;
import jakarta.jms.MessageConsumer;
import jakarta.jms.MessageHeader;
import jakarta.jms.MessagingClientBuilder;
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
                             final Project project) throws JMSException {

        final NotificationsClient notificationsClient = MessagingClientBuilder.newBuilder()
                .connectionFactory(connectionFactory)
                .build(NotificationsClient.class);

        try {
            build(project);

            notificationsClient.processNotifications(
                    buildId,
                    project.getUrl(),
                    "SUCCESS"
            );

        } catch (Exception e) {

            notificationsClient.processNotifications(
                    buildId,
                    project.getUrl(),
                    "FAIL"
            );
        }
    }

    private void build(final Project project) {

    }

}

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

import io.breezmq.MaxMessagesPerSession;
import io.breezmq.MaxSessions;
import jakarta.jms.JMSException;
import jakarta.jms.JMSMessageDrivenBean;
import jakarta.jms.ObjectMessage;
import org.example.destinations.BuildTopic;
import org.example.destinations.TaskQueue;

import javax.ejb.MessageDriven;

@MessageDriven
@MaxSessions(3)
@MaxMessagesPerSession(1)
public class BuildTasksMessageListener implements JMSMessageDrivenBean {

    @TaskQueue
    public void processBuildTask(final ObjectMessage objectMessage) throws JMSException {

        final BuildTask buildTask = (BuildTask) objectMessage.getObject();

        doSomethingUseful(buildTask);

    }

    @BuildTopic
    public void processBuildNotification(final ObjectMessage objectMessage) throws JMSException {

        final BuildNotification notification = (BuildNotification) objectMessage.getObject();

        System.out.println("Something happened " + notification);
    }


    // This is the only "useful" code in the class
    private void doSomethingUseful(final BuildTask buildTask) {
        System.out.println(buildTask);
    }
}

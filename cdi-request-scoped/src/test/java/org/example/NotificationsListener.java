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

import javax.enterprise.context.RequestScoped;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProperty;
import javax.jms.TopicListener;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@MessageConsumer
public class NotificationsListener {

    private final Logger logger = Logger.getLogger(NotificationsListener.class.getName());

    @TopicListener("BUILD.NOTIFICATIONS")
    public void processNotifications(@MessageProperty("buildId") final String buildId,
                                     @MessageProperty("date") final long date,
                                     @MessageProperty("projectUrl") final String projectUrl,
                                     @MessageProperty("buildUrl") final String buildUrl,
                                     @MessageProperty("buildStatus") final String buildStatus,
                                     final String message) throws JMSException {


        if ("FAIL".equals(buildStatus)) {

            logger.log(Level.SEVERE, String.format("Build Failed %s at %s: %s - details %s", buildId, date, buildUrl, message));

        } else if ("SUCCESS".equals(buildStatus)) {

            logger.log(Level.INFO, String.format("Build Succeded %s at %s: %s - details %s", buildId, date, buildUrl, message));
        }
    }
}

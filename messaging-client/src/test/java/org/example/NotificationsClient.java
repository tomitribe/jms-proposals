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

import jakarta.jms.MessageProperty;
import jakarta.jms.TopicListener;

import java.net.URL;

public interface NotificationsClient {

    @TopicListener("BUILD.NOTIFICATIONS")
    public void processNotifications(@MessageProperty("buildId") final String buildId,
                                     @MessageProperty("projectUrl") final String projectUrl,
                                     @MessageProperty("buildUrl") final String buildUrl,
                                     @MessageProperty("buildStatus") final String buildStatus,
                                     final String message);

    @TopicListener("BUILD.NOTIFICATIONS")
    public void processNotifications(@MessageProperty("buildId") final String buildId,
                                     @MessageProperty("projectUrl") final URL projectUrl,
                                     @MessageProperty("buildStatus") final String buildStatus);
}

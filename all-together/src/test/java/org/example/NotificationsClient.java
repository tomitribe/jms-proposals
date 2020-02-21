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

import org.example.destinations.BuildNotificationsTopic;
import org.example.properties.BuildIdParam;
import org.example.properties.BuildStatusParam;
import org.example.properties.BuildUrlParam;
import org.example.properties.DateParam;
import org.example.properties.ProjectUrlParam;

import java.net.URI;
import java.net.URL;
import java.util.Date;

public interface NotificationsClient {

    @BuildNotificationsTopic
    public void processNotifications(@BuildIdParam final BuildId buildId,
                                     @DateParam final Date date,
                                     @ProjectUrlParam final URL projectUrl,
                                     @BuildUrlParam final URI buildUrl,
                                     @BuildStatusParam final BuildStatus buildStatus,
                                     final String message);


    @BuildNotificationsTopic
    public void processNotifications(@BuildIdParam final BuildId buildId,
                                     @ProjectUrlParam final URL projectUrl,
                                     @BuildStatusParam final BuildStatus buildStatus);


}

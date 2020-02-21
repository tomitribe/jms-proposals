/*
 * Copyright (c) 2015, 2017 Oracle and/or its affiliates. All rights reserved.
 *
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

package jakarta.jms;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This annotation specifies that a callback method on a JMS message-driven bean must use the specified message
 * selector. It may be specified either on the callback method or on the message-driven bean class.
 *
 * <p>
 * If this annotation is specified on a method of a message-driven bean class then that method must also be annotated
 * with {@code QueueListener} or {@code TopicListener}. If it is not then deployment will fail.
 *
 * <p>
 * If this annotation is specified on the message-driven bean class then at least one method must be annotated with
 * {@code QueueListener} or {@code TopicListener}. If no method is annotated with {@code QueueListener} or
 * {@code TopicListener} then deployment will fail.
 *
 * <p>
 * If this annotation is specified on both a method of a message-driven bean class and on the message-driven bean class
 * itself then deployment will fail.
 *
 * @see QueueListener
 * @see TopicListener
 *
 * @version JMS 2.1
 * @since JMS 2.1
 */
@Retention(RUNTIME)
@Target({METHOD, TYPE, ANNOTATION_TYPE})
public @interface MessageSelector {

    /**
     * The message selector that will be used.
     *
     * @return The message selector that will be used.
     */
    String value();

}

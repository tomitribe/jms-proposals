/*
 * Copyright (c) 2017-2019 Contributors to the Eclipse Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jakarta.jms;

import javax.jms.ConnectionFactory;
import javax.ws.rs.core.Configurable;

public interface MessagingClientBuilder extends Configurable<MessagingClientBuilder> {

    static MessagingClientBuilder newBuilder() {
        return null;
    }

    MessagingClientBuilder connectionFactory(final ConnectionFactory connectionFactory);

    <T> T build(Class<T> clazz);

}
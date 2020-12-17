/*
 * Copyright (C) open knowledge GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package io.smallrye.asyncapi.core.runtime.io.bindings.mqtt.server;

public class MQTTServerBindingConstant {

    public static final String PROP_CLIENT_ID = "clientId";

    public static final String PROP_CLEAN_SESSION = "cleanSession";

    public static final String PROP_LAST_WILL = "lastWill";

    public static final String PROP_BINDING_VERSION = "bindingVersion";

    public static final String PROP_TOPIC = "topic";

    public static final String PROP_QOS = "qos";

    public static final String PROP_MESSAGE = "message";

    public static final String PROP_RETAIN = "retain";

    public MQTTServerBindingConstant() {
    }
}

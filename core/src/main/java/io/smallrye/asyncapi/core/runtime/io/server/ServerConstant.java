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
package io.smallrye.asyncapi.core.runtime.io.server;

import org.jboss.jandex.DotName;

import io.smallrye.asyncapi.spec.annotations.server.Server;
import io.smallrye.asyncapi.spec.annotations.server.Servers;

/**
 * Constants related to Server
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0#serverObject"
 *
 */
public class ServerConstant {
    public static final String PROP_VARIABLES = "variables";

    static final DotName DOTNAME_SERVER = DotName.createSimple(Server.class.getName());
    static final DotName DOTNAME_SERVERS = DotName.createSimple(Servers.class.getName());
    public static final String PROP_SERVER = "server";
    public static final String PROP_URL = "url";
    public static final String PROP_PROTOCOL = "protocol";
    public static final String PROP_PROTOCOL_VERSION = "protocolVersion";
    public static final String PROP_DESCRIPTION = "description";
    public static final String PROP_SECURITY_REQUIREMENTS = "security";
    public static final String PROP_BINDINGS = "bindings";

    private ServerConstant() {
    }
}

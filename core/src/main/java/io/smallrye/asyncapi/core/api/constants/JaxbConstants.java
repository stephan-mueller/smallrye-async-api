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
package io.smallrye.asyncapi.core.api.constants;

import org.jboss.jandex.DotName;

/**
 * Constants related to the JAXB Specification
 */
public class JaxbConstants {

    public static final DotName XML_TYPE = DotName.createSimple("javax.xml.bind.annotation.XmlType");

    public static final DotName XML_ELEMENT = DotName.createSimple("javax.xml.bind.annotation.XmlElement");

    public static final DotName XML_ATTRIBUTE = DotName.createSimple("javax.xml.bind.annotation.XmlAttribute");

    public static final String PROP_NAME = "name";

    private JaxbConstants() {
    }
}

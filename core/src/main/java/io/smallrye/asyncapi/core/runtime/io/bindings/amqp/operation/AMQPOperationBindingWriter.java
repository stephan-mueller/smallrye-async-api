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
package io.smallrye.asyncapi.core.runtime.io.bindings.amqp.operation;

import java.util.List;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.bindings.OperationBindingsConstants;
import io.smallrye.asyncapi.spec.models.binding.amqp.AMQPOperationBinding;

public class AMQPOperationBindingWriter {

    public AMQPOperationBindingWriter() {
    }

    public static void writeAMQPOperationBinding(ObjectNode parent, AMQPOperationBinding model) {
        if (model == null) {
            return;
        }
        ObjectNode node = JsonUtil.objectNode();
        parent.set(OperationBindingsConstants.PROP_AMQP_BINDING, node);

        JsonUtil.intProperty(node, AMQPOperationBindingConstants.PROP_EXPIRATION, model.getExpiration());
        JsonUtil.stringProperty(node, AMQPOperationBindingConstants.PROP_USER_ID, model.getUserId());

        List<String> cc = model.getCc();
        if (cc != null) {
            ArrayNode ccArray = node.putArray(AMQPOperationBindingConstants.PROP_CC);
            for (String ccValue : cc) {
                ccArray.add(ccValue);
            }
        }

        JsonUtil.intProperty(node, AMQPOperationBindingConstants.PROP_PRIORITY, model.getPriority());
        JsonUtil.intProperty(node, AMQPOperationBindingConstants.PROP_DELIVERY_MODE, model.getDeliveryMode());
        JsonUtil.booleanProperty(node, AMQPOperationBindingConstants.PROP_MANDATORY, model.isMandatory());

        List<String> bcc = model.getBcc();
        if (bcc != null) {
            ArrayNode bccArray = node.putArray(AMQPOperationBindingConstants.PROP_BCC);
            for (String bccValue : bcc) {
                bccArray.add(bccValue);
            }
        }

        JsonUtil.stringProperty(node, AMQPOperationBindingConstants.PROP_REPLY_TO, model.getReplyTo());
        JsonUtil.booleanProperty(node, AMQPOperationBindingConstants.PROP_TIME_STAMP, model.getTimeStamp());
        JsonUtil.booleanProperty(node, AMQPOperationBindingConstants.PROP_ACK, model.getAck());
        JsonUtil.stringProperty(node, AMQPOperationBindingConstants.PROP_BINDING_VERSION, model.getBindingVersion());
    }
}

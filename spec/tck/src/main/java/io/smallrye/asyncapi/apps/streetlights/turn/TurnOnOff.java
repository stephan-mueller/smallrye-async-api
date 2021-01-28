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

package io.smallrye.asyncapi.apps.streetlights.turn;

import java.time.LocalDateTime;

import io.smallrye.asyncapi.spec.annotations.schema.Schema;

@Schema(name = "turnOnOffPayload")
public class TurnOnOff {

    private Command command;

    private LocalDateTime sentAt;

    public TurnOnOff() {
    }

    public TurnOnOff(final Command command, final LocalDateTime sentAt) {
        this.command = command;
        this.sentAt = sentAt;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(final Command command) {
        this.command = command;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(final LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    @Override
    public String toString() {
        return "TurnOnOff{" + "command=" + command + ", sentAt=" + sentAt.toString() + '}';
    }
}

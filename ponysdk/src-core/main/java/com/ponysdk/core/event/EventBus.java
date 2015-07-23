/*
 * Copyright (c) 2011 PonySDK
 *  Owners:
 *  Luciano Broussal  <luciano.broussal AT gmail.com>
 *	Mathieu Barbier   <mathieu.barbier AT gmail.com>
 *	Nicolas Ciaravola <nicolas.ciaravola.pro AT gmail.com>
 *  
 *  WebSite:
 *  http://code.google.com/p/pony-sdk/
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.ponysdk.core.event;

import java.util.Collection;

import com.ponysdk.core.event.Event.Type;

public interface EventBus {

    public <H extends EventHandler> HandlerRegistration addHandler(Type<H> type, H handler);

    public <H extends EventHandler> void removeHandler(Type<H> type, H handler);

    public <H extends EventHandler> HandlerRegistration addHandlerToSource(Type<H> type, Object source, H handler);

    public <H extends EventHandler> void removeHandlerFromSource(Type<H> type, Object source, H handler);

    public void addHandler(BroadcastEventHandler handler);

    public void removeHandler(BroadcastEventHandler handler);

    public void fireEvent(Event<?> event);

    public void fireEventFromSource(Event<?> event, Object source);

    public <H extends EventHandler> Collection<H> getHandlers(final Type<H> type, final Object source);
}

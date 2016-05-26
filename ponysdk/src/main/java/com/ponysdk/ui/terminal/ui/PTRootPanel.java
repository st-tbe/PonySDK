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

package com.ponysdk.ui.terminal.ui;

import com.google.gwt.user.client.ui.RootPanel;
import com.ponysdk.ui.terminal.UIBuilder;
import com.ponysdk.ui.terminal.model.BinaryModel;
import com.ponysdk.ui.terminal.model.ReaderBuffer;
import com.ponysdk.ui.terminal.model.ServerToClientModel;

public class PTRootPanel extends PTAbsolutePanel {

    private String rootId;

    @Override
    public void create(final ReaderBuffer buffer, final int objectId, final UIBuilder uiService) {
        // ServerToClientModel.ROOT_ID
        rootId = buffer.getBinaryModel().getStringValue();

        super.create(buffer, objectId, uiService);
    }

    @Override
    protected RootPanel createUIObject() {
        return rootId != null ? RootPanel.get(rootId) : RootPanel.get();
    }

    @Override
    public boolean update(final ReaderBuffer buffer, final BinaryModel binaryModel) {
        if (ServerToClientModel.CLEAR_DOM.equals(binaryModel.getModel())) {
            RootPanel.get().clear(true);
            return true;
        }
        return super.update(buffer, binaryModel);
    }

}

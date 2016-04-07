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

import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.ponysdk.ui.terminal.UIService;
import com.ponysdk.ui.terminal.model.BinaryModel;
import com.ponysdk.ui.terminal.model.Model;
import com.ponysdk.ui.terminal.model.ReaderBuffer;

public class PTDecoratedPopupPanel extends PTPopupPanel {

    @Override
    public void create(final ReaderBuffer buffer, final int objectId, final UIService uiService) {
        boolean autoHide = false;
        final BinaryModel binaryModel = buffer.getBinaryModel();
        if (Model.POPUP_AUTO_HIDE.equals(binaryModel.getModel())) {
            autoHide = binaryModel.getBooleanValue();
        } else {
            buffer.rewind(binaryModel);
        }

        this.uiObject = new DecoratedPopupPanel(autoHide);
        this.objectID = objectId;
        uiService.registerUIObject(this.objectID, uiObject);
        addCloseHandler(uiService);
    }

    @Override
    public DecoratedPopupPanel cast() {
        return (DecoratedPopupPanel) uiObject;
    }

}

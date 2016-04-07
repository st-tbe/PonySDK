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

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.ponysdk.ui.terminal.UIService;
import com.ponysdk.ui.terminal.model.BinaryModel;
import com.ponysdk.ui.terminal.model.HandlerModel;
import com.ponysdk.ui.terminal.model.Model;
import com.ponysdk.ui.terminal.model.ReaderBuffer;

public class PTTextBoxBase<W extends TextBoxBase> extends PTValueBoxBase<W, String> {

    @Override
    public void create(final ReaderBuffer buffer, final int objectId, final UIService uiService) {
        super.create(buffer, objectId, uiService);
        uiObject.addValueChangeHandler(new ValueChangeHandler<String>() {

            @Override
            public void onValueChange(final ValueChangeEvent<String> event) {
                final PTInstruction eventInstruction = new PTInstruction();
                eventInstruction.setObjectID(objectId);
                eventInstruction.put(HandlerModel.HANDLER_STRING_VALUE_CHANGE_HANDLER);
                eventInstruction.put(Model.VALUE, event.getValue());
                uiService.sendDataToServer(uiObject, eventInstruction);
            }
        });

        final BinaryModel binaryModel = buffer.getBinaryModel();
        if (Model.TEXT.equals(binaryModel.getModel())) {
            uiObject.setText(binaryModel.getStringValue());
        } else {
            buffer.rewind(binaryModel);
        }
    }

    @Override
    public boolean update(final ReaderBuffer buffer, final BinaryModel binaryModel) {
        if (Model.TEXT.equals(binaryModel.getModel())) {
            uiObject.setText(binaryModel.getStringValue());
            return true;
        }
        if (Model.PLACEHOLDER.equals(binaryModel.getModel())) {
            uiObject.getElement().setAttribute("placeholder", binaryModel.getStringValue());
            return true;
        }
        return super.update(buffer, binaryModel);
    }

}

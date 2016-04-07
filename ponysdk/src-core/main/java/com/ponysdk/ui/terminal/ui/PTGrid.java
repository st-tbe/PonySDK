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

import com.google.gwt.user.client.ui.Grid;
import com.ponysdk.ui.terminal.UIService;
import com.ponysdk.ui.terminal.model.BinaryModel;
import com.ponysdk.ui.terminal.model.Model;
import com.ponysdk.ui.terminal.model.ReaderBuffer;

public class PTGrid extends PTHTMLTable {

    @Override
    public void create(final ReaderBuffer buffer, final int objectId, final UIService uiService) {
        final BinaryModel binaryModel = buffer.getBinaryModel();
        if (Model.ROW.equals(binaryModel.getModel())) {
            final int rows = binaryModel.getIntValue();
            // Model.COLUMN
            final int columns = buffer.getBinaryModel().getIntValue();
            this.uiObject = new Grid(rows, columns);
        } else {
            buffer.rewind(binaryModel);
            this.uiObject = new Grid();
        }

        this.uiObject.addStyleName("pony-PGrid");

        this.objectID = objectId;
        uiService.registerUIObject(this.objectID, uiObject);
    }

    @Override
    public boolean update(final ReaderBuffer buffer, final BinaryModel binaryModel) {
        if (Model.CLEAR_ROW.equals(binaryModel.getModel())) {
            cast().removeRow(binaryModel.getIntValue());
            return true;
        }
        if (Model.INSERT_ROW.equals(binaryModel.getModel())) {
            cast().insertRow(binaryModel.getIntValue());
            return true;
        }
        return super.update(buffer, binaryModel);
    }

    @Override
    public Grid cast() {
        return (Grid) uiObject;
    }
}

/**
 * Copyright (C) 2011 WRML.org <mark@wrml.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wrml.core.runtime;

import org.wrml.core.model.alert.Alert;

public class AlertException extends RuntimeException {

    private static final long serialVersionUID = -4887032027932971775L;

    private final Alert _Alert;

    public AlertException(Alert alert) {
        _Alert = alert;
    }

    public Alert getAlert() {
        return _Alert;
    }

}

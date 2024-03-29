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

package org.wrml.core.service;

import java.net.URI;

import org.wrml.core.Model;
import org.wrml.core.util.observable.MapEvent;
import org.wrml.core.util.observable.ObservableMap;

public class ServiceEvent<M extends Model> extends MapEvent<URI, M> {

    private static final long serialVersionUID = 6956353992776164917L;

    public ServiceEvent(ObservableMap<URI, M> map, boolean cancelable) {
        super(map, cancelable);
        // TODO Auto-generated constructor stub
    }

    public ServiceEvent(ObservableMap<URI, M> map, boolean cancelable, URI _Key) {
        super(map, cancelable, _Key);
        // TODO Auto-generated constructor stub
    }

    public ServiceEvent(ObservableMap<URI, M> map, boolean cancelable, URI key, M newValue, M oldValue) {
        super(map, cancelable, key, newValue, oldValue);
        // TODO Auto-generated constructor stub
    }

}

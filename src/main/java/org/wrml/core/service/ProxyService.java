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
import java.util.Map;

import org.wrml.core.Model;
import org.wrml.core.runtime.Context;
import org.wrml.core.transformer.Transformer;
import org.wrml.core.util.observable.DelegatingObservableMap;
import org.wrml.core.www.MediaType;

public class ProxyService extends DelegatingObservableMap<URI, Object> implements DelegatingService<Map<URI, Object>> {

    // TODO: Add service event handler add/remove and event firing logic

    private final transient Context _Context;

    public ProxyService(Context context, Service originService) {
        super(originService);
        _Context = context;
    }

    public Object create(URI collectionId, Object requestEntity, MediaType responseType, Model referrer) {
        return getOriginService().create(collectionId, requestEntity, responseType, referrer);
    }

    public Object get(URI resourceId, Object cachedEntity, MediaType responseType, Model referrer) {
        return getOriginService().get(resourceId, cachedEntity, responseType, referrer);
    }

    public final Context getContext() {
        return _Context;
    }

    public Transformer<URI, ?> getIdTransformer() {
        return getOriginService().getIdTransformer();
    }

    public Service getOriginService() {
        return (Service) super.getDelegate();
    }

    public Object put(URI resourceId, Object requestEntity, MediaType responseType, Model referrer) {
        return getOriginService().put(resourceId, requestEntity, responseType, referrer);
    }

    public Object remove(URI resourceId, MediaType responseType, Model referrer) {
        return getOriginService().remove(resourceId, responseType, referrer);
    }

}

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

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.wrml.core.model.Collection;
import org.wrml.core.model.api.Api;
import org.wrml.core.model.api.LinkTemplate;

final class HypermediaEngine extends RuntimeObject {

    private final URI _ApiId;
    private final Map<URI, Resource> _Resources;

    private List<LinkTemplate> _LinkTemplates;

    public HypermediaEngine(Context context, URI apiId) {
        super(context);
        _ApiId = apiId;
        _Resources = new HashMap<URI, Resource>();
    }

    public Api getApi() {
        return (Api) getContext().getService(Api.class).get(getApiId());
    }

    public URI getApiId() {
        return _ApiId;
    }

    public List<LinkTemplate> getLinkTemplates() {

        if (_LinkTemplates == null) {

            final Context context = getContext();
            final Api api = getApi();

            final Collection<LinkTemplate> linkTemplates = api.getLinkTemplates();
            int size = linkTemplates.getSize();
            size = (size > 0) ? size : linkTemplates.getElements().size();
            _LinkTemplates = new ArrayList<LinkTemplate>(size);
            context.fetchAllDocuments(linkTemplates, _LinkTemplates);

        }

        return _LinkTemplates;
    }

    public Resource getResource(URI resourceTemplateId) {
        if (!_Resources.containsKey(resourceTemplateId)) {
            _Resources.put(resourceTemplateId, new Resource(this, resourceTemplateId));
        }

        return _Resources.get(resourceTemplateId);
    }

}

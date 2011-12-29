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

package org.wrml;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import org.wrml.model.communication.http.MediaType;
import org.wrml.model.schema.Schema;

/**
 * The base interface for all web resource schema instances.
 * 
 * TODO: Consider refactoring some/all of this interface into a true WRML
 * schema-driven model.
 */
public interface Model extends Serializable {

    public void addEventListener(ModelEventListener listener);

    public void addFieldEventListener(String fieldName, FieldEventListener listener);

    public void addLinkEventListener(URI linkRelationId, LinkEventListener listener);

    /**
     * Die means that the model is gone but may not be deleted
     */
    public void die();

    public void extend(Model modelToExtend, Model... additionalModelsToExtend);

    public Context getContext();

    public List<URI> getEmbeddedLinkRelationIds();

    public Object getFieldValue(String fieldName);

    public Schema getSchema();

    public URI getSchemaId();

    public Model getStaticInterface();

    public void removeEventListener(ModelEventListener listener);

    public void removeFieldEventListener(String fieldName, FieldEventListener listener);

    public void removeLinkEventListener(URI linkRelationId, LinkEventListener listener);

    public void setAllFieldsToDefaultValue();

    public void setFieldToDefaultValue(String fieldName);

    public Object setFieldValue(String fieldName, Object fieldValue);

    public Object clickLink(URI rel, MediaType responseMediaType, Object requestEntity, List<String> hrefParams);

}

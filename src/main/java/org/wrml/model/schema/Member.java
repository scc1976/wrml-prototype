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

package org.wrml.model.schema;

import java.net.URI;

import org.wrml.Model;

/**
 * Base class for members of a schema.
 */
// Generated from a Web Resource Schema
public interface Member extends Model {

    // Generated from Link
    //     Relation: declaredSchema
    //         Methods: GET 
    //         ResponseSchema: Schema[?]
    //     EnabledFormula: declaredSchemaId != null
    //     DestinationUriTemplate: {declaredSchemaId} 
    //     DestinationUriTemplateParameters: [FieldUriTemplateParameter["declaredSchemaId"]]
    //     Href: <declaredSchemaId>
    public Schema getDeclaredSchema();

    public URI getDeclaredSchemaId();

    // Generated from Link
    //     Relation: ownerSchema
    //         Methods: GET 
    //         ResponseSchema: Schema[?]
    //     EnabledFormula: ownerSchemaId != null
    //     DestinationUriTemplate: {ownerSchemaId} 
    //     DestinationUriTemplateParameters: [FieldUriTemplateParameter["ownerSchemaId"]]
    //     Href: <ownerSchemaId>
    public Schema getOwnerSchema();

    public URI getOwnerSchemaId();
}
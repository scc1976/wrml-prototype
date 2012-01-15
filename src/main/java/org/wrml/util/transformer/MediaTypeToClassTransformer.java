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

package org.wrml.util.transformer;

import java.lang.reflect.TypeVariable;
import java.net.URI;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.wrml.Model;
import org.wrml.runtime.Context;
import org.wrml.util.MediaType;

public class MediaTypeToClassTransformer extends ConstantTransformer<MediaType, Class<?>> {

    public MediaTypeToClassTransformer(Context context) {
        super(context);
    }

    public Class<?> aToB(MediaType mediaType) {
        final Context context = getContext();
        final URI schemaId = context.getSchemaIdToMediaTypeTransformer().bToA(mediaType);
        return context.getSchemaIdToClassTransformer().aToB(schemaId);
    }

    public MediaType bToA(Class<?> clazz) {

        if (clazz == null) {
            return null;
        }

        final Context context = getContext();
        MediaType mediaType = null;

        if (Model.class.isAssignableFrom(clazz)) {

            final Transformer<URI, Class<?>> schemaIdToClassTransformer = context.getSchemaIdToClassTransformer();
            String mediaTypeString = null;

            if (clazz.equals(Model.class)) {
                // Return the vanilla application/wrml type for base models                    
                mediaTypeString = MediaType.MEDIA_TYPE_STRING_WRML;
            }
            else {
                // Return the application/wrml with schema param for derived models
                final URI schemaId = schemaIdToClassTransformer.bToA(clazz);

                // TODO: Allow for parameterized types via Constraints
                SortedMap<String, String> parameters = null;

                /*
                 * Use reflection to determine if the type is parameterized.
                 * Ultimately
                 * looking to get a map of type parameter name (aka Schema
                 * ConstraintDefinition Name)
                 * to schema Id. Get the name (keys) from the Java generics
                 * reflection API.
                 * Get the URI values using Java class name to Schema URI
                 * transform.
                 * 
                 * TODO: Need to figure out how to get a Java generic
                 * interface's
                 * parameterized type names and actual types (as a Model
                 * subclass)
                 * 
                 * http://www.artima.com/weblogs/viewpost.jsp?thread=208860
                 */

                final TypeVariable<?>[] typeParameters = clazz.getTypeParameters();
                if (typeParameters != null) {
                    parameters = new TreeMap<String, String>();
                    for (final TypeVariable<?> typeParam : typeParameters) {
                        final String name = typeParam.getName();

                        final Class<?> typeParamSchemaClass = null;
                        final URI typeParamSchemaId = schemaIdToClassTransformer.bToA(typeParamSchemaClass);
                        parameters.put(name, String.valueOf(typeParamSchemaId));
                    }
                }

                mediaTypeString = createWrmlMediaTypeString(schemaId, parameters);

            }

            mediaType = context.getMediaTypeToStringTransformer().bToA(mediaTypeString);
        }
        else {
            // TODO: Get MediaType associated with non-WRML class
        }

        return mediaType;
    }

    public String createWrmlMediaTypeString(final URI schemaId) {
        return createWrmlMediaTypeString(schemaId, null);
    }

    public String createWrmlMediaTypeString(final URI schemaId, final Map<String, String> parameters) {
        return MediaType.createWrmlMediaTypeString(getContext().getUriToStringTransformer().aToB(schemaId), parameters);
    }

}

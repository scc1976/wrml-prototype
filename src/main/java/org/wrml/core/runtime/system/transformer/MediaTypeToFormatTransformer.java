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

package org.wrml.core.runtime.system.transformer;

import java.net.URI;

import org.wrml.core.Model;
import org.wrml.core.model.format.Format;
import org.wrml.core.runtime.Context;
import org.wrml.core.service.Service;
import org.wrml.core.www.MediaType;

public class MediaTypeToFormatTransformer extends ConstantTransformer<MediaType, Format> {

    public MediaTypeToFormatTransformer(Context context) {
        super(context);
    }

    public Format aToB(MediaType mediaType) {
        final Context context = getContext();
        final SystemTransformers systemTransformers = context.getSystemTransformers();

        URI formatId = null;
        final String formatIdString = mediaType.getFormatIdString();
        if (formatIdString != null) {
            formatId = context.getStringTransformers().getTransformer(URI.class).bToA(formatIdString);
        }
        else {
            formatId = systemTransformers.getMediaTypeToFormatIdTransformer().aToB(mediaType);
        }

        final Service service = context.getService(Format.class);
        return (Format) ((Model) service.get(formatId, null, systemTransformers.getMediaTypeToNativeTypeTransformer()
                .bToA(Format.class), null)).getStaticInterface();
    }

    public MediaType bToA(Format format) {

        if (format == null) {
            return null;
        }

        return format.getMediaType();
    }

}

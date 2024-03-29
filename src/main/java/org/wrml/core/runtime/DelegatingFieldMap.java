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

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.wrml.core.util.DelegatingMap;

public abstract class DelegatingFieldMap extends FieldMap implements DelegatingMap<String, Object> {

    private final Map<String, Object> _Delegate;

    public DelegatingFieldMap(final Context context, final Map<String, Object> delegate) {
        super(context);
        _Delegate = delegate;
    }

    public final Map<String, Object> getDelegate() {
        return _Delegate;
    }

    @Override
    protected final SortedSet<String> getFieldNames() {
        return new TreeSet<String>(_Delegate.keySet());
    }

    @Override
    protected Object getRawFieldValue(String fieldName) {
        return _Delegate.get(fieldName);
    }

    @Override
    protected boolean isReadOnly(String fieldName) {
        return false;
    }

    @Override
    protected Object setRawFieldValue(String fieldName, Object newValue) {
        return _Delegate.put(fieldName, newValue);
    }

}

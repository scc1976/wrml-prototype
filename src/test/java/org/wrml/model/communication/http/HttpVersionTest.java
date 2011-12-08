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
package org.wrml.model.communication.http;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.wrml.model.communication.http.HttpVersion.*;

public class HttpVersionTest {

    @Test
    public void shouldParse10() {
        assertThat("parsed HTTP version", fromString("HTTP/1.0"), is(HTTP_1_0));
    }

    @Test
    public void shouldParse11() {
        assertThat("parsed HTTP version", fromString("HTTP/1.1"), is(HTTP_1_1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException() {
        fromString("1234");
        fail("Invalid HTTP version should throw exception.");
    }

}

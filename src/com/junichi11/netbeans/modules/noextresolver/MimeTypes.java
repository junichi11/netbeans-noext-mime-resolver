/*
 * The MIT License
 *
 * Copyright 2014 junichi11.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.junichi11.netbeans.modules.noextresolver;

import java.util.HashMap;
import java.util.Map;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author junichi11
 */
public enum MimeTypes {

    SHELL("text/sh"), // NOI18N
    RUBY("text/x-ruby"), // NOI18N
    PYTHON("text/x-python"), // NOI18N
    PERL("text/x-perl"), // NOI18N
    JS("text/javascript"), // NOI18N
    GROOVY("text/x-groovy"), // NOI18N
    ;

    private final String mimeType;
    private static final Map<String, MimeTypes> TYPES = new HashMap<>();
    public static final String[] MIME_TYPES = new String[] {
        SHELL.getMimeType(),
        RUBY.getMimeType(),
        PYTHON.getMimeType(),
        PERL.getMimeType(),
        JS.getMimeType(),
        GROOVY.getMimeType(),
    };

    static {
        TYPES.put("bash", SHELL); // NOI18N
        TYPES.put("sh", SHELL); // NOI18N
        TYPES.put("ruby", RUBY); // NOI18N
        TYPES.put("python", PYTHON); // NOI18N
        TYPES.put("python2", PYTHON); // NOI18N
        TYPES.put("python3", PYTHON); // NOI18N
        TYPES.put("perl", PERL); // NOI18N
        TYPES.put("node", JS); // NOI18N
        TYPES.put("groovy", GROOVY); // NOI18N
    }

    private MimeTypes(String mimeType) {
        this.mimeType = mimeType;
    }

    @NonNull
    public String getMimeType() {
        return mimeType;
    }

    public static MimeTypes valueOfInterpreter(String interpreter) {
        return TYPES.get(interpreter);
    }

}

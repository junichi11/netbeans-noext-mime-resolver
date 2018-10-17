/*
 * The MIT License
 *
 * Copyright 2018 junichi11.
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
package com.junichi11.netbeans.modules.noextresolver.utils;

/**
 *
 * @author junichi11
 */
public final class Utils {

    private static final String MULTI_START = "/*"; // NOI18N
    private static final String MULTI_END = "*/"; // NOI18N
    private static final String ONELINE_HASH = "#"; // NOI18N
    private static final String ONELINE_DOUBLE_SLASH = "//"; // NOI18N

    private Utils() {
    }

    public static boolean isCommentLine(String line) {
        if (line == null) {
            return false;
        }
        String commentLine = line.trim();
        return commentLine.startsWith(ONELINE_DOUBLE_SLASH)
                || commentLine.startsWith(ONELINE_HASH)
                || (commentLine.startsWith(MULTI_START) && commentLine.endsWith(MULTI_END));
    }
}

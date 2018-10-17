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

import com.junichi11.netbeans.modules.noextresolver.api.MimeType;

/**
 *
 * @author junichi11
 */
public class NoExtMIMEResolverModelineTest extends NoExtMIMEResolverTestBase {

    public NoExtMIMEResolverModelineTest(String name) {
        super(name, "modeline");
    }

    public void testJs_01() {
        findMimeType(MimeType.JS, "js");
    }

    public void testC_01() {
        findMimeType(MimeType.C, "c");
    }

    public void testCpp_01() {
        findMimeType(MimeType.CPP, "cpp");
    }

    public void testCpp_02() {
        findMimeType(MimeType.CPP, "cpp_long");
    }

    public void testCpp_03() {
        findMimeType(MimeType.CPP, "cpp_hash");
    }

    public void testCpp_04() {
        findMimeType(MimeType.CPP, "cpp_multiline_comment");
    }

    public void testUnsupported() {
        findMimeType(null, "unsupported");
    }

}

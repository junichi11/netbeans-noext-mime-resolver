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
public class NoExtMIMEResolverShebangLineTest extends NoExtMIMEResolverTestBase {

    public NoExtMIMEResolverShebangLineTest(String name) {
        super(name, "shebang");
    }

    public void testShell_01() {
        findMimeType(MimeType.SHELL, "env_sh");
    }

    public void testShell_02() {
        findMimeType(MimeType.SHELL, "env_bash");
    }

    public void testShell_03() {
        findMimeType(MimeType.SHELL, "bin_bash");
    }

    public void testShell_04() {
        findMimeType(MimeType.SHELL, ".bashrc");
    }

    public void testRuby() {
        findMimeType(MimeType.RUBY, "ruby");
    }

    public void testPython() {
        findMimeType(MimeType.PYTHON, "python");
    }

    public void testPython2() {
        findMimeType(MimeType.PYTHON, "python2");
    }

    public void testPython3() {
        findMimeType(MimeType.PYTHON, "python3");
    }

    public void testPerl_01() {
        findMimeType(MimeType.PERL, "perl_01");
    }

    public void testPerl_02() {
        findMimeType(MimeType.PERL, "perl_02");
    }

    public void testGroovy_01() {
        findMimeType(MimeType.GROOVY, "groovy_01");
    }

    public void testGroovy_02() {
        findMimeType(MimeType.GROOVY, "groovy_02");
    }

    public void testPHP_01a() {
        findMimeType(MimeType.PHP, "php_01a");
    }

    public void testPHP_01b() {
        findMimeType(MimeType.PHP, "php_01b");
    }

    public void testPHP_02() {
        findMimeType(MimeType.PHP, "php_02");
    }

    public void testUnsupported_01() {
        findMimeType(null, "unsupported");
    }

    public void testUnsupported_02() {
        findMimeType(null, "empty");
    }

}

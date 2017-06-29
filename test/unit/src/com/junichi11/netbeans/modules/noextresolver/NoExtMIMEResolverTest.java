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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.netbeans.junit.NbTestCase;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author junichi11
 */
public class NoExtMIMEResolverTest extends NbTestCase {

    private final FileObject rootDirectory;
    private final NoExtMIMEResolver resolver;

    public NoExtMIMEResolverTest(String name) {
        super(name);
        FileObject dataDirectory = FileUtil.toFileObject(getDataDir());
        rootDirectory = dataDirectory.getFileObject("shebang");
        resolver = new NoExtMIMEResolver();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    @Override
    public void setUp() {
    }

    @After
    @Override
    public void tearDown() {
    }

    public void testShell_01() {
        findMimeType(MimeTypes.SHELL, "env_sh");
    }

    public void testShell_02() {
        findMimeType(MimeTypes.SHELL, "env_bash");
    }

    public void testShell_03() {
        findMimeType(MimeTypes.SHELL, "bin_bash");
    }

    public void testShell_04() {
        findMimeType(MimeTypes.SHELL, ".bashrc");
    }

    public void testRuby() {
        findMimeType(MimeTypes.RUBY, "ruby");
    }

    public void testPython() {
        findMimeType(MimeTypes.PYTHON, "python");
    }

    public void testPerl_01() {
        findMimeType(MimeTypes.PERL, "perl_01");
    }

    public void testPerl_02() {
        findMimeType(MimeTypes.PERL, "perl_02");
    }

    public void testGroovy_01() {
        findMimeType(MimeTypes.GROOVY, "groovy_01");
    }

    public void testGroovy_02() {
        findMimeType(MimeTypes.GROOVY, "groovy_02");
    }

    public void testUnsupported_01() {
        findMimeType(null, "unsupported");
    }

    public void testUnsupported_02() {
        findMimeType(null, "empty");
    }

    private void findMimeType(MimeTypes mimetype, String filepath) {
        if (mimetype == null) {
            assertEquals(null, resolver.findMIMEType(rootDirectory.getFileObject(filepath)));
        } else {
            assertEquals(mimetype.getMimeType(), resolver.findMIMEType(rootDirectory.getFileObject(filepath)));
        }
    }

}

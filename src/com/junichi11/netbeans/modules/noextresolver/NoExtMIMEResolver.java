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

import static com.junichi11.netbeans.modules.noextresolver.MimeType.UNNKOWN;
import com.junichi11.netbeans.modules.noextresolver.parser.ModelineParser;
import com.junichi11.netbeans.modules.noextresolver.parser.Parser;
import com.junichi11.netbeans.modules.noextresolver.parser.ParserFactory;
import com.junichi11.netbeans.modules.noextresolver.parser.ShebanglineParser;
import com.junichi11.netbeans.modules.noextresolver.utils.ShebangUtils;
import com.junichi11.netbeans.modules.noextresolver.utils.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.util.lookup.ServiceProvider;

/**
 * MIMEResolver for no extension file.
 *
 * @author junichi11
 */
@ServiceProvider(service = MIMEResolver.class)
public final class NoExtMIMEResolver extends MIMEResolver {

    public NoExtMIMEResolver() {
        super(MimeType.MIME_TYPES);
    }

    @Override
    public String findMIMEType(FileObject fo) {
        if (!fo.canRead() || fo.isFolder()) {
            return null;
        }

        // dot file : file name is empty and ext is not empty
        String ext = fo.getExt();
        String name = fo.getName();
        if (!name.isEmpty() && !ext.isEmpty()) {
            return null;
        }

        String firstLine = getFirstLine(fo);
        List<Parser> parsers = ParserFactory.createParsers(firstLine);
        for (Parser parser : parsers) {
            Parser.Result result = parser.parse().getResult();
            if (result.getMimeType() != UNNKOWN) {
                return result.getMimeType().getMimeType();
            }
        }
        return null;
    }

    /**
     * Get first line from FileObject.
     *
     * @param fo FileObject
     * @return empty string(i.e. "") if some problems are occurred, first line
     * otherwise
     */
    private String getFirstLine(FileObject fo) {
        try {
            // XXX get encoding?
            for (String line : fo.asLines("UTF-8")) { // NOI18N
                return line;
            }
        } catch (IOException ex) {
            Logger.getLogger(NoExtMIMEResolver.class.getName()).log(Level.WARNING, null, ex);
        }
        return ""; // NOI18N
    }

}

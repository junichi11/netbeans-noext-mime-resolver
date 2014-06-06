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

import com.junichi11.netbeans.modules.noextresolver.utils.ShebangUtils;
import java.io.IOException;
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

    @Override
    public String findMIMEType(FileObject fo) {
        if (fo.isFolder()) {
            return null;
        }

        String ext = fo.getExt();
        if (!ext.isEmpty()) {
            return null;
        }

        // exists shebang?
        String firstLine = getFirstLine(fo);
        if (!ShebangUtils.isShebang(firstLine)) {
            return null;
        }

        String interpriterName = ShebangUtils.getInterpriterName(firstLine);
        if (interpriterName == null || interpriterName.isEmpty()) {
            return null;
        }

        // check all mime types
        for (MimeTypes mimeType : MimeTypes.values()) {
            if (mimeType.isThis(interpriterName)) {
                return mimeType.getMiMeType();
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

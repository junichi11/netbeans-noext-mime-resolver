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
package com.junichi11.netbeans.modules.noextresolver.parser;

import com.junichi11.netbeans.modules.noextresolver.api.MimeType;
import com.junichi11.netbeans.modules.noextresolver.utils.ShebangUtils;
import com.junichi11.netbeans.modules.noextresolver.parser.spi.NoExtMIMEResolverParser;

public class ShebanglineParser implements NoExtMIMEResolverParser {

    private final String shebanline;
    private String fileType;

    public ShebanglineParser(String shebanline) {
        this.shebanline = shebanline;
    }

    @Override
    public ShebanglineParser parse() {
        if (ShebangUtils.isShebang(shebanline)) {
            String interpriterName = ShebangUtils.getInterpriterName(shebanline);
            if (interpriterName != null && !interpriterName.isEmpty()) {
                fileType = interpriterName;
            }
        }
        return this;
    }

    @Override
    public Result getResult() {
        if (fileType == null) {
            return NoExtMIMEResolverParser.UNKOWN_RESULT;
        }
        return new ShebanglineResult(MimeType.valueOfFileType(fileType));
    }

    private static class ShebanglineResult implements Result {

        private final MimeType mimeType;

        public ShebanglineResult(MimeType mimeType) {
            this.mimeType = mimeType;
        }

        @Override
        public String getMimeType() {
            return mimeType.getMimeType();
        }

    }
}

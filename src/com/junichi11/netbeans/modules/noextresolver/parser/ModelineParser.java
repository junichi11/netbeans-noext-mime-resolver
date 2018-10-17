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
import com.junichi11.netbeans.modules.noextresolver.utils.NoMIMEResolverUtils;
import com.junichi11.netbeans.modules.noextresolver.parser.spi.NoExtMIMEResolverParser;

public class ModelineParser implements NoExtMIMEResolverParser {

    private final String modeline;
    private String fileType;
    private static final String FILE_TYPE_OPTION_LONG = "filetype="; // NOI18N
    private static final String FILE_TYPE_OPTION = "ft="; // NOI18N
    private static final String VI = "vi:";
    private static final String VIM = "vim:";
    private static final String EX = "ex:";

    public ModelineParser(String modeline) {
        assert modeline != null;
        this.modeline = modeline;
    }

    @Override
    public ModelineParser parse() {
        if (NoMIMEResolverUtils.isCommentLine(modeline)) {
            String line = modeline.replace(":", " ").trim(); // NOI18N
            String[] options = line.split(" "); // NOI18N
            for (String option : options) {
                if (isFileTypeOption(option)) {
                    fileType = option.replace(FILE_TYPE_OPTION, "") // NOI18N
                            .replace(FILE_TYPE_OPTION_LONG, ""); // NOI18N
                    break;
                }
            }
        }
        return this;
    }

    @Override
    public Result getResult() {
        if (fileType == null) {
            return NoExtMIMEResolverParser.UNKOWN_RESULT;
        }
        return new ModelineResult(MimeType.valueOfFileType(fileType));
    }

    private static boolean isFileTypeOption(String option) {
        return option.startsWith(FILE_TYPE_OPTION)
                || option.startsWith(FILE_TYPE_OPTION_LONG);
    }

    static boolean support(String line) {
        return NoMIMEResolverUtils.isCommentLine(line)
                && (line.contains(VIM) || line.contains(VI) || line.contains(EX));
    }

    private static class ModelineResult implements Result {

        private final MimeType mimeType;

        public ModelineResult(MimeType mimeType) {
            this.mimeType = mimeType;
        }

        @Override
        public String getMimeType() {
            return mimeType.getMimeType();
        }

    }
}

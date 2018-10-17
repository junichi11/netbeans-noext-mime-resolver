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

import com.junichi11.netbeans.modules.noextresolver.parser.spi.NoExtMIMEResolverParserProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.openide.util.Lookup;
import com.junichi11.netbeans.modules.noextresolver.parser.spi.NoExtMIMEResolverParser;

/**
 *
 * @author junichi11
 */
public final class ParserFactory {

    private ParserFactory() {
    }

    public static List<NoExtMIMEResolverParser> createParsers(String line) {
        List<NoExtMIMEResolverParser> parsers = new ArrayList<>();
        Collection<? extends NoExtMIMEResolverParserProvider> providers = Lookup.getDefault().lookupAll(NoExtMIMEResolverParserProvider.class);
        for (NoExtMIMEResolverParserProvider provider : providers) {
            if (!provider.support(line)) {
                continue;
            }
            NoExtMIMEResolverParser parser = provider.getParser(line);
            if (parser == null) {
                continue;
            }
            parsers.add(parser);
        }
        return parsers;
    }
}

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
package com.junichi11.netbeans.modules.noextresolver.utils;

/**
 *
 * @author junichi11
 */
public final class ShebangUtils {

    private static final String USR_BIN_ENV = "/usr/bin/env"; // NOI18N

    private ShebangUtils() {
    }

    /**
     * Check whether a line isThis shebang format (starts with "#!").
     *
     * @param line first line of a file
     * @return {@code true} if it's shebang format, {@code false} otherwise
     */
    public static boolean isShebang(String line) {
        if (line == null) {
            return false;
        }
        return line.startsWith("#!"); // NOI18N
    }

    /**
     * Get interpreter name.
     *
     * @param line shebang line
     * @return interpreter name if line is shebang, empty string otherwise
     */
    public static String getInterpriterName(String line) {
        if (line == null || !isShebang(line)) {
            return ""; // NOI18N
        }

        String path = removeShebang(line);
        String interpreter;
        if (path.contains(USR_BIN_ENV)) {
            interpreter = path.replace(USR_BIN_ENV, "").trim(); // NOI18N
        } else {
            interpreter = path;
        }
        String[] split = interpreter.split(" "); // NOI18N
        interpreter = split[0];
        if (interpreter.contains("/")) { // NOI18N
            int lastIndexOfSlash = interpreter.lastIndexOf("/"); // NOI18N
            interpreter = interpreter.substring(lastIndexOfSlash)
                    .replace("/", ""); // NOI18N

        }
        return interpreter;
    }

    /**
     * Remove shebang (#!).
     *
     * @param line sheban line
     * @return line that "#!" is removed
     */
    private static String removeShebang(String line) {
        if (line == null) {
            return ""; // NOI18N
        }
        return line.replace("#!", "").trim(); // NOI18N
    }

}

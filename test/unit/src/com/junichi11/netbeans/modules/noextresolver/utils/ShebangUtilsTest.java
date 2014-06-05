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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.netbeans.junit.NbTestCase;

/**
 *
 * @author junichi11
 */
public class ShebangUtilsTest extends NbTestCase {

    public ShebangUtilsTest(String name) {
        super(name);
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

    /**
     * Test of isShebang method, of class ShebangUtils.
     */
    @Test
    public void testIsShebang() {
        assertEquals(true, ShebangUtils.isShebang("#!/bin/bash"));

        assertEquals(false, ShebangUtils.isShebang("!/bin/bash"));
        assertEquals(false, ShebangUtils.isShebang(""));
        assertEquals(false, ShebangUtils.isShebang(null));
    }

    /**
     * Test of getInterpriterName method, of class ShebangUtils.
     */
    @Test
    public void testGetInterpriterName() {
        assertEquals("bash", ShebangUtils.getInterpriterName("#! bash"));
        assertEquals("bash", ShebangUtils.getInterpriterName("#!/bin/bash"));
        assertEquals("bash", ShebangUtils.getInterpriterName("#!/bin/bash -v some"));
        assertEquals("ruby", ShebangUtils.getInterpriterName("#!/usr/bin/env ruby"));

        assertEquals("", ShebangUtils.getInterpriterName("/usr/bin/env bash"));
        assertEquals("", ShebangUtils.getInterpriterName(null));

    }

}

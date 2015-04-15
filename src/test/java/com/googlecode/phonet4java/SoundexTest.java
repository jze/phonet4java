/*
 * SoundexTest.java
 * 
 * Copyright (c) 2009, Jesper Zedlitz. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.googlecode.phonet4java;

import com.googlecode.phonet4java.Soundex;

import junit.framework.TestCase;


/**
 * @author Jesper Zedlitz &lt;jze@informatik.uni-kiel.de&gt;
 *
 */
public class SoundexTest extends TestCase {
    Soundex soundex = new Soundex();

    public void testUppercase() throws Exception {
        assertEquals("Z343", soundex.code("Zedlitz"));
    }

    public void testLowercase() throws Exception {
        assertEquals("Z343", soundex.code("zedlitz"));
    }

    public void testEmpty() throws Exception {
        assertEquals("Z000", soundex.code(""));
    }

    public void testNull() throws Exception {
        assertEquals("Z000", soundex.code(null));
    }

    public void testUnknownCharacter() throws Exception {
        assertEquals("Z000", soundex.code("$"));
    }

    public void testUmlauts() {
        assertEquals("O200", soundex.code("ößüä"));
    }
}

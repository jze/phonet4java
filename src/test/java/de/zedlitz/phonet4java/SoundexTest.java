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
package de.zedlitz.phonet4java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author Jesper Zedlitz &lt;jze@informatik.uni-kiel.de&gt;
 */
public class SoundexTest extends AbstractTestBase<Soundex> {
    private final Soundex soundex = new Soundex();

    @Override
    Soundex getCoder() {
        return soundex;
    }

    @Test
    public void testUppercase() throws Exception {
        assertEquals("Z343", soundex.code("Zedlitz"));
    }

    @Test
    public void testLowercase() throws Exception {
        assertEquals("Z343", soundex.code("zedlitz"));
    }

    @Test
    public void testEmpty() throws Exception {
        assertEquals("Z000", soundex.code(""));
    }

    @Test
    public void testNull() throws Exception {
        assertEquals("Z000", soundex.code(null));
    }

    @Test
    public void testUnknownCharacter() throws Exception {
        assertEquals("Z000", soundex.code("$"));
    }

    @Test
    public void testUmlauts() {
        assertEquals("O200", soundex.code("ößüä"));
    }
}

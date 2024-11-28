/*
 * SoundexRefinedTest.java
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
public class SoundexRefinedTest extends AbstractTestBase<SoundexRefined> {

    private final SoundexRefined phonet = new SoundexRefined();

    @Override
    SoundexRefined getCoder() {
        return phonet;
    }

    @Test
    public void testEmpty() {
        assertEquals("Z000", phonet.code(""));
    }

    @Test
    public void testNull() {
        assertEquals("Z000", phonet.code(null));
    }

    @Test
    public void testUnkownCharacter() {
        assertEquals("Z000", phonet.code("$"));
    }

    @Test
    public void testCode() {
        assertEquals("Z6765", phonet.code("Zedlitz"));
        assertEquals("K8000",phonet.code("Kim"));
        assertEquals("B6000",phonet.code("Boot"));
    }

}

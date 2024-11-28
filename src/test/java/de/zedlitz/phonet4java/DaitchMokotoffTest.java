/*
 * DaitchMokotoffTest.java
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
public class DaitchMokotoffTest extends AbstractTestBase<DaitchMokotoff> {
    private final DaitchMokotoff phonet = new DaitchMokotoff();

    @Override
    DaitchMokotoff getCoder() {
        return phonet;
    }

    @Test
    public void testEmpty() {
        assertEquals("000000", phonet.code(""));
    }

    @Test
    public void testNull() {
        assertEquals("000000", phonet.code(null));
    }

    @Test
    public void testSpace() {
        assertEquals("000000", phonet.code(" "));
    }

    @Test
    public void testUnkownCharacter() {
        assertEquals("000000", phonet.code("$"));
    }

    @Test
    public void code() {
        assertEquals("097500", phonet.code("Auerbach"));
        assertEquals("097500", phonet.code("OHRBACH"));
        assertEquals("874400", phonet.code("LIPSHITZ"));
        assertEquals("486740", phonet.code("SZLAMAWICZ"));
        assertEquals("486740", phonet.code("SHLAMOVITZ"));
        assertEquals("279457", phonet.code("Szczypiorskowski"));

        checkSimilarCoding("876450", new String[]{"LEWINSKY", "LEVINSKI", "Levinsky"});
    }


}

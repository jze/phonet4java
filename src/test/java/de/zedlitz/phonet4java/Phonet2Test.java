/*
 * Phonet2Test.java
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
public class Phonet2Test extends AbstractTestBase<Phonet2> {
    private final Phonet2 coder = new Phonet2();

    @Override
    public Phonet2 getCoder() {
        return coder;
    }

    @Test
    public void testZedlitz() throws Exception {
        assertEquals("ZETLIZ", coder.code("Zedlitz"));
    }

    @Test
    public void testBremerhaven() throws Exception {
        assertEquals("BRENAFN", coder.code("Bremerhaven"));
    }

    @Test
    public void testSchönberg() {
        assertEquals("ZÖNBAK", coder.code("Schönberg"));
    }

    @Test
    public void testHamburgerHafen() {
        assertEquals("ANBURKA AFN", coder.code("Hamburger Hafen"));
    }


    @Test
    public void testZiegler() {
        assertEquals("ZIKLA", coder.code("Ziegler"));
    }

    @Test
    public void testScherer() {
        assertEquals("ZERA", coder.code("Scherer"));
    }

    @Test
    public void testJansen() {
        assertEquals("IANZN", coder.code("Jansen"));
    }

    @Test
    public void testEberhardt() {
        assertEquals("EBART", coder.code("Eberhardt"));
    }

    @Test
    public void testGottschalk() {
        assertEquals("KUZALK", coder.code("Gottschalk"));
    }

    @Test
    public void testBrueckmann() {
        assertEquals("BRIKNAN", coder.code("Brückmann"));
    }

    @Test
    public void testBlechschmidt() {
        assertEquals("BLEKZNIT", coder.code("Blechschmidt"));
    }

    @Test
    public void testKolodziej() {
        assertEquals("KULUTZI", coder.code("Kolodziej"));
    }

    @Test
    public void testKrausse() {
        assertEquals("KRAUZE", coder.code("Krauße"));
    }


}

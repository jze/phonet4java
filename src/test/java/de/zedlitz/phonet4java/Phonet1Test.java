/*
 * Phonet1Test.java
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
public class Phonet1Test extends AbstractTestBase<Phonet1> {
    private final Phonet1 coder = new Phonet1();

    @Override
    public Phonet1 getCoder() {
        return coder;
    }

    @Test
    public void testEmpty() {
        assertEquals("", coder.code(""));
    }

    @Test
    public void testWhitespcae() {
        assertEquals(" ", coder.code(" "));
    }

    @Test
    public void testUnderscore() {
        assertEquals("_", coder.code("_"));
    }

    @Test
    public void testNull() {
        assertEquals("", coder.code(null));
    }

    @Test
    public void testWithTrace() {
        coder.trace = true;
        assertEquals("ZETLIZ", coder.code("Zedlitz"));
    }

    @Test
    public void testZedlitz() {
        assertEquals("ZETLIZ", coder.code("Zedlitz"));
    }

    @Test
    public void testBremerhaven() {
        assertEquals("BREMAHAFN", coder.code("Bremerhaven"));
    }

    @Test
    public void testHamburgerHafen() {
        assertEquals("HAMBURGA HAFN", coder.code("Hamburger Hafen"));
    }

    @Test
    public void testJesper() {
        assertEquals("IESPA", coder.code("Jesper"));
    }

    @Test
    public void testElisabeth() {
        assertEquals("ELISABET", coder.code("elisabeth"));
    }

    @Test
    public void testElisabet() {
        assertEquals("ELISABET", coder.code("elisabet"));
    }

    @Test
    public void testZiegler() {
        assertEquals("ZIKLA", coder.code("Ziegler"));
    }

    @Test
    public void testScherer() {
        assertEquals("SHERA", coder.code("Scherer"));
    }

    @Test
    public void testBartels() {
        assertEquals("BARTLS", coder.code("Bartels"));
    }

    @Test
    public void testJansen() {
        assertEquals("IANSN", coder.code("Jansen"));
    }

    @Test
    public void testSievers() {
        assertEquals("SIWAS", coder.code("Sievers"));
    }

    @Test
    public void testMichels() {
        assertEquals("MICHLS", coder.code("Michels"));
    }

    @Test
    public void testEwers() {
        assertEquals("EWERS", coder.code("Ewers"));
    }

    @Test
    public void testEvers() {
        assertEquals("EWERS", coder.code("Evers"));
    }

    @Test
    public void testWessels() {
        assertEquals("WESLS", coder.code("Wessels"));
    }

    @Test
    public void testGottschalk() {
        assertEquals("GOSHALK", coder.code("Gottschalk"));
    }

    @Test
    public void testBrueckmann() {
        assertEquals("BRÜKMAN", coder.code("Brückmann"));
    }

    @Test
    public void testBlechschmidt() {
        assertEquals("BLECHSHMIT", coder.code("Blechschmidt"));
    }

    @Test
    public void testKolodziej() {
        assertEquals("KOLOTZI", coder.code("Kolodziej"));
    }

    @Test
    public void testKrausse() {
        assertEquals("KRAUSE", coder.code("Krauße"));
    }

    @Test
    public void testCachel() {
        assertEquals("KESHL", coder.code("Cachel"));
    }

    @Test
    public void testWrongOe() {
        assertEquals("SHӧNBERGA", coder.code("Schӧnberger"));
    }

    @Test
    public void testSchulzIII() {
        assertEquals("SHULS Ⅲ", coder.code("Schulz Ⅲ"));
    }
}

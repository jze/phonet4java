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
package com.googlecode.phonet4java;

import junit.framework.TestCase;


/**
 * @author Jesper Zedlitz &lt;jze@informatik.uni-kiel.de&gt;
 *
 */
public class Phonet1Test extends TestCase {
    Phonet1 coder = new Phonet1();

    public void testEmpty() {
	assertEquals("", coder.code(""));
    }	

    public void testNull() {
	assertEquals("", coder.code(null));
    }	

    public void testWithTrace() {
	coder.trace = true;
	assertEquals("ZETLIZ", coder.code("Zedlitz"));
    }
    
    public void testZedlitz() {
        assertEquals("ZETLIZ", coder.code("Zedlitz"));
    }

    public void testBremerhaven() throws Exception {
        assertEquals("BREMAHAFN", coder.code("Bremerhaven"));
    }

    public void testHamburgerHafen() {
        assertEquals("HAMBURGA HAFN", coder.code("Hamburger Hafen"));
    }

    public void testJesper() {
        assertEquals("IESPA", coder.code("Jesper"));
    }

    public void testElisabeth() {
        assertEquals("ELISABET", coder.code("elisabeth"));
    }

    public void testElisabet() {
        assertEquals("ELISABET", coder.code("elisabet"));
    }

    public void testZiegler() {
        assertEquals("ZIKLA", coder.code("Ziegler"));
    }

    public void testScherer() {
        assertEquals("SHERA", coder.code("Scherer"));
    }

    public void testBartels() {
        assertEquals("BARTLS", coder.code("Bartels"));
    }

    public void testJansen() {
        assertEquals("IANSN", coder.code("Jansen"));
    }

    public void testSievers() {
        assertEquals("SIWAS", coder.code("Sievers"));
    }

    public void testMichels() {
        assertEquals("MICHLS", coder.code("Michels"));
    }

    public void testEwers() {
        assertEquals("EWERS", coder.code("Ewers"));
    }

    public void testEvers() {
        assertEquals("EWERS", coder.code("Evers"));
    }
    public void testWessels() {
	assertEquals("WESLS", coder.code("Wessels"));
    }
    
    public void testGottschalk() {
	assertEquals("GOSHALK", coder.code("Gottschalk"));
    }
    public void testBrückmann() {
	assertEquals("BRÜKMAN", coder.code("Brückmann"));
    }
    	
    public void testBlechschmidt() {
	assertEquals("BLECHSHMIT", coder.code("Blechschmidt"));
    }

    public void testKolodziej() {
	assertEquals("KOLOTZI", coder.code("Kolodziej"));
    }	
    public void testKrauße() {
	assertEquals("KRAUSE", coder.code("Krauße"));
    }	
    public void testCachel() {
	assertEquals("KESHL", coder.code("Cachel"));
    }
}

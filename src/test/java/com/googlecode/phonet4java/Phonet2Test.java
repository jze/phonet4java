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
package com.googlecode.phonet4java;

import com.googlecode.phonet4java.Coder;

import junit.framework.TestCase;


/**
 * @author Jesper Zedlitz &lt;jze@informatik.uni-kiel.de&gt;
 *
 */
public class Phonet2Test extends TestCase {
    Coder coder = new Phonet2();

    public void testZedlitz() throws Exception {
        assertEquals("ZETLIZ", coder.code("Zedlitz"));
    }

    public void testBremerhaven() throws Exception {
        assertEquals("BRENAFN", coder.code("Bremerhaven"));
    }
    public void testSchönberg() {
	assertEquals("ZÖNBAK",  coder.code("Schönberg"));
    }
    
    public void testHamburgerHafen() {
	assertEquals("ANBURKA AFN", coder.code("Hamburger Hafen"));
    }
    

    public void testZiegler() {
	assertEquals("ZIKLA", coder.code("Ziegler"));
    }
    
    public void testScherer() {
	assertEquals("ZERA", coder.code("Scherer"));
    }
    
    public void testJansen() {
	assertEquals("IANZN", coder.code("Jansen"));
    }
    
    public void testEberhardt() {
	assertEquals("EBART", coder.code("Eberhardt"));
    }
    
    public void testGottschalk() {
	assertEquals("KUZALK", coder.code("Gottschalk"));
    }
    
    public void testBrückmann() {
	assertEquals("BRIKNAN", coder.code("Brückmann"));
    }public void testBlechschmidt() {
	assertEquals("BLEKZNIT", coder.code("Blechschmidt"));
    }
    public void testKolodziej() {
	assertEquals("KULUTZI", coder.code("Kolodziej"));
    }	
    public void testKrauße() {
	assertEquals("KRAUZE", coder.code("Krauße"));
    }	

    
}

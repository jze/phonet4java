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
package com.googlecode.phonet4java;

import com.googlecode.phonet4java.SoundexRefined;

import junit.framework.TestCase;

/**
 * @author Jesper Zedlitz &lt;jze@informatik.uni-kiel.de&gt;
 *
 */
public class SoundexRefinedTest extends TestCase{
    
    SoundexRefined phonet = new SoundexRefined();
    
    public void testEmpty() {
	assertEquals("Z000", phonet.code(""));
    }
    
    public void testNull() {
	assertEquals("Z000", phonet.code(null));
    }

    public void testUnkownCharacter() {
	assertEquals("Z000", phonet.code("$"));
    }

    public void testZedlitz() {
	
    assertEquals("Z6765", phonet.code("Zedlitz"));
    }
}

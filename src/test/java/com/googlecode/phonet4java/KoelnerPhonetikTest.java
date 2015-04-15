/*
 * KoelnerPhonetikTest.java
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

import com.googlecode.phonet4java.KoelnerPhonetik;

import junit.framework.TestCase;


/**
 * @author Jesper Zedlitz &lt;jze@informatik.uni-kiel.de&gt;
 *
 */
public class KoelnerPhonetikTest extends TestCase {
    KoelnerPhonetik phonet = new KoelnerPhonetik();

    
    public void testEmpty() throws Exception {
        assertEquals("", phonet.code(""));
    }

    public void testNull() throws Exception {
        assertEquals("", phonet.code(null));
    }

    public void testUnknownCharacter() throws Exception {
        assertEquals("", phonet.code("$"));
    }
    
    public void testDoubleName() throws Exception {
        assertEquals("65752682", phonet.code("Müller-Lüdenscheidt"));
    }
    
    public void testBreschnew() {
	assertEquals("17863", phonet.code("Breschnew"));
    }
    
    public void testMoritzMüller() {
	assertEquals("678657", phonet.code("Moritz Müller"));
    }
    public void testMorizMüler() {
	assertEquals("678657", phonet.code("Moriz Müler"));
    }
    public void testLauraMayer() {
	assertEquals("5767", phonet.code("Laura Mayer"));
    }
    public void testLauraMeier() {
	assertEquals("5767", phonet.code("Laura Meier"));
    }

    public void testCzerny() {
	assertEquals("876", phonet.code("Czerny"));
    }

    public void testScoobydoo() {
	assertEquals("8412", phonet.code("SCOOBYDOO"));
    }
 
    public void testSkoobydoo() {
	assertEquals("8412", phonet.code("SKOOBYDOO"));
    }
    public void testPhoto() {
	assertEquals("32", phonet.code("Photo"));
    }
    public void testChina() {
	assertEquals("46", phonet.code("China"));
    }
    public void testPapst() {
	assertEquals("1182", phonet.code("Papst"));
    }
}

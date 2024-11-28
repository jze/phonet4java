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
package de.zedlitz.phonet4java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author Jesper Zedlitz &lt;jze@informatik.uni-kiel.de&gt;
 */
public class KoelnerPhonetikTest extends AbstractTestBase<KoelnerPhonetik> {
    private final KoelnerPhonetik phonet = new KoelnerPhonetik();

    @Override
    KoelnerPhonetik getCoder() {
        return phonet;
    }

    @Test
    public void testEmpty() throws Exception {
        assertEquals("", phonet.code(""));
    }

    @Test
    public void testNull() throws Exception {
        assertEquals("", phonet.code(null));
    }

    @Test
    public void testUnknownCharacter() throws Exception {
        assertEquals("", phonet.code("$"));
    }

    @Test
    public void testDoubleName() throws Exception {
        assertEquals("65752682", phonet.code("Müller-Lüdenscheidt"));
    }

    @Test
    public void testBreschnew() {
        assertEquals("17863", phonet.code("Breschnew"));
    }

    @Test
    public void testMoritzMueller() {
        checkSimilarCoding("678657", new String[]{"Moritz Müller", "Moriz Müler"});
    }

    @Test
    public void testLauraMayer() {
        checkSimilarCoding("5767", new String[]{"Laura Mayer", "Laura Meier"});
    }

    @Test
    public void testCanada() {
        checkSimilarCoding("462", new String[]{"Kanada", "Canada"});
    }

    @Test
    public void testCzerny() {
        assertEquals("876", phonet.code("Czerny"));
    }

    @Test
    public void testSchaefer() {
        assertEquals("837", phonet.code("Schäfer"));
    }

    @Test
    public void testHeinzClassen() {
        assertEquals("0684586", phonet.code("Heinz Classen"));
    }

    @Test
    public void testEdgeCases() {
        final String[][] data = {
                {"Aco", "04"},
                {"Acxi", "048"},
                {"Ace", "08"},
        };
        checkCoding(data);
    }

    @Test
    public void testRealExamples() {
        final String[][] data = {
                {"Arbeitsamt", "071862"},
                {"Becker", "147"},
                {"Breschnew", "17863"},
                {"Café", "43"},
                {"China", "46"},
                {"Claude", "452"},
                {"Code", "42"},
                {"Christstollen", "478256"},
                {"Deutsch", "28"},
                {"Deutz", "28"},
                {"Eberhard", "01772"},
                {"Eberhardt", "01772"},
                {"Fischer", "387"},
                {"Foto", "32"},
                {"Gustav", "4823"},
                {"Haithabu", "021"},
                {"Hamburg", "06174"},
                {"Hannover", "0637"},
                {"Hoffmann", "0366"},
                {"Holzbau", "0581"},
                {"Jürgen", "0746"},
                {"Juergen", "0746"},
                {"Matsch", "68"},
                {"Matz", "68"},
                {"Müller", "657"},
                {"Mönchengladbach", "664645214"},
                {"Papst", "1182"},
                {"Peter", "127"},
                {"Pharma", "376"},
                {"Photo", "32"},
                {"Qualle", "45"},
                {"Schäfer", "837"},
                {"Schmidt", "862"},
                {"Schneider", "8627"},
                {"Wagner", "3467"},
                {"Weber", "317"},
                {"Wikipedia", "3412"},
                {"Xanthippe", "48621"},
                {"Zacharias", "8478"}};
        checkCoding(data);
    }
}

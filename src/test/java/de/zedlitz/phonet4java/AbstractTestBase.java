/*
  Copyright (C) 2016 Jesper Zedlitz <j.zedlitz@email.uni-kiel.de>
 
  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU Affero General Public License as published
  by the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Affero General Public License for more details.

   You should have received a copy of the GNU Affero General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.zedlitz.phonet4java;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Jesper Zedlitz <j.zedlitz@email.uni-kiel.de>
 */
public abstract class AbstractTestBase<C extends Coder> {
    Coder coder;

    /**
     * Return an instance of the Coder under test.
     */
    abstract C getCoder();

    /**
     * Run a couple of tests. The parameter is an array of String pairs (input, expected)
     */
    protected void checkCoding(String[][] testData) {
        Coder coder = getCoder();

        for (String[] pair : testData) {
            if (pair.length != 2) {
                throw new IllegalArgumentException("Test data is not a pair.");
            }

            String input = pair[0];
            String expected = pair[1];
            assertEquals(expected, coder.code(input));
        }
    }

    /**
     * Run the coding of a couple of input strings and compare with the specified expected result.
     */
    protected void checkSimilarCoding(String expected, String[] inputs) {
        Coder coder = getCoder();
        for (String input : inputs) {
            assertEquals(expected, coder.code(input));
        }

    }
}

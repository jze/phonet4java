/*
 * KoelnerPhonetik.java
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


/**
 *
 * An implementation of the algorithm as described in H.J. Postel, "Die Kölner Phonetik
 * Ein Verfahren zu Identifizierung von Personennamen auf der Grundlage der Gestaltanalyse",
 * IBM-Nachrichten 19 (1969), p. 925-931
 *
 * See http://de.wikipedia.org/wiki/Kölner_Phonetik
 *
 * @author Jesper Zedlitz &lt;jze@informatik.uni-kiel.de&gt;
 *
 */
public class KoelnerPhonetik implements Coder {
    /**
      * @see Coder#code(java.lang.String)
      */
    public String code(final String input) {
        if ((input == null) || (input.length() == 0)) {
            return "";
        }

        String word = input.toLowerCase();

        StringBuilder code = new StringBuilder();
        boolean beginningOfWord = true;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if ((c == 'a') || (c == 'e') || (c == 'i') || (c == 'j') ||
                    (c == 'o') || (c == 'u') || (c == 'y') || (c == 'ä') ||
                    (c == 'ö') || (c == 'ü')) {
                code.append(0);
            } else if (c == 'b') {
                code.append(1);
            } else if ((c == 'f') || (c == 'v') || (c == 'w')) {
                code.append(3);
            } else if ((c == 'g') || (c == 'k') || (c == 'q')) {
                code.append(4);
            } else if (c == 'l') {
                code.append(5);
            } else if ((c == 'm') || (c == 'n')) {
                code.append(6);
            } else if (c == 'r') {
                code.append(7);
            } else if ((c == 's') || (c == 'z') || (c == 'ß')) {
                code.append(8);
            } else if ((c == 'd') || (c == 't')) {
                if (isNextLetter(word, i, 'c') || isNextLetter(word, i, 'ß') ||
                        isNextLetter(word, i, 'z') ||
                        isNextLetter(word, i, 's')) {
                    code.append(8);
                } else {
                    code.append(2);
                }
            } else if (c == 'p') {
                if (isNextLetter(word, i, 'h')) {
                    code.append(3);
                } else {
                    code.append(1);
                }
            } else if (c == 'x') {
                if (isPreviousLetter(word, i, 'c') ||
                        isPreviousLetter(word, i, 'k') ||
                        isPreviousLetter(word, i, 'q')) {
                    code.append(8);
                } else {
                    code.append(4);
                    code.append(8);
                }
            } else if (c == 'c') {
                if (beginningOfWord) {
                    if (isNextLetter(word, i, 'a') ||
                            isNextLetter(word, i, 'h') ||
                            isNextLetter(word, i, 'k') ||
                            isNextLetter(word, i, 'l') ||
                            isNextLetter(word, i, 'o') ||
                            isNextLetter(word, i, 'q') ||
                            isNextLetter(word, i, 'r') ||
                            isNextLetter(word, i, 'u') ||
                            isNextLetter(word, i, 'x')) {
                        code.append(4);
                    } else {
                        code.append(8);
                    }
                } else {
                    if (isPreviousLetter(word, i, 's') ||
                            isPreviousLetter(word, i, 'z') ||
                            isPreviousLetter(word, i, 'ß')) {
                        code.append(8);
                    } else if (isNextLetter(word, i, 'a') ||
                            isNextLetter(word, i, 'h') ||
                            isNextLetter(word, i, 'k') ||
                            isNextLetter(word, i, 'l') ||
                            isNextLetter(word, i, 'o') ||
                            isNextLetter(word, i, 'q') ||
                            isNextLetter(word, i, 'r') ||
                            isNextLetter(word, i, 'u') ||
                            isNextLetter(word, i, 'x')) {
                        code.append(4);
                    } else {
                        code.append(8);
                    }
                }
            } /*else if ((c == ' ') || (c == '-') || (c == '/') || (c == ',')) {
                beginningOfWord = true;
            }   */

            beginningOfWord = false;
        }

        // remove duplicates
        int i = 0;
        char lastChar = ' ';

        while (i < code.length()) {
            char c = code.charAt(i);

            if (c == lastChar) {
                code.deleteCharAt(i);
            } else {
                lastChar = c;
                i++;
            }
        }

        // remove zeros
        i = 1;

        while (i < code.length()) {
            if (code.charAt(i) == '0') {
                code.deleteCharAt(i);
            } else {
                i++;
            }
        }

        return code.toString();
    }

    /**
     * Check if the letter at positoin (i+1) == letter.
        */
    private boolean isNextLetter(final String s, final int i, final char letter) {
        return s.length() > (i + 1) && s.charAt(i + 1) == letter;

    }

    /**
     * Check if the letter at positoin (i-1) == letter.
      */
    private boolean isPreviousLetter(final String s, final int i,
        final char letter) {
        return i >= 1 && s.charAt(i - 1) == letter;

    }
}

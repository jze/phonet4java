/*
 * DaitchMokotoff.java
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


/**
 * @author Jesper Zedlitz &lt;jze@informatik.uni-kiel.de&gt;
 *
 */
public class DaitchMokotoff implements Coder {
    private static final String[] daimok_rules =
        {
            "ZSCH", "4", "4", "4", "ZSH", "4", "4", "4", "TCH", "4", "4", "4",
            "TTCH", "4", "4", "4", "TTSCH", "4", "4", "4", "TH", "3", "3", "3",
            "TRZ", "4", "4", "4", "TRS", "4", "4", "4", "TSCH", "4", "4", "4",
            "TSH", "4", "4", "4", "TC", "4", "4", "4", "SCHTSH", "2", "4", "4",
            "SCHTCH", "2", "4", "4", "SCHTSCH", "2", "4", "4", "SHTCH", "2", "4",
            "4", "SHCH", "2", "4", "4", "SHTSH", "2", "4", "4", "SHT", "2", "43",
            "43", "SCHT", "2", "43", "43", "SCHD", "2", "43", "43", "STCH", "2",
            "4", "4", "STSCH", "2", "4", "4", "STRZ", "2", "4", "4", "STRS", "2",
            "4", "4", "STSH", "2", "4", "4", "SZCZ", "2", "4", "4", "SZCS", "2",
            "4", "4", "SZT", "2", "43", "43", "SHD", "2", "43", "43", "SZD", "2",
            "43", "43", "SD", "2", "43", "43", "STSCH", "2", "4", "4", "SH", "4",
            "4", "4", "SCH", "4", "4", "4", "SC", "2", "4", "4", "ZDZH", "2",
            "4", "2", "ZHDZH", "2", "4", "4", "ZDZ", "2", "4", "4", "ZHD", "2",
            "43", "43", "ZD", "2", "43", "43", "ZH", "4", "4", "4", "ZS", "4",
            "4", "4", "AI", "0", "1", "", "AJ", "0", "1", "", "AY", "0", "1", "",
            "AU", "0", "7", "", "B", "7", "7", "7", "CHS", "5", "54", "54",
            "TCH", "4", "4", "4", "CH", "5", "5", "5", "CK", "5", "5", "5",
            "CZS", "4", "4", "4", "CSZ", "4", "4", "4", "CZ", "4", "4", "4", "C",
            "5", "5", "5", "DRZ", "4", "4", "4", "DRS", "4", "4", "4", "DSZ",
            "4", "4", "4", "DSH", "4", "4", "4", "DS", "4", "4", "4", "DZH", "4",
            "4", "4", "DZS", "4", "4", "4", "DZ", "4", "4", "4", "DT", "3", "3",
            "3", "D", "3", "3", "3", "EI", "0", "1", "", "EJ", "0", "1", "",
            "EY", "0", "1", "", "EU", "1", "1", "", "IE", "1", "", "", "UE", "0",
            "", "", "E", "0", "", "", "FB", "7", "7", "7", "F", "7", "7", "7",
            "G", "5", "5", "5", "H", "5", "5", "", "IA", "1", "", "", "IO", "1",
            "", "", "IU", "1", "", "", "OI", "0", "1", "", "OJ", "0", "1", "",
            "UI", "0", "1", "", "UJ", "0", "1", "", "I", "0", "", "", "J", "1",
            "1", "1", "KS", "5", "54", "54", "KH", "5", "5", "5", "K", "5", "5",
            "5", "L", "8", "8", "8", "MN", "66", "66", "66", "NM", "66", "66",
            "66", "M", "6", "6", "6", "N", "6", "6", "6", "OY", "0", "1", "",
            "O", "0", "", "", "PF", "7", "7", "7", "PH", "7", "7", "7", "P", "7",
            "7", "7", "Q", "5", "5", "5", "RZ", "94", "94", "94", "RS", "94",
            "94", "94", "R", "9", "9", "9", "ST", "2", "43", "43", "SZ", "4",
            "4", "4", "S", "4", "4", "4", "TTS", "4", "4", "4", "TTSZ", "4", "4",
            "4", "TS", "4", "4", "4", "TTZ", "4", "4", "4", "TZS", "4", "4", "4",
            "TSZ", "4", "4", "4", "TZ", "4", "4", "4", "T", "3", "3", "3", "UY",
            "0", "1", "", "V", "7", "7", "7", "W", "7", "7", "7", "X", "5", "54",
            "54", "Y", "1", "", "", "Z", "4", "4", "4", "A", "0", "", "", "ß",
            "4", "4", "4", "Ä", "0", "", "", "Ö", "0", "", "", "Ü", "0", "", "",
            " ", "", "", "", "", "", "", ""
        };

    /**
      * @see com.googlecode.phonet4java.Coder#code(java.lang.String)
      */
    public String code(final String input) {
        if ((input == null) || input.trim().isEmpty()) {
            return "000000";
        }

        int i;
        int n = 0;
        int pos = 0;
        String sound;
        String lastSound = null;
        StringBuffer result = new StringBuffer();

        String in = input.toUpperCase() + "*";

        while ((daimok_rules[n].length() > 0) && (in.length() > 0)) {
            if (in.regionMatches(pos, daimok_rules[n], 0,
                        daimok_rules[n].length())) {
                /* check the position of the sound */
                if (pos == 0) {
                    /* the beginning */
                    sound = daimok_rules[n + 1];
                    pos = pos + daimok_rules[n].length();
                } else {
                    pos = pos + daimok_rules[n].length();

                    if ((in.charAt(pos) == 'A') || (in.charAt(pos) == 'E') ||
                            (in.charAt(pos) == 'I') || (in.charAt(pos) == 'O') ||
                            (in.charAt(pos) == 'U')) {
                        /* vor einem Vokal */
                        sound = daimok_rules[n + 2];
                    } else {
                        sound = daimok_rules[n + 3];
                    }
                }

                if (!sound.equals(lastSound)) {
                    result.append(sound);
                    lastSound = sound;
                }

                n = 0;
            } else {
                n = n + 4; /* jump to next rule */
            }
        }

        if (result.length() > 6) {
            result.setLength(6);
        } else {
            for (i = result.length(); i < 6; i++) {
                result.append(0);
            }
        }

        return result.toString();
    }
}

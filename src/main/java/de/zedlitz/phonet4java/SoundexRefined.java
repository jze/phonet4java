/*
 * SoundexRefined.java
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
 * @author Jesper Zedlitz &lt;jze@informatik.uni-kiel.de&gt;
 *
 */
public class SoundexRefined implements Coder {
    /**
      * @see Coder#code(java.lang.String)
      */
    public String code(final String input) {
        if ((input == null) || input.trim().length() == 0) {
            return "Z000";
        }

        int[] code =
            {
                0, 1, 3, 6, 0, 2, 4, 0, 0, 4, 3, 7, 8, 8, 0, 1, 5, 9, 3, 6, 0, 2,
                0, 5, 0, 5
            };

        /* a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z */
        char[] key = { 'Z', '0', '0', '0', '0' };
        char ch;
        int last;
        int count;
        int scount;

        String in =
            input.toUpperCase().replace('Ä', 'A').replace('Ö', 'O')
                 .replace('Ü', 'U').replace('ß', 's');

        try {
            key[0] = in.charAt(0);
            last = code[key[0] - 'A'];
            scount = 1;

            for (count = 1; (count < 5) && (scount < in.length()); ++scount) {
                ch = in.charAt(scount);

                if (last != code[ch - 'A']) {
                    last = code[ch - 'A'];

                    if (last != 0) {
                        key[count++] = (char) ('0' + last);
                    }
                }
            }
        } catch (final ArrayIndexOutOfBoundsException e) {
            // If we hit an unknown character return Z000.
            return "Z000";
        }

        return new String(key);
    }
}

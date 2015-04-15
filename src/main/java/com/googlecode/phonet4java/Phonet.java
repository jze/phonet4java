/*
 * Java implementation of the "phonet" algorithm presented in the c't magazine 
 * volume 25/1999, p. 252
 * 
 * The original author of the C version is 
 *    Joerg MICHAEL, Adalbert-Stifter-Str. 11, 30655 Hannover, Germany
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
public class Phonet {
    private static final String[] phonet_rules =
        PhoneticRules.phonet_rules_german;
    private static final int HASH_COUNT = 512;
    private static final String umlaut_upper =
        "ÀÁÂÃÅÄÆÇÐÈÉÊËÌÍÎÏÑÒÓÔÕÖØßÞÙÚÛÜÝŸ";
    private static final String umlaut_lower =
        "àáâãåäæçðèéêëìíîïñòóôõöøßþùúûüýÿ";
    private static final String letters_a_to_z = "abcdefghijklmnopqrstuvwxyz";
    private static final String letters_A_to_Z = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Output debug information if set <code>true</code>.
     */
    boolean trace = false;
    private final char[] upperchar = new char[HASH_COUNT];
    private final int[] isletter = new int[HASH_COUNT];
    private final int[] phonet_hash = new int[512];
    private final int[] alpha_pos = new int[HASH_COUNT];
    int[][] phonet_hash_1 = new int[26][28];
    int[][] phonet_hash_2 = new int[26][28];

    /**
     *
     */
    public Phonet() {
        this.initialize_phonet();
    }

    private void trace_info(final String text, final int n, final String errText) {
        String s = (phonet_rules[n] == null) ? "(NULL)" : phonet_rules[n];
        String s2 =
            (phonet_rules[n + 1] == null) ? "(NULL)" : phonet_rules[n + 1];
        String s3 =
            (phonet_rules[n + 2] == null) ? "(NULL)" : phonet_rules[n + 2];

        System.out.printf("%s %d:  \"%s\"%s\"%s\" %s\n", text, ((n / 3) + 1),
            s, s2, s3, errText);
    }

    /**
     * Remove the first character of a String.
     * @param s
     * @return
     */
    private String removeFirst(final String s) {
        String result;

        if ((s == null) || (s.length() == 0)) {
            result = null;
        } else {
            result = s.substring(1);

            if (result.length() == 0) {
                result = null;
            }
        }

        return result;
    }

    /**
     * Return the pos's character, 0 is the String is to short or null
     * @param s
     * @param pos
     * @return
     */
    private char charAt(final String s, final int pos) {
        char result = 0;

        if ((s != null) && (s.length() > pos)) {
            result = s.charAt(pos);
        }

        return result;
    }

    private void initialize_phonet() {
        /****  generate arrays "alpha_pos", "upperchar" and "isletter"  ****/
        for (int i = 0; i < HASH_COUNT; i++) {
            alpha_pos[i] = 0;
            isletter[i] = 0;
            upperchar[i] = (char) i;
        }
        /* German and international umlauts  */
        {
            /* k == -1 in the original C code */
            String s = umlaut_lower;
            String s2 = umlaut_upper;

            for (int i = 0; i < s.length(); i++) {
                /* s2 */
                char n = s2.charAt(i);
                alpha_pos[n] = -1 + 2;
                isletter[n] = 2;
                upperchar[n] = s2.charAt(i);

                /* s */
                n = s.charAt(i);
                alpha_pos[n] = -1 + 2;
                isletter[n] = 1;
                upperchar[n] = s2.charAt(i);
            }
        }
        /*  "normal" letters ('a'-'z' and 'A'-'Z')  */
        /* k == 0 in the original C code */
        {
            String s = letters_a_to_z;
            String s2 = letters_A_to_Z;

            for (int i = 0; i < s.length(); i++) {
                /* s2 */
                char n = s2.charAt(i);
                alpha_pos[n] = i + 2;
                isletter[n] = 2;
                upperchar[n] = s2.charAt(i);

                /* s */
                n = s.charAt(i);
                alpha_pos[n] = i + 2;
                isletter[n] = 1;
                upperchar[n] = s2.charAt(i);
            }
        }

        for (int i = 0; i < HASH_COUNT; i++) {
            phonet_hash[i] = -1;
        }

        for (int i = 0; i < 26; i++) {
            int[] p_hash1 = phonet_hash_1[i];
            int[] p_hash2 = phonet_hash_2[i];

            for (int k = 0; k < 28; k++) {
                p_hash1[k] = -1;
                p_hash2[k] = -1;
            }
        }

        /* for each phonetc rule */
        for (int i = 0;
                (phonet_rules[i] == null) ||
                !phonet_rules[i].equals(PhoneticRules.PHONET_END); i++) {
            String s = phonet_rules[i];

            if ((s != null) && ((i % 3) == 0)) {
                /* calculate first hash value */
                int k = phonet_rules[i].charAt(0);

                if ((phonet_hash[k] < 0) &&
                        ((phonet_rules[i + 1] != null) ||
                        (phonet_rules[i + 2] != null))) {
                    phonet_hash[k] = i;
                }

                /* calculate second hash values  */
                if ((k != 0) && (alpha_pos[k] >= 2)) {
                    k = alpha_pos[k];

                    int[] p_hash1 = phonet_hash_1[k - 2];
                    int[] p_hash2 = phonet_hash_2[k - 2];
                    s = s.substring(1);

                    if (s.length() == 0) {
                        s = " ";
                    } else if (s.charAt(0) == '(') {
                        s = s.substring(1);
                    } else {
                        s = "" + s.charAt(0);
                    }

                    while ((s.length() > 0) && (s.charAt(0) != ')')) {
                        k = alpha_pos[s.charAt(0)];

                        if (k > 0) {
                            /****  add hash value for this letter  ****/
                            if (p_hash1[k] < 0) {
                                p_hash1[k] = i;
                                p_hash2[k] = i;
                            }

                            if (p_hash2[k] >= (i - 30)) {
                                p_hash2[k] = i;
                            } else {
                                k = -1;
                            }
                        }

                        if (k <= 0) {
                            /****  add hash value for all letters  ****/
                            if (p_hash1[0] < 0) {
                                p_hash1[0] = i;
                            }

                            p_hash2[0] = i;
                        }

                        s = s.substring(1);
                    }
                }
            }
        }
    }

    private String toUpperCase(final String s) {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            int pos;

            if ((pos = letters_a_to_z.indexOf(c)) > -1) {
                // a normal letter
                result.append(letters_A_to_Z.charAt(pos));
            } else if ((pos = umlaut_lower.indexOf(c)) > -1) {
                // an umlaut
                result.append(umlaut_upper.charAt(pos));
            } else {
                // another character
                result.append(c);
            }
        }

        return result.toString();
    }

    String phonet(final String input, final int ml) {
        int i;
        int j;
        int k;
        int n;
        int p;
        int z;
        int k0;
        int n0;
        int p0;
        int z0;
        char c;
        char c0 = 0;
        String s;
        String dest = input;

        if ((input == null) || (input.length() <= 0)) {
            return "";
        }

        int inputLength = input.length();

        /* convert input string to upper-case */
        String src = toUpperCase(input);

        /*  check "src"  */
        i = 0;
        j = 0;
        z = 0;

        while (i < src.length()) {
            c = src.charAt(i);

            if (trace) {
                System.out.printf("\ncheck position %d:  src = \"%s\",", j,
                    src.substring(i));
                System.out.printf("  dest = \"%s\"\n", dest.substring(0, j));
            }

            int start1;
            int start2;
            int end1;
            int end2;
            n = alpha_pos[c];

            if (n >= 2) {
                int[] p_hash1 = phonet_hash_1[n - 2];
                int[] p_hash2 = phonet_hash_2[n - 2];

                if ((i + 1) == src.length()) {
                    n = alpha_pos[0];
                } else {
                    n = alpha_pos[src.charAt(i + 1)];
                }

                start1 = p_hash1[n];
                start2 = p_hash1[0];
                end1 = p_hash2[n];
                end2 = p_hash2[0];

                /****  preserve rule priorities  ****/
                if ((start2 >= 0) && ((start1 < 0) || (start2 < start1))) {
                    n = start1;
                    start1 = start2;
                    start2 = n;
                    n = end1;
                    end1 = end2;
                    end2 = n;
                }

                if ((end1 >= start2) && (start2 >= 0)) {
                    if (end2 > end1) {
                        end1 = end2;
                    }

                    start2 = -1;
                    end2 = -1;
                }
            } else {
                n = phonet_hash[c];
                start1 = n;
                end1 = 10000;
                start2 = -1;
                end2 = -1;
            }

            n = start1;
            z0 = 0;

            if (n >= 0) {
                /* check rules for this char */
                while ((phonet_rules[n] == null) ||
                        (phonet_rules[n].charAt(0) == c)) {
                    if (n > end1) {
                        if (start2 > 0) {
                            n = start2;
                            start1 = start2;
                            start2 = -1;
                            end1 = end2;
                            end2 = -1;

                            continue;
                        }

                        break;
                    }

                    if ((phonet_rules[n] == null) ||
                            (phonet_rules[n + ml] == null)) {
                        /* no conversion rule available */
                        n += 3;

                        continue;
                    }

                    if (trace) {
                        trace_info("> rule no.", n, "is being checked");
                    }

                    /* check whole string */
                    k = 1; // number of matching letters
                    p = 5; // default priority
                    s = phonet_rules[n];
                    s = removeFirst(s);

                    while ((s != null) && (s.length() > 0) &&
                            (src.length() > (i + k)) &&
                            (src.charAt(i + k) == s.charAt(0)) &&
                            !Character.isDigit(s.charAt(0)) &&
                            ("(-<^$".indexOf(s) == -1)) {
                        k++;
                        s = removeFirst(s);
                    }

                    if ((s != null) && (s.charAt(0) == '(')) {
                        /* check an array of letters  */
                        if ((src.length() > (i + k)) &&
                                Character.isLetter(src.charAt(i + k)) &&
                                (s.substring(1).indexOf(src.charAt(i + k)) > -1)) {
                            k++;

                            while ((s != null) && (s.charAt(0) != ')')) {
                                s = removeFirst(s);
                            }

                            if (s.charAt(0) == ')') {
                                s = removeFirst(s);
                            }
                        }
                    }

                    if (s != null) {
                        p0 = s.charAt(0);
                    } else {
                        p0 = 0;
                    }

                    k0 = k;

                    while ((charAt(s, 0) == '-') && (k > 1)) {
                        k--;
                        s = removeFirst(s);
                    }

                    if (charAt(s, 0) == '<') {
                        s = s.substring(1);

                        if (s.length() == 0) {
                            s = null;
                        }
                    }

                    if ((charAt(s, 0) != 0) && Character.isDigit(s.charAt(0))) {
                        /*  read priority  */
                        p = s.charAt(0) - '0';
                        s = removeFirst(s);
                    }

                    if ((charAt(s, 0) == '^') && (charAt(s, 1) == '^')) {
                        s = removeFirst(s);
                    }

                    if ((charAt(s, 0) == 0) ||
                            ((charAt(s, 0) == '^') &&
                            ((i == 0) ||
                            !Character.isLetter(charAt(src, i - 1))) &&
                            ((charAt(s, 1) != '$') ||
                            (!Character.isLetter(charAt(src, i + k0)) &&
                            (charAt(src, i + k0) != '.')))) ||
                            ((charAt(s, 0) == '$') && (i > 0) &&
                            Character.isLetter(charAt(src, i - 1)) &&
                            (!Character.isLetter(charAt(src, i + k0)) &&
                            (charAt(src, i + k0) != '.')))) {
                        /* look for continuation, if:
                              k > 1 und NO '-' in first string */
                        n0 = -1;

                        int start3 = 0;
                        int start4 = 0;
                        int end3 = 0;
                        int end4 = 0;

                        if ((k > 1) && (charAt(src, i + k) != 0) &&
                                (p0 != '-')) {
                            c0 = charAt(src, (i + k) - 1);
                            n0 = alpha_pos[c0];

                            if ((n0 >= 2) && (charAt(src, i + k) != 0)) {
                                int[] p_hash1 = phonet_hash_1[n0 - 2];
                                int[] p_hash2 = phonet_hash_2[n0 - 2];
                                n0 = alpha_pos[charAt(src, i + k)];
                                start3 = p_hash1[n0];
                                start4 = p_hash1[0];
                                end3 = p_hash2[n0];
                                end4 = p_hash2[0];

                                /****  preserve rule priorities  ****/
                                if ((start4 >= 0) &&
                                        ((start3 < 0) || (start4 < start3))) {
                                    n0 = start3;
                                    start3 = start4;
                                    start4 = n0;
                                    n0 = end3;
                                    end3 = end4;
                                    end4 = n0;
                                }

                                if ((end3 >= start4) && (start4 >= 0)) {
                                    if (end4 > end3) {
                                        end3 = end4;
                                    }

                                    start4 = -1;
                                    end4 = -1;
                                }
                            } else {
                                n0 = phonet_hash[c0];
                                start3 = n0;
                                end3 = 10000;
                                start4 = -1;
                                end4 = -1;
                            }

                            n0 = start3;
                        }

                        if (n0 >= 0) { /* check continuation rules for "src[i+k] */

                            while ((phonet_rules[n0] == null) ||
                                    (phonet_rules[n0].charAt(0) == c0)) {
                                if (n0 > end3) {
                                    if (start4 > 0) {
                                        n0 = start4;
                                        start3 = start4;
                                        start4 = -1;
                                        end3 = end4;
                                        end4 = -1;

                                        continue;
                                    }

                                    p0 = -1;

                                    /****  important  ****/
                                    break;
                                }

                                if ((phonet_rules[n0] == null) ||
                                        (phonet_rules[n0 + ml] == null)) {
                                    /* no conversion rule available  */
                                    n0 += 3;

                                    continue;
                                }

                                if (trace) {
                                    trace_info("> > continuation rule no.", n0,
                                        "is being checked");
                                }

                                /****  check whole string  ****/
                                k0 = k;
                                p0 = 5;
                                s = phonet_rules[n0];
                                s = removeFirst(s);

                                while ((s != null) && s.length() > 0 &&
                                        (charAt(src, i + k0) == charAt(s, 0)) &&
                                        (!Character.isDigit(charAt(s, 0)) ||
                                        ("(-<^$".indexOf(s) == -1))) {
                                    k0++;
                                    s = removeFirst(s);
                                }

                                if (charAt(s, 0) == '(') {
                                    /****  check an array of letters  ****/
                                    if (Character.isLetter(charAt(src, i + k0)) &&
                                            (s.substring(1)
                                                  .indexOf(charAt(src, i + k0)) > -1)) {
                                        k0++;

                                        while ((s != null) &&
                                                (s.charAt(0) != ')')) {
                                            s = removeFirst(s);
                                        }

                                        if (s.charAt(0) == ')') {
                                            s = removeFirst(s);
                                        }
                                    }
                                }

                                while (charAt(s, 0) == '-') {
                                    /*  "k0" is NOT decremented
                                         because of  "if (k0 == k)"  ****/
                                    s = removeFirst(s);
                                }

                                if (charAt(s, 0) == '<') {
                                    s = removeFirst(s);
                                }

                                if (Character.isDigit(charAt(s, 0))) {
                                    p0 = s.charAt(0) - '0';
                                    s = removeFirst(s);
                                }

                                if ((s == null) || s.length() == 0
                                        /*s == '^' is not possible here */ ||
                                        ((charAt(s, 0) == '$') &&
                                        !Character.isLetter(charAt(src, i + k0)) &&
                                        (charAt(src, i + k0) != '.'))) {
                                    if (k0 == k) {
                                        /* this is only a partial string */
                                        if (trace) {
                                            trace_info("> > continuation rule no.",
                                                n0, "not used (too short)");
                                        }

                                        n0 += 3;

                                        continue;
                                    }

                                    if (p0 < p) {
                                        /* priority is too low  */
                                        if (trace) {
                                            trace_info("> > continuation rule no.",
                                                n0, "not used (priority)");
                                        }

                                        n0 += 3;

                                        continue;
                                    }

                                    /* continuation rule found  */
                                    break;
                                }

                                if (trace) {
                                    trace_info("> > continuation rule no.", n0,
                                        "not used");
                                }

                                n0 += 3;
                            }

                            /* end of "while" */
                            if ((p0 >= p) &&
                                    ((phonet_rules[n0] != null) &&
                                    (phonet_rules[n0].charAt(0) == c0))) {
                                n += 3;

                                if (trace) {
                                    trace_info("> rule no.", n, "");
                                    trace_info("> not used because of continuation",
                                        n0, "");
                                }

                                continue;
                            }
                        }

                        /* replace string */
                        if (trace) {
                            trace_info("Rule no.", n, "is applied");
                        }

                        if ((phonet_rules[n] != null) &&
                                (phonet_rules[n].substring(1).indexOf('<') > -1)) {
                            p0 = 1;
                        } else {
                            p0 = 0;
                        }

                        s = phonet_rules[n + ml];

                        if ((p0 == 1) && (z == 0)) {
                            /* rule with '<' is applied */
                            if ((j > 0) && s != null && s.length() > 0 &&
                                    ((charAt(dest, j - 1) == c) ||
                                    (charAt(dest, j - 1) == charAt(s, 0)))) {
                                j--;
                            }

                            z0 = 1;
                            z++;
                            k0 = 0;

                            while ((s != null) && (s.length() > 0) && (charAt(src, i + k0) != 0)) {
                                src = src.substring(0, i + k0) + charAt(s, 0) +
                                    src.substring(i + k0 + 1);
                                k0++;
                                s = removeFirst(s);
                            }

                            if (k0 < k) {
                                src = src.substring(0, i + k0) +
                                    src.substring(i + k);
                            }

                            c = src.charAt(i);
                        } else {
                            i = (i + k) - 1;
                            z = 0;

                            while ((s != null) && (s.length() > 1) ) {
                                if ((j == 0) ||
                                        (dest.charAt(j - 1) != s.charAt(0))) {
                                    dest = dest.substring(0, j) + s.charAt(0) +
                                            dest.substring(Math.min(dest.length(), j + 1));
                                    j++;
                                }

                                s = removeFirst(s);
                            }

                            /* new "current char" */
                            if (s.length() == 0) {
                                s = null;
                                c = 0;
                            } else {
                                c = s.charAt(0);
                            }

                            if ((phonet_rules[n] != null) &&
                                    (phonet_rules[n].substring(1).indexOf("^^") > -1)) {
                                if (c != 0) {
                                    dest = dest.substring(0, j) + c +
                                            dest.substring(Math.min(dest.length(), j + 1));
                                    j++;
                                }

                                src = src.substring(i + 1);
                                i = 0;
                                z0 = 1;
                            }
                        }

                        break;
                    }

                    n += 3;

                    if ((n > end1) && (start2 > 0)) {
                        n = start2;
                        start1 = start2;
                        end1 = end2;
                        start2 = -1;
                        end2 = -1;
                    }
                }
            }

            if (z0 == 0) {
                if ((c != 0) &&  ((j == 0) || (dest.charAt(j - 1) != c))) {
                    /* delete multiple letters only */
                    dest = dest.substring(0, j) + c + dest.substring(Math.min(j + 1, inputLength));
                    j++;
                }

                i++;
                z = 0;
            }
        }

        dest = dest.substring(0, j);

        return (dest);
    }
}

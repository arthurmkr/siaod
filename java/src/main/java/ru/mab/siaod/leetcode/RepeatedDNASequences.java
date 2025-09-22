package ru.mab.siaod.leetcode;

import java.util.*;

/**
 * 187. Repeated DNA Sequences
 */
public class RepeatedDNASequences {
    public static void main(String[] args) {
        RepeatedDNASequences alg = new RepeatedDNASequences();
        System.out.println(alg.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(alg.findRepeatedDnaSequences("AAAAAAAAAAAAA"));
        System.out.println(alg.findRepeatedDnaSequences("AAAAAAAAAA"));
    }

    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() <= 10) {
            return List.of();
        }

        Set<Integer> sequences = new HashSet<>();
        int[] mask = new int[25];
        mask['C' - 'A'] = 1;
        mask['G' - 'A'] = 2;
        mask['T' - 'A'] = 3;

        int prev = 0;
        char[] charArray = s.toCharArray();
        for (int i = 0; i < 10; i++) {
            prev <<= 2;
            prev |= mask[charArray[i] - 'A'];
        }

        sequences.add(prev);
        Map<Integer, String> indexes = new HashMap<>();

        int val = prev;
        for (int i = 10; i < charArray.length; i++) {
            val = (val << 2) & 0xFFFFF;

            val |= mask[charArray[i] - 'A'];

            if (sequences.contains(val)) {
                if(!indexes.containsKey(val)) {
                    indexes.put(val, new String(Arrays.copyOfRange(charArray, i - 9, i+1)));
                }
            } else {
                sequences.add(val);
            }
        }

        return new ArrayList<>(indexes.values());
    }
}

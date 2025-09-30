package ru.mab.siaod.leetcode;

/**
 * 1768. Merge Strings Alternately
 */
public class MergeStringsAlternately {
    public static void main(String[] args) {
        MergeStringsAlternately alg = new MergeStringsAlternately();
        System.out.println(alg.mergeAlternately("abc", "pqr").equals("apbqcr"));
        System.out.println(alg.mergeAlternately("ab", "pqrs").equals("apbqrs"));
        System.out.println(alg.mergeAlternately("abcd", "pq").equals("apbqcd"));
    }

    public String mergeAlternately(String word1, String word2) {
        StringBuilder s = new StringBuilder();

        int i;
        for (i = 0; i < word1.length() && i < word2.length(); i++) {
            s.append(word1.charAt(i))
                    .append(word2.charAt(i));
        }

        if(i < word1.length()) {
            s.append(word1.substring(i));
        } else {
            s.append(word2.substring(i));
        }

        return s.toString();
    }
}

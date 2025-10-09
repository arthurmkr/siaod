package ru.mab.siaod.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1657. Determine if Two Strings Are Close
 */
public class DetermineIfTwoStringsAreClose {
    public static void main(String[] args) {
        System.out.println(closeStrings("abc", "bca") == true);
        System.out.println(closeStrings("a", "aa") == false);
        System.out.println(closeStrings("cabbba", "abbccc") == true);
        System.out.println(closeStrings("cabbba", "aabbss") == false);
        System.out.println(closeStrings("xuu", "ssr") == false);
        System.out.println(closeStrings("abbbzcf", "babzzcz") == false);
    }

//    public static boolean closeStrings(String word1, String word2) {
//        if (word1.length() != word2.length()) {
//            return false;
//        }
//
//        int[] counts1 = count(word1);
//        int[] counts2 = count(word2);
//
//        for (int i = 0; i < counts1.length; i++) {
//            if ((counts1[i] != 0 && counts2[i] == 0) ||
//                    (counts1[i] == 0 && counts2[i] != 0)) {
//                return false;
//            }
//        }
//
//        Arrays.sort(counts1);
//        Arrays.sort(counts2);
//        return Arrays.equals(counts1, counts2);
//    }
//
//    private static int[] count(String word1) {
//        int[] counts = new int[127];
//        char[] charArray = word1.toCharArray();
//        for (char c : charArray) {
//            counts[c]++;
//        }
//
//        return counts;
//    }

    public static boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int[] counts = new int[127];
        int[] counts2 = new int[127];
        char[] charArray = word1.toCharArray();
        for (char c : charArray) {
            counts[c]++;
        }

        char[] charArray2 = word2.toCharArray();
        for (char c : charArray2) {
            if (counts[c] == 0) {
                return false;
            }
            counts2[c]++;
        }

        Arrays.sort(counts);
        Arrays.sort(counts2);

        for(int j = counts.length - 1; j >= 0; j--) {
            if(counts[j] != counts2[j]) {
                return false;
            }
        }
        return true;
    }
}

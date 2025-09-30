package ru.mab.siaod.leetcode;

/**
 * 392. Is Subsequence
 */
public class IsSubsequence {
    public static void main(String[] args) {
        System.out.println(isSubsequence("abc", "ahbgdc") == true);
        System.out.println(isSubsequence("axc", "ahbgdc") == false);
        System.out.println(isSubsequence("ahbgdc", "abc") == false);
    }

    public static boolean isSubsequence(String s, String t) {
        int lenS = s.length();
        int lenT = t.length();
        if (lenS > lenT) {
            return false;
        }

        int i = 0;
        int j = 0;

        char[] arrS = s.toCharArray();
        char[] arrT = t.toCharArray();
        while (i < lenS && j < lenT) {
            if (arrS[i] == arrT[j]) {
                i++;
            }
            j++;
        }

        return i == lenS;
    }
}

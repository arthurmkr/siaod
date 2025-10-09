package ru.mab.siaod.leetcode;

/**
 * 1456. Maximum Number of Vowels in a Substring of Given Length
 */
public class MaximumNumberOfVowelsInSubstringOfGivenLength {
    public static void main(String[] args) {
        System.out.println(maxVowels("abciiidef", 3) == 3);
        System.out.println(maxVowels("aeiou", 2) == 2);
        System.out.println(maxVowels("leetcode", 3) == 2);
    }

    public static int maxVowels(String s, int k) {
        boolean[] vovels = new boolean[127];
        for (char c : new char[]{'a', 'e', 'i', 'o', 'u'}) {
            vovels[c] = true;
        }

        char[] arr = s.toCharArray();
        int max = 0;

        int i;
        for (i = 0; i < k && i < arr.length; i++) {
            if (vovels[arr[i]]) {
                max++;
            }
        }

        int cur = max;
        while (i < arr.length) {
            if (!vovels[arr[i - k]] && vovels[arr[i]]) {
                cur++;
                max = Math.max(cur, max);
            } else if (vovels[arr[i - k]] && !vovels[arr[i]]) {
                cur--;
                max = Math.max(cur, max);
            }
            i++;
        }

        return max;
    }
}

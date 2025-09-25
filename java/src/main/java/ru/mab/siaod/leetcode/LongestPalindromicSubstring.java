package ru.mab.siaod.leetcode;

/**
 * 5. Longest Palindromic Substring
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        LongestPalindromicSubstring alg = new LongestPalindromicSubstring();
        System.out.println(alg.longestPalindrome("a"));
        System.out.println(alg.longestPalindrome("abb"));
        System.out.println(alg.longestPalindrome("babad"));
        System.out.println(alg.longestPalindrome("babad"));
        System.out.println(alg.longestPalindrome("babab"));
        System.out.println(alg.longestPalindrome("d1bab"));
    }

    public String longestPalindrome(String s) {
        char[] arr = s.toCharArray();

        int mid = arr.length / 2;
        int[] max = new int[]{1, 0, 1};

        int i = 0;
        while (i <= mid && max[0] / 2 <= mid - i) {
            extracted(mid - i, mid - i, arr, max);

            if (mid - i != mid + i) {
                extracted(mid + i, mid + i, arr, max);
            }

            extracted(mid - i - 1, mid - i, arr, max);

            extracted(mid + i - 1, mid + i, arr, max);

            i++;
        }

        return max[0] > 0 ? s.substring(max[1], max[2]) : "";
    }

    private void extracted(int l, int r, char[] arr, int[] max) {
        int j = r;
        int i = l;
        for (; i >= 0 && i < arr.length && j < arr.length; i--, j++) {
            if (arr[i] != arr[j]) {
                break;
            }
        }

        i++;

        if (j - i > max[0]) {
            max[0] = j - i;
            max[1] = i;
            max[2] = j;
        }
    }
}

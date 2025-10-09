package ru.mab.siaod.leetcode;

/**
 * 2390. Removing Stars From a String
 */
public class RemovingStarsFromString {
    public static void main(String[] args) {
        System.out.println(removeStars("leet**cod*e").equals("lecoe"));
        System.out.println(removeStars("erase*****").equals(""));
        System.out.println(removeStars("erase*******").equals(""));
        System.out.println(removeStars("**erase*******").equals(""));
        System.out.println(removeStars("**erase*******").equals(""));
        System.out.println(removeStars("e**rase**").equals("ra"));
    }

    public static String removeStars(String s) {
        int i = 0;
        int j = -1;
        char[] arr = s.toCharArray();

        while (i < arr.length) {
            if (arr[i] != '*') {
                j++;
                arr[j] = arr[i];
            } else {
                j = Math.max(j - 1, -1);
            }
            i++;
        }

        return j < 0 ? "" : new String(arr, 0, j + 1);
    }
}

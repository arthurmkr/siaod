package ru.mab.siaod.leetcode;

public class LongestCommonPrefix {
    public static void main(String[] args) {
//        System.out.println("[%s]".formatted(longestCommonPrefix(new String[]{"flower", "flow", "flight"})));
//        System.out.println("[%s]".formatted(longestCommonPrefix(new String[]{"dog","racecar","car"})));
//        System.out.println("[%s]".formatted(longestCommonPrefix(new String[]{""})));
//        System.out.println("[%s]".formatted(longestCommonPrefix(new String[]{"a"})));
        System.out.println("[%s]".formatted(longestCommonPrefix(new String[]{"flower", "flower", "flower", "flower"})));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        int j = 0;
        main:
        for (; j < strs[0].length(); j++) {
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].length() == j || strs[i].charAt(j) != strs[0].charAt(j)) {
                    break main;
                }
            }
        }

        return strs[0].substring(0, j);
    }
}

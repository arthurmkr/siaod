package ru.mab.siaod.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * 76. Minimum Window Substring
 */
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        MinimumWindowSubstring alg = new MinimumWindowSubstring();
        System.out.println(alg.minWindow("ADOBEBCODEBANC", "ABC"));
        System.out.println(alg.minWindow("a", "a"));
        System.out.println(alg.minWindow("a", "b"));
        System.out.println(alg.minWindow("a", "aa"));
        System.out.println(alg.minWindow("BBA", "AB"));
        System.out.println(alg.minWindow("cabwefgezcwaefgcf", "cae"));
    }

    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        int[] counters = new int[58];
        boolean[] letter = new boolean[58];
        int totalCount = 0;
        for (char c : t.toCharArray()) {
            counters[c - 'A']++;
            letter[c - 'A'] = true;
            totalCount++;
        }

        Queue<Integer> indexes = new ArrayDeque<>();
        int start = -1, end = s.length();
        for (int i = 0; i < s.length(); i++) {
            int ind = s.charAt(i) - 'A';
            if (!letter[ind]) {
                continue;
            }

            if (counters[ind] > 0) {
                totalCount--;
            }
            indexes.add(i);
            counters[ind]--;

            if (totalCount == 0) {
                int headIndex = indexes.poll();
                while (counters[s.charAt(headIndex) - 'A'] < 0) {
                    ind = s.charAt(headIndex) - 'A';
                    counters[ind]++;
                    headIndex = indexes.poll();
                }

                if (i - headIndex < end - start) {
                    end = i;
                    start = headIndex;
                }

                ind = s.charAt(headIndex) - 'A';
                counters[ind]++;
                totalCount++;
            }
        }
        return start < 0 ? "" : s.substring(start, end + 1);
    }

    public String minWindow2(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        int[] counters = new int[58];
        boolean[] letter = new boolean[58];
        int totalCount = 0;
        for (char c : t.toCharArray()) {
            counters[c - 'A']++;
            letter[c - 'A'] = true;
            totalCount++;
        }

        int start = -1;
        int end = -1;
        int minLength = Integer.MAX_VALUE;
        Deque<CharIndex> queue = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int ind = c - 'A';
            if (!letter[ind]) {
                continue;
            }

            counters[ind]--;
            if (totalCount > 0 && counters[ind] >= 0) {
                totalCount--;
            }

            queue.add(new CharIndex(i, c));

            if (totalCount == 0) {
                if (minLength == Integer.MAX_VALUE) {
                    int curStart = queue.peek().index;
                    int newLength = i - curStart;
                    if (newLength < minLength) {
                        minLength = newLength;
                        start = curStart;
                        end = i;
                    }
                }

                ind = queue.peek().c - 'A';
                while (counters[ind] < 0) {
                    counters[ind]++;
                    queue.poll();

                    CharIndex newHead = queue.peek();
                    ind = newHead.c - 'A';
                    int newLength = i - newHead.index;

                    if (newLength < minLength) {
                        minLength = newLength;
                        start = newHead.index;
                        end = i;
                    }
                }
            }
        }

        return start < 0 ? "" : s.substring(start, end + 1);
    }

    class CharIndex {
        final int index;
        final char c;

        public CharIndex(int index, char c) {
            this.index = index;
            this.c = c;
        }

        @Override
        public String toString() {
            return c + ": " + index;
        }
    }
}

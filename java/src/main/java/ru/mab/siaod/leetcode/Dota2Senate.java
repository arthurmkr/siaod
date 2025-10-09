package ru.mab.siaod.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 649. Dota2 Senate
 */
public class Dota2Senate {
    public static void main(String[] args) {
        System.out.println(predictPartyVictory("D").equals("Dire"));
        System.out.println(predictPartyVictory("RD").equals("Radiant"));
        System.out.println(predictPartyVictory("RDD").equals("Dire"));
        System.out.println(predictPartyVictory("DDRRR").equals("Dire"));
    }

    public static String predictPartyVictory(String senate) {
        char[] arr = senate.toCharArray();
        int n = arr.length;
        Queue<Character> queue = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        int cntR = 0;
        int cntD = 0;
        while (!queue.isEmpty()) {
            char c = queue.poll();
            if (c == 'R') {
                if (cntD > 0) {
                    cntD--;
                    n--;
                } else {
                    cntR++;
                    queue.add(c);
                }
            } else {
                if (cntR > 0) {
                    cntR--;
                    n--;
                } else {
                    cntD++;
                    queue.add(c);
                }
            }

            if (cntR + cntD == n) {
                return cntR == 0 ? "Dire" : "Radiant";
            }
        }

        return "";
    }
}

package ru.mab.siaod.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 841. Keys and Rooms
 */
public class KeysAndRooms {
    public static void main(String[] args) {
        System.out.println(canVisitAllRooms(convert(new int[][]{{1}, {2}, {3}, {}})) == true);
        System.out.println(canVisitAllRooms(convert(new int[][]{{1, 3}, {3, 0, 1}, {2}, {}})) == false);
    }

    public static List<List<Integer>> convert(int[][] rooms) {
        List<List<Integer>> res = new ArrayList<>();
        for (int[] keys : rooms) {
            List<Integer> resKeys = new ArrayList<>();
            for (int key : keys) {
                resKeys.add(key);
            }

            res.add(resKeys);
        }

        return res;
    }

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Queue<Integer> roomIndexes = new ArrayDeque<>();
        roomIndexes.add(0);

        boolean[] keysFound = new boolean[rooms.size()];
        keysFound[0] = true;
        int cnt = 1;

        while (!roomIndexes.isEmpty()) {
            List<Integer> keys = rooms.get(roomIndexes.poll());
            for (Integer key : keys) {
                if (!keysFound[key]) {
                    cnt++;
                    keysFound[key] = true;
                    roomIndexes.add(key);
                }
            }
        }


        return cnt == rooms.size();
    }
}

package ru.mab.siaod.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 447. Number of boomerangs
 */
public class NumberOfBoomerangs {
    public static void main(String[] args) {
        System.out.println(numberOfBoomerangs(new int[][]{{0, 0}, {1, 0}, {2, 0}}));
        System.out.println(numberOfBoomerangs(new int[][]{{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}}));
    }

    public static int numberOfBoomerangs(int[][] points) {
        Map<Long, Integer> distances = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                long diffX = points[i][0] - points[j][0];
                long diffY = points[i][1] - points[j][1];
                long distance = diffX * diffX + diffY * diffY;

                distances.put(distance, distances.getOrDefault(distance, 0) + 1);
            }
        }

        int count = 0;
        for (Map.Entry<Long, Integer> dist : distances.entrySet()) {
            int value = dist.getValue();
            if (value > 1) {
                count += value * (value - 1);
            }
        }

        return count;
    }
}

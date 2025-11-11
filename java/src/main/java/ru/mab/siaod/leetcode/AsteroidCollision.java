package ru.mab.siaod.leetcode;

import java.util.Arrays;

/**
 * 735. Asteroid Collision
 */
public class AsteroidCollision {
    public static void main(String[] args) {
        System.out.println(Arrays.equals(asteroidCollision(new int[]{5, 1, -5}), new int[0]));
        System.out.println(Arrays.equals(asteroidCollision(new int[]{5, 10, -5}), new int[]{5, 10}));
        System.out.println(Arrays.equals(asteroidCollision(new int[]{8, -8}), new int[0]));
        System.out.println(Arrays.equals(asteroidCollision(new int[]{10, 2, -5}), new int[]{10}));
        System.out.println(Arrays.equals(asteroidCollision(new int[]{-2, -1, 1, 2}), new int[]{-2, -1, 1, 2}));
        System.out.println(Arrays.equals(asteroidCollision(new int[]{1, -2, -2, -2}), new int[]{-2, -2, -2}));
    }

    public static int[] asteroidCollision(int[] asteroids) {
        int indexOfLast = 0;
        int i = 1;
        while (i < asteroids.length) {
            if (indexOfLast >= 0 && asteroids[indexOfLast] > 0 && asteroids[i] < 0) {
                if (asteroids[indexOfLast] == -asteroids[i]) {
                    indexOfLast--;
                    i++;
                } else if (asteroids[indexOfLast] < -asteroids[i]) {
                    indexOfLast--;
                } else {
                    i++;
                }
                continue;
            }

            indexOfLast++;
            asteroids[indexOfLast] = asteroids[i];

            i++;
        }

        int[] res = new int[indexOfLast + 1];
        System.arraycopy(asteroids, 0, res, 0, indexOfLast + 1);
        return res;
    }
}

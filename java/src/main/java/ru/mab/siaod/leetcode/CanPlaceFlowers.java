package ru.mab.siaod.leetcode;

/**
 * 605. Can Place Flowers
 */
public class CanPlaceFlowers {
    public static void main(String[] args) {
        CanPlaceFlowers alg = new CanPlaceFlowers();
        System.out.println(alg.canPlaceFlowers(new int[]{1, 0, 0, 0, 0, 1}, 2) == false);
        System.out.println(alg.canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1) == true);
        System.out.println(alg.canPlaceFlowers(new int[]{1, 0, 0, 1}, 1) == false);
        System.out.println(alg.canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 2) == false);
        System.out.println(alg.canPlaceFlowers(new int[]{0, 1, 0, 0, 1}, 1) == false);
        System.out.println(alg.canPlaceFlowers(new int[]{0, 1, 0, 0}, 1) == true);
        System.out.println(alg.canPlaceFlowers(new int[]{0, 0, 0}, 2) == true);
        System.out.println(alg.canPlaceFlowers(new int[]{0, 0, 1, 0, 0}, 1) == true);
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        boolean zero = false;
        for (int i = 0; i < flowerbed.length && n > 0; i++) {
            if (flowerbed[i] == 1) {
                i++;
                zero = false;
            } else {
                if (zero) {
                    n--;
                }

                zero = !zero;
            }
        }

        if (zero) {
            n--;
        }

        return n <= 0;
    }
}

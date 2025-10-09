package ru.mab.siaod.leetcode;

/**
 * 11. Container With Most Water
 */
public class ContainerWithMostWater {
    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}) == 49);
        System.out.println(maxArea(new int[]{1, 1}) == 1);
    }

    public static int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int max = 0;
        int leftMax = 0;
        int rightMax = 0;

        while (i <= j) {
            if(height[i] >= height[j]) {
                if (height[j] > rightMax) {
                    int curV = height[j] * (j - i);
                    max = Math.max(curV, max);
                    rightMax = height[j];
                }
                j--;
            } else {
                if (height[i] > leftMax) {
                    int curV = height[i] * (j - i);
                    max = Math.max(curV, max);
                    leftMax = height[i];
                }
                i++;
            }
        }

        return max;
    }
}

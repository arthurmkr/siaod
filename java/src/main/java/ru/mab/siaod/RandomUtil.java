package ru.mab.siaod;

import java.util.Random;

public class RandomUtil {
    private static final Random RANDOM = new Random();

    public static int nextInt(int bound) {
        return RANDOM.nextInt(bound);
    }
}

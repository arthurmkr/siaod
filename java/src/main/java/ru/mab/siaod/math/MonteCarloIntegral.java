package ru.mab.siaod.math;

import java.util.function.Function;

/**
 * Метод расчета интеграла методом Монте-Карло
 */
public class MonteCarloIntegral {

    public static final int COUNT = 10000;

    public static void main(String[] args) {
        System.out.println("f = x; value = " + calcIntegral(x -> x, COUNT));
        System.out.println("f = x*x; value = " + calcIntegral(x -> x * x, COUNT));
        System.out.println("f = x*x*x; value = " + calcIntegral(x -> x * x * x, COUNT));
    }

    static double calcIntegral(Function<Double, Double> func, int count) {
        double sum = 0;
        for (int i = 0; i < count; i++) {
            sum += func.apply(Math.random());
        }

        return sum / count;
    }
}

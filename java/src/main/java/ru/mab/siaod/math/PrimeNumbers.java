package ru.mab.siaod.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrimeNumbers {

    public static final int REPEAT_COUNT = 100;

    public static void main(String[] args) {
        List<PrimesFinder> finders = List.of(
                new BruteForceFinder(),
                new Eratosthenes());

        Map<PrimesFinder, Long> times = new HashMap<>();
        for (int i = 0; i < REPEAT_COUNT; i++) {
            for (PrimesFinder finder : finders) {
                long time = System.nanoTime();
                finder.findPrimes(100000);
                long taken = System.nanoTime() - time;

                times.put(finder, times.getOrDefault(finder, 0L) + taken);
            }
        }

        for (Map.Entry<PrimesFinder, Long> result : times.entrySet()) {
            System.out.println(result.getKey().getClass().getSimpleName() + ": " + ((double) result.getValue() / REPEAT_COUNT / 1000));
        }
    }

    interface PrimesFinder {
        List<Integer> findPrimes(int n);
    }

    static class Eratosthenes implements PrimesFinder {
        @Override
        public List<Integer> findPrimes(int n) {
            boolean[] all = new boolean[n + 1];

            List<Integer> primes = new ArrayList<>();
            primes.add(1);
            for (int i = 2; i < all.length; i++) {
                if (all[i]) {
                    continue;
                }

                int start = i < 46000 ? i * i : i;
                for (int j = start; j < all.length; j += i) {
                    if (all[j]) {
                        continue;
                    }
                    all[j] = j % i == 0;
                }

                primes.add(i);
            }
            return primes;
        }
    }

    static class BruteForceFinder implements PrimesFinder {

        @Override
        public List<Integer> findPrimes(int n) {
            List<Integer> primes = new ArrayList<>();
            primes.add(1);

            for (int i = 2; i <= n; i++) {
                boolean isPrime = true;
                for (int j = 1; j < primes.size(); j++) {
                    if (i % primes.get(j) == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) {
                    primes.add(i);
                }
            }

            return primes;
        }
    }
}

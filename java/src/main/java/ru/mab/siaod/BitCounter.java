package ru.mab.siaod;

import java.util.BitSet;

public class BitCounter {
    BitSet set = new BitSet();
    int length;

    int incCount;
    int decCount;

    public BitCounter(int bits) {
        this.length = bits;
    }

    public static void main(String[] args) {
        BitCounter counter = new BitCounter(64);
        int N = 40;
        for (int i = 0; i < N; i++) {
            System.out.println(counter.inc());
        }

        System.out.println("Inc: " + counter.getIncCount());

        for (int i = 0; i < N; i++) {
            System.out.println(counter.dec());
        }

        System.out.println("Dec: " + counter.getDecCount());
    }

    private long dec() {
        boolean prev = false;
        int i = 0;
        while (!prev && i < length) {
            boolean cur = set.get(i);
            set.set(i, !cur);
            decCount++;

            prev = cur;
            i++;
        }

        return toLong();
    }

    private long toLong() {
        return set.isEmpty() ? 0 : set.toLongArray()[0];
    }

    public long inc() {
        int i = 0;
        while (i < length && set.get(i)) {
            set.set(i, false);
            incCount++;
            i++;
        }

        if (i < length) {
            set.set(i);
            incCount++;
        }

        return toLong();
    }

    public int getIncCount() {
        return incCount;
    }

    public int getDecCount() {
        return decCount;
    }
}

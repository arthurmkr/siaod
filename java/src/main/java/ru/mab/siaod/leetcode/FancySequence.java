package ru.mab.siaod.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FancySequence {
    public static void main(String[] args) {
        Fancy fancy = new Fancy();
        fancy.append(285845760);   // fancy sequence: [2]
        fancy.addAll(9);   // fancy sequence: [2+3] -> [5]
        fancy.append(9);   // fancy sequence: [5, 7]
        fancy.multAll(4);  // fancy sequence: [5*2, 7*2] -> [10, 14]
        fancy.addAll(8);   // fancy sequence: [2+3] -> [5]
        fancy.addAll(11);   // fancy sequence: [2+3] -> [5]
        fancy.multAll(15);  // fancy sequence: [5*2, 7*2] -> [10, 14]
        fancy.addAll(1);   // fancy sequence: [2+3] -> [5]
        fancy.append(4);   // fancy sequence: [2+3] -> [5]
        fancy.append(10);   // fancy sequence: [2+3] -> [5]
        System.out.println(fancy.getIndex(0));   // fancy sequence: [2+3] -> [5]
    }
}
/*
["Fancy","append","append","getIndex","append","getIndex","addAll","append","getIndex","getIndex","append","append","getIndex","append","getIndex","append","getIndex","append","getIndex","multAll","addAll","getIndex","append","addAll","getIndex","multAll","getIndex","multAll","addAll","addAll","append","multAll","append","append","append","multAll","getIndex","multAll","multAll","multAll","getIndex","addAll","append","multAll","addAll","addAll","multAll","addAll","addAll","append","append","getIndex"]
[[],[12],[8],[1],[12],[0],[12],[8],[2],[2],[4],[13],[4],[12],[6],[11],[1],[10],[2],[3],[1],[6],[14],[5],[6],[12],[3],[12],[15],[6],[7],[8],[13],[15],[15],[10],[9],[12],[12],[9],[9],[9],[9],[4],[8],[11],[15],[9],[1],[4],[10],[9]]

"getIndex","addAll","append","multAll","addAll","addAll","multAll","addAll","addAll","append","append","getIndex"
[9],[9],[9],[4],[8],[11],[15],[9],[1],[4],[10],[9]
 */

class Fancy {
    private static final int MODULE = 1_000_000_000 + 7;

    int[] array = new int[100001];
    CachedValue[] cache = new CachedValue[100001];
    int size = 0;
    List<Operation> ops = new ArrayList<>();

    public Fancy() {
    }

    public void append(int val) {
        array[size] = val;
        size++;
    }

    public void addAll(int inc) {
        if (size > 0) {
            ops.add(new Operation(true, size - 1, inc));
        }
    }

    public void multAll(int m) {
        if (size > 0) {
            ops.add(new Operation(false, size - 1, m));
        }
    }

    public int getIndex(int idx) {
        if (idx < 0 || idx >= size) {
            return -1;
        }

        if (ops.isEmpty() || ops.get(ops.size() - 1).index < idx) {
            return array[idx];
        }

        int i = find(idx, ops, 0, ops.size());
        long value = array[idx];

        CachedValue cachedValue = cache[idx];
        if (cachedValue != null) {
            i = cachedValue.opIndex;
            value = cachedValue.value;
        }

        while (i < ops.size()) {
            Operation op = ops.get(i);
            value = (op.add ? value + op.value : value * op.value) % MODULE;

            i++;
        }

        cache[idx] = new CachedValue((int) value, ops.size());

        return cache[idx].value;
    }

    int find(int v, List<Operation> arr, int start, int end) {
        for(int i = 0; i < arr.size(); i++) {
            if(arr.get(i).index >= v) {
                return i;
            }
        }

        return arr.size();
//        if (start >= end) {
//            return arr.get(start).index > v ? start : start + 1;
//        }
//        int mid = (start + end) / 2;
//        if (arr.get(start).index >= v) {
//            return find(v, arr, start, mid);
//        } else {
//            return find(v, arr, mid + 1, end);
//        }
    }

    static class CachedValue {
        int value;
        int opIndex;

        public CachedValue(int value, int opIndex) {
            this.value = value;
            this.opIndex = opIndex;
        }
    }

    static class Operation {
        boolean add;
        int index;
        int value;

        public Operation(boolean add, int index, int value) {
            this.add = add;
            this.index = index;
            this.value = value;
        }
    }
}

class Fancy2 {
    private static final int MODULE = 1_000_000_000 + 7;

    int[] array = new int[10002];
    int[] cache = new int[10002];
    //    List<> = new int[10002];
    int size = 0;

    public Fancy2() {
        Arrays.fill(cache, -1);
//        Arrays.setAll();
    }

    public void append(int val) {
        array[size] = val;
        size++;
    }

    public void addAll(int inc) {
        for (int i = 0; i < size; i++) {
            array[i] = (int) (((long) inc + array[i]) % MODULE);
        }
    }

    public void multAll(int m) {
        for (int i = 0; i < size; i++) {
            array[i] = (int) (((long) m * array[i]) % MODULE);
        }
    }

    public int getIndex(int idx) {
        if (idx < 0 || idx >= size) {
            return -1;
        }


        return array[idx];
    }
}
package ru.mab.siaod.interlacement;

import org.junit.jupiter.api.Test;
import ru.mab.siaod.ArrayUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

class InterlacementTest {
    @Test
    void interlace() {
        List<Integer> arr1 = List.of(1, 2);
        List<Integer> arr2 = List.of(3, 4);

        InterlacementArrayImpl interlacement = new InterlacementArrayImpl();
//        Set<Interlacement.Node> actual = interlacement.interlace(arr1, arr2);
//
//        Set<List<Integer>> expected = Set.of(
//                List.of(1, 2, 3, 4),
//                List.of(1, 3, 2, 4),
//                List.of(1, 3, 4, 2),
//                List.of(3, 1, 2, 4),
//                List.of(3, 1, 4, 2),
//                List.of(3, 4, 1, 2)
//        );
//        assertEquals(expected, actual);
        System.out.println(1);
    }

    @Test
    void bigO() {
        InterlacementLinkedImpl interlacement = new InterlacementLinkedImpl();
//        InterlacementArrayImpl interlacement = new InterlacementArrayImpl();
        int size = 15;
        long prev = 1;
        for (int i = 0; i < 25; i++) {
            List<Integer> arr1 = generateArray(size, Integer.MIN_VALUE, -1);
            List<Integer> arr2 = generateArray(size, 1, Integer.MAX_VALUE);

            long start = System.currentTimeMillis();

//            Set<Interlacement.Node> result = interlacement.interlace(arr1, arr2);
            Set result = interlacement.interlace(arr1, arr2);

            long l = System.currentTimeMillis() - start;
            System.out.println(new Date() + ", size: " + size + ", time: " + (l / 1000) + ", x: " + (l / prev));

            System.out.println("size: " + size + ", count: " + result.size());

            prev = Math.max(l, 1);
            size += 1;
        }
    }

    private List<Integer> generateArray(int size, int minValue, int i) {
        int[] array = ArrayUtil.generateArray(size, minValue, i);
        List<Integer> res = new ArrayList<>();
        for (int v : array) {
            res.add(v);
        }
        return res;
    }
}
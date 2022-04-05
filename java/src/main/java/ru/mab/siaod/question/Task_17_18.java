package ru.mab.siaod.question;

import java.util.*;

/**
 * Поиск минимального подмассива содержащего все элементы
 */
public class Task_17_18 {
    public static void main(String[] args) {
        int[] q = {1, 5, 9};
        int[] arr = {7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};

        int[] res = func(arr, q);
        System.out.println("res: " + Arrays.toString(res) + ", is correct: " + (Arrays.equals(res, new int[]{7, 10})));
    }

    private static int[] func(int[] arr, int[] q) {
        Map<Integer, Queue<Integer>> positions = new HashMap<>();
        for (int v : q) {
            positions.put(v, new LinkedList<>());
        }

        for (int i = 0; i < arr.length; i++) {
            Queue<Integer> queue = positions.get(arr[i]);
            if (queue != null) {
                queue.add(i);
            }
        }

        class HeapNode {
            int v;
            int pos;

            public HeapNode(int v, int pos) {
                this.v = v;
                this.pos = pos;
            }
        }

        int max = Integer.MIN_VALUE;
        PriorityQueue<HeapNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.pos));
        for(Map.Entry<Integer, Queue<Integer>> entry : positions.entrySet()) {
            Integer peek = entry.getValue().peek();
            minHeap.add(new HeapNode(entry.getKey(), peek));

            max = Math.max(peek, max);
        }

        int minDistance = Integer.MAX_VALUE;
        boolean isAnyEmpty = false;
        while (!isAnyEmpty) {
            HeapNode node = minHeap.poll();

            int curDistance = max - node.pos;
            if(minDistance > curDistance) {
                minDistance = curDistance;

                Queue<Integer> queue = positions.get(node.v);
                Integer nextPos = queue.poll();
                minHeap.add(new HeapNode(node.v, nextPos));

                
            }
        }

        int resultMin = -1;
        int resultMax = -1;

        int countEmpty = 0;
        while (countEmpty < q.length) {
            int minIndex = Integer.MAX_VALUE;
            int maxIndex = Integer.MIN_VALUE;
            int positionForShift = -1;

            for (Map.Entry<Integer, Queue<Integer>> entry : positions.entrySet()) {
                Queue<Integer> value = entry.getValue();
                if (!value.isEmpty()) {
                    if (value.peek() < minIndex) {
                        minIndex = value.peek();
                        positionForShift = entry.getKey();
                    }

                    maxIndex = Math.max(value.peek(), maxIndex);
                }
            }

            int curDistance = maxIndex - minIndex;
            if (minDistance > curDistance) {
                minDistance = curDistance;
                resultMin = minIndex;
                resultMax = maxIndex;
            }

            Queue<Integer> queue = positions.get(positionForShift);
            if (queue.size() == 1) {
                countEmpty++;
            } else {
                queue.poll();
            }
        }

        return new int[]{resultMin, resultMax};
    }
}

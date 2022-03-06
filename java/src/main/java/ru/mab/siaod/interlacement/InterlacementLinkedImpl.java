package ru.mab.siaod.interlacement;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InterlacementLinkedImpl {

    public Set<Node> interlace(List<Integer> arr1, List<Integer> arr2) {
        Set<Node>[][] cache = new Set[arr1.size()][arr2.size()];
        Node list1 = convertToList(arr1);
        Node list2 = convertToList(arr2);
        return subInterlace(list1, 0, list2, 0, cache);
    }

    private Node convertToList(List<Integer> arr) {
        Node head = null;
        for (int i = arr.size() - 1; i >= 0; i--) {
            Node node = new Node(arr.get(i));
            node.next = head;
            head = node;
        }
        return head;
    }

    private Set<Node> subInterlace(Node arr1, int arr1Index, Node arr2, int arr2Index, Set<Node>[][] cache) {

        if (arr1 == null) {
            return Set.of(arr2);
        }

        if (arr2 == null) {
            return Set.of(arr1);
        }

        if (arr1.next != null && arr2.next != null) {
            if (cache[arr1Index][arr2Index] != null) {
                return cache[arr1Index][arr2Index];
            }
        }

        Set<Node> result = new HashSet<>();

        Set<Node> first = subInterlace(arr1.next, arr1Index + 1, arr2, arr2Index, cache);
        generate(arr1.v, first, result);

        Set<Node> second = subInterlace(arr1, arr1Index, arr2.next, arr2Index + 1, cache);
        generate(arr2.v, second, result);

        cache[arr1Index][arr2Index] = result;

        return result;
    }

    private void generate(Integer head, Set<Node> tails, Set<Node> result) {
        for (Node tail : tails) {
            Node newList = new Node(head);
            newList.next = tail;
            result.add(newList);
        }
    }

    public static class Node {
        @Getter
        private final int v;
        private Node next;

        public Node(int v) {
            this.v = v;
        }
    }
}

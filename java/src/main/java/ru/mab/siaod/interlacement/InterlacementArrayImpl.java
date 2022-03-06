package ru.mab.siaod.interlacement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InterlacementArrayImpl {
    public Set<List<Integer>> interlace(List<Integer> arr1, List<Integer> arr2) {
        Set<List<Integer>>[][] cache = new Set[arr1.size()][arr2.size()];
        return subInterlace(arr1, 0, arr2, 0, cache);
    }

    private Set<List<Integer>> subInterlace(List<Integer> arr1, int arr1Index, List<Integer> arr2, int arr2Index, Set<List<Integer>>[][] cache) {
        if (arr1Index < arr1.size() && arr2Index < arr2.size()) {
            if (cache[arr1Index][arr2Index] != null) {
                return cache[arr1Index][arr2Index];
            }
        }

        if (arr1Index >= arr1.size()) {
            return Set.of(arr2.subList(arr2Index, arr2.size()));
        }

        if (arr2Index >= arr2.size()) {
            return Set.of(arr1.subList(arr1Index, arr1.size()));
        }

        Set<List<Integer>> result = new HashSet<>();

        Set<List<Integer>> first = subInterlace(arr1, arr1Index + 1, arr2, arr2Index, cache);
        generate(arr1.get(arr1Index), first, result);

        Set<List<Integer>> second = subInterlace(arr1, arr1Index, arr2, arr2Index + 1, cache);
        generate(arr2.get(arr2Index), second, result);

        cache[arr1Index][arr2Index] = result;

        return result;
    }

    private void generate(Integer head, Set<List<Integer>> tails, Set<List<Integer>> result) {
        for (List<Integer> list : tails) {
            List<Integer> newList = new ArrayList<>(list.size() + 1);
            newList.add(head);
            newList.addAll(list);
            result.add(newList);
        }
    }
}

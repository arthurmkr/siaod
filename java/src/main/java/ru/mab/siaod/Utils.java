package ru.mab.siaod;

import org.apache.commons.io.FileUtils;
import ru.mab.siaod.leetcode.SuccessfulPairsOfSpellsAndPotions;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<int[]> readArrays(String fileName) {
        try {
            List<String> strings = FileUtils.readLines(new File(SuccessfulPairsOfSpellsAndPotions.class.getClassLoader().getResource("2.txt").toURI()), "UTF-8");

            List<int[]> res = new ArrayList<>();
            for (String s : strings) {
                String[] numbers = s.split(",");
                int[] arr = new int[numbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    arr[i] = Integer.parseInt(numbers[i]);
                }
                res.add(arr);
            }
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}

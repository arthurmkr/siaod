package ru.mab.siaod.median;

/**
 * Алгоритм поиска К-й статистики
 */
public interface StatisticsSelector {
    default int find(int[] arr, int k) {
        return find(arr, 0, arr.length - 1, k);
    }

    /**
     * Поиск К-ой статистики в диапазоне [left, right]. Исходный массив может быть модифицирован.
     *
     * @param arr   исходный массив
     * @param left  индекс начала диапазона поиска
     * @param right индекс конца диапазона поиска
     * @param k     порядковый номер статистики
     * @return значение К-й статистики
     */
    int find(int[] arr, int left, int right, int k);

    default int median(int[] arr) {
        return find(arr, arr.length / 2);
    }
}

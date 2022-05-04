package ru.mab.siaod.tree;

import ru.mab.siaod.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Дерево ван Эмде Боаса
 */
public class VEBTree {
    public static void main(String[] args) {
        int U = (int) Math.pow(2, 4);
        VEBTree vebTree = new VEBTree(U);

        List<Integer> values = new ArrayList<>();
        int N = 16;

        for (int i = 0; i < N; i++) {
            int v = RandomUtil.nextInt(U);
            values.add(v);
            vebTree.insert(v);
        }

        System.out.println(values);
        for (Integer integer : values) {
            if (!vebTree.isMember(integer)) {
                System.out.println(integer);
            }
        }
    }

    /**
     * Нижний квадратный корень
     */
    private final long lowerSqrt;
    /**
     * Верхний квадратный корень
     */
    private final int upperSqrt;
    /**
     * Размер универсума
     */
    private final long u;
    /**
     * Суммарная информация по дереву
     */
    VEBTree summary;
    /**
     * Минимальное значение в дереве, корнем которого является текущий узел
     */
    Long min;
    /**
     * Максимальное значение в дереве, корнем которого является текущий узел
     */
    Long max;

    /**
     * Дочерние узлы
     */
   private final Map<Integer, VEBTree> clusterMap;

    public VEBTree(long u) {
        double power = Math.log(u) / Math.log(2) / 2;
        this.lowerSqrt = (long) Math.pow(2, Math.floor(power));
        this.upperSqrt = (int) Math.pow(2, Math.ceil(power));

        this.u = u;
        clusterMap = new TreeMap<>();
    }

    private VEBTree getSummary() {
        assert u <= 2 : "Universe must be greater than 2";
        if (summary == null) {
            summary = new VEBTree(upperSqrt);
        }
        return summary;
    }

    private int low(long x) {
        return (int) (x % lowerSqrt);
    }

    private int high(long x) {
        return (int) Math.floorDiv(x, lowerSqrt);
    }

    private Long index(long x, Long offset) {
        if (offset == null) {
            return null;
        }
        return x * lowerSqrt + offset;
    }

    private Long min(VEBTree node) {
        return node == null ? null : node.min;
    }

    private Long max(VEBTree node) {
        return node == null ? null : node.max;
    }

    private void emptyInsert(long x) {
        min = max = x;
    }

    private boolean isMember(long x) {
        if (x == min || x == max) {
            return true;
        } else if (u == 2) {
            return false;
        } else {
            VEBTree cluster = getCluster(high(x));
            return cluster != null && cluster.isMember(low(x));
        }
    }

    private VEBTree getCluster(int index) {
        return clusterMap.get(index);
    }

    private void insert(long x) {
        if (min == null) {
            emptyInsert(x);
        } else {
            if (x < min) {
                long temp = min;
                min = x;
                x = temp;
            }

            if (u > 2) {
                int high = high(x);
                int low = low(x);

                VEBTree cluster = getCluster(high);
                if (min(cluster) == null) {
                    getSummary().insert(high);
                    VEBTree newCluster = createCluster(high);
                    newCluster.emptyInsert(low);
                } else {
                    cluster.insert(low);
                }
            }

            if (x > max) {
                max = x;
            }
        }
    }

    private VEBTree createCluster(int high) {
        VEBTree vebTree = new VEBTree(lowerSqrt);
        clusterMap.put(high, vebTree);

        return vebTree;
    }

    private Long successor(long x) {
        if (x >= u) {
            return null;
        }

        if (u == 2) {
            if (x == 0 && max == 1) {
                return 1L;
            } else {
                return null;
            }
        } else if (min != null && x < min) {
            return min;
        } else {
            int highX = high(x);
            VEBTree highCluster = getCluster(highX);
            Long maxLow = max(highCluster);
            int lowX = low(x);

            if (maxLow != null && lowX < maxLow) {
                Long offset = highCluster.successor(lowX);
                return index(highX, offset);
            } else {
                Long successorClusterIndex = getSummary().successor(highX);
                if (successorClusterIndex == null) {
                    return null;
                } else {
                    Long offset = min(getCluster(Math.toIntExact(successorClusterIndex)));
                    return index(successorClusterIndex, offset);
                }
            }
        }
    }
}

package ru.mab.siaod.tree;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Дерево ван Эмде Боаса.
 * Рализация работает только с уникальными значениями
 */
public class VEBTree {
    public static void main(String[] args) {
        int U = (int) Math.pow(2, 4);
        VEBTree vebTree = new VEBTree(U);

        Random random = new Random();
        List<Integer> values =
//                List.of(2, 3, 4, 5, 7, 14, 15);
                List.of(5, 3, 8, 4, 2);
//                random.longs(10, 0, 15).boxed().collect(toList());
//        int N = 16;

//        for (int i = 0; i < N; i++) {
//            int v = RandomUtil.nextInt(U);
//            values.add(v);
//            vebTree.insert(v);
//        }

        values.forEach(vebTree::insert);

        System.out.println(values);
        for (Integer value : values) {
            if (!vebTree.isMember(value)) {
                System.out.println(value);
            }
        }

        for (Integer value : values) {
            vebTree.delete(value);
        }

        for (Integer value : values) {
            if (vebTree.isMember(value)) {
                System.out.println(value);
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
     * Дочерние узлы
     */
    private final Map<Integer, VEBTree> clusterMap;
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
        if (min == null) {
            return false;
        } else if (x == min || x == max) {
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

    private void delete(long x) {
        if (min != null && min.equals(max)) {
            min = max = null;
        } else if (u == 2) {
            if (x == 0) {
                min = 1L;
            } else {
                min = 0L;
            }

            max = min;
        } else {
            if (min != null && x == min) {
                Long firstCluster = min(summary);
                x = index(firstCluster, min(getCluster(Math.toIntExact(firstCluster))));
                min = x;
            }

            int highX = high(x);
            VEBTree cluster = getCluster(highX);
            cluster.delete(low(x));

            VEBTree clusterWithX = getCluster(highX);
            if (min(clusterWithX) == null) {
                getSummary().delete(highX);

                if (x == max) {
                    Long summaryMax = max(summary);

                    if (summaryMax == null) {
                        max = min;
                    } else {
                        max = index(summaryMax, max(getCluster(Math.toIntExact(summaryMax))));
                    }
                }
            } else if (x == max) {
                max = index(highX, max(clusterWithX));
            }
        }
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

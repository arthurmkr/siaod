package ru.mab.siaod.tree;

/**
 * Дерево ван Эмде Боаса
 */
public class VEBTree {
    public static void main(String[] args) {
        VEBTree tree = new VEBTree(16);
        long[] arr = {15, 2, 14, 3, 7, 4, 5};
//        long[] arr = {1, 2, 3, 4, 5, 6};
        for (long x : arr) {
            tree.insert(x);
        }

        for (int i = 0; i <= 16; i++) {
            System.out.println(i + " -> " + tree.successor(i));
        }
    }

    /**
     * Размер универсума
     */
    long u;
    /**
     * Нижний квадратный корень
     */
    long lowerSqrt;
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
    VEBTree[] cluster;

    public VEBTree(long u) {
        this.lowerSqrt = (long) Math.floor(Math.sqrt(u));
        int upperSqrt = (int) Math.ceil(Math.sqrt(u));

        this.u = u;
        cluster = new VEBTree[upperSqrt];
        if (u > 2) {
            summary = new VEBTree(upperSqrt);
        }
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
            return cluster[high(x)].isMember(low(x));
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

                if (min(cluster[high]) == null) {
                    summary.insert(high);
                    cluster[high] = new VEBTree(lowerSqrt);
                    cluster[high].emptyInsert(low);
                } else {
                    cluster[high].insert(low);
                }
            }

            if (x > max) {
                max = x;
            }
        }
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
            VEBTree highCluster = cluster[highX];
            Long maxLow = max(highCluster);
            int lowX = low(x);

            if (maxLow != null && lowX < maxLow) {
                Long offset = highCluster.successor(lowX);
                return index(highX, offset);
            } else {
                Long successorClusterIndex = summary.successor(highX);
                if (successorClusterIndex == null) {
                    return null;
                } else {
                    Long offset = min(cluster[Math.toIntExact(successorClusterIndex)]);
                    return index(successorClusterIndex, offset);
                }
            }
        }
    }
}

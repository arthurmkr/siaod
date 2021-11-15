package ru.mab.siaod.tree;

public class OptimalBST {
    /*
    Задана последовательность ключей K = {k1, k2, ... kn} требуется вычислить оптимальное BST.
    Имеются вероятности Pi поиска i-го ключа.
    Имеются фиктивные ключи (отсутствующие в дереве) D = {d0,d1, ... dn} причем d0 < k1 и ki < di.
    Имеются вероятности поиска фиктивных ключей Qi
     */
    public static void main(String[] args) {
        // Вероятности поиска i-го ключа
        double[] p = {0.15, 0.10, 0.05, 0.10, 0.20};
        // Вероятности поиска фиктивного i-го ключа,
        double[] q = {0.05, 0.10, 0.05, 0.05, 0.05, 0.1};

        int[][] roots = evalOptimalBSTRoots(p, q);

        print(roots, 0, roots.length - 1, true);
    }

    private static int[][] evalOptimalBSTRoots(double[] p, double[] q) {
        int[][] roots = new int[p.length][p.length];
        // суммарное мат ожидание поиска в поддереве с ключами Ki...Kj
        double[][] w = new double[q.length][q.length];
        // мат ожидание стоимости поиска в поддереве с ключами Ki...Kj
        double[][] e = new double[q.length][q.length];

        for (int i = 0; i < q.length; i++) {
            w[i][i] = e[i][i] = q[i];
        }

        for (int l = 0; l < p.length; l++) {
            for (int i = 0; i < p.length - l; i++) {
                int j = i + l + 1;
                e[i][j] = Double.MAX_VALUE;
                w[i][j] = w[i][j - 1] + p[j - 1] + q[j];

                for (int r = i; r < j; r++) {
                    double t = e[i][r] // стоимость поиска в левом поддереве
                            + e[r + 1][j] // стоимость поиска в правом поддереве
                            + w[i][j];

                    // отбираем оптимальное решение
                    if (t < e[i][j]) {
                        e[i][j] = t;
                        roots[i][j - 1] = r; // сохраняем вершину оптимального поддерева
                    }
                }
            }
        }

        return roots;
    }

    private static void print(int[][] roots, int i, int j, boolean left) {
        if (i > j) {
            subPrint(i, j + 2, j + 1, left, "d");
            return;
        }
        int r = roots[i][j];

        if (i == 0 && j == roots.length - 1) {
            System.out.println("k" + (r + 1) + " is root");
        } else {
            subPrint(r + 1, j + 2, i, left, "k");
        }
        print(roots, i, r - 1, true);
        print(roots, r + 1, j, false);

    }

    private static void subPrint(int key, int leftParent, int rightParent, boolean left, String keyPrefix) {
        if (left) {
            System.out.println(keyPrefix + key + " is left child of k" + leftParent);
        } else {
            System.out.println(keyPrefix + key + " is right child of k" + rightParent);
        }
    }
}

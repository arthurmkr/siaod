package ru.mab.siaod.graph;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

class DFSTest {

    public static void main(String[] args) throws IOException, URISyntaxException {
        ClassLoader classLoader = DFSTest.class.getClassLoader();
        URI uri = classLoader.getResource("arka_integration_public_sku2.csv")
                .toURI();
        Set<String> fromDb = new HashSet<>(Files.readAllLines(Path.of(uri)));

        Set<String> fromRef = new HashSet<>(Files.readAllLines(Path.of(classLoader.getResource("catalog-excel.csv")
                .toURI())));
        List<String> rows = new ArrayList<>();
        for (String row : fromDb) {
            String[] split = row.split(";");

            if (split.length == 0) {
                continue;
            }
            if (!fromRef.contains(split[split.length - 1])) {
                rows.add(row);
            }
        }

        Files.write(Path.of("result.csv"), rows);
    }

    @Test
    void bfs() {
        Graph<Integer, SimpleGraphNode<Integer>> graph = new DirectedMapGraph<>();

        for (int i = 0; i < 6; i++) {
            graph.addNode(new SimpleGraphNode<>(i));
        }

        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 1);
        graph.addEdge(4, 3);
        graph.addEdge(5, 5);

        System.out.println(graph);

        DFS algo = new DFS();
        Map<Integer, DFS.DFSNode<Integer, SimpleGraphNode<Integer>>> res = algo.dfs(graph);
        res.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
//        System.out.println(res);
    }
}

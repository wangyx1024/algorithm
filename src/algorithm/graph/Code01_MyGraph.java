package algorithm.graph;


import java.util.ArrayList;
import java.util.List;

/**
 * 我的图结构
 */
public class Code01_MyGraph<T> {

    public List<Node> nodes;
    public List<Edge> edges;

    public class Node {
        public T value;
        // 入度
        public int in;
        // 出度
        public int out;
        // 指向谁，下标
        public List<Integer> nexts;
        // 边
        public List<Integer> edges;

        public Node() {
        }

        public Node(T value) {
            this.value = value;
            this.in = 0;
            this.out = 0;
            this.nexts = new ArrayList<>();
            this.edges = new ArrayList<>();
        }
    }

    public class Edge {
        // 从谁出发
        public Integer from;
        // 指向谁
        public Integer to;
        // 权重
        public int weight;

        public Edge() {
        }

        public Edge(Integer from, Integer to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public Code01_MyGraph(int[][] matrix) {
    }
}

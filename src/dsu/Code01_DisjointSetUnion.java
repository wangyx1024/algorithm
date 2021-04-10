package dsu;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 并查集
 */
public class Code01_DisjointSetUnion {

    public static void main(String[] args) {
        Student s1 = new Student("s1");
        Student s2 = new Student("s2");
        Student s3 = new Student("s3");

        Student s4 = new Student("s4");
        Student s5 = new Student("s5");

        Dsu dsu = new Dsu<Student>();
        dsu.add(s1);
        dsu.add(s2);
        dsu.add(s3);
        dsu.add(s4);
        dsu.add(s5);

//        System.out.println(dsu);
        dsu.union(s1, s2);
        System.out.println(dsu.groupNum());
        dsu.union(s2, s3);
        System.out.println(dsu.groupNum());

        dsu.union(s4, s5);
        System.out.println(dsu.groupNum());


        dsu.union(s3, s5);
    }

    public static class Student {
        public String name;

        public Student(String name) {
            this.name = name;
        }
    }

    public static class Dsu<V> {

        private Map<V, V> parentMap;
        private Map<V, Integer> groupSizeMap;

        public Dsu() {
            this.parentMap = new HashMap<>();
            this.groupSizeMap = new HashMap<>();
        }

        public void add(V v) {
            if (this.parentMap.containsKey(v)) {
                return;
            }

            this.parentMap.put(v, v);
            this.groupSizeMap.put(v, 1);
        }

        public boolean isSameSet(V v1, V v2) {
            V v1Ancestor = this.findAncestor(v1);
            V v2Ancestor = this.findAncestor(v2);
            return v1Ancestor == v2Ancestor;
        }

        public void union(V v1, V v2) {
            if (v1 == null || v2 == null || this.isSameSet(v1, v2)) {
                return;
            }

            V v1Ancestor = this.findAncestor(v1);
            V v2Ancestor = this.findAncestor(v2);
            int v1GroupSize = this.groupSizeMap.get(v1Ancestor);
            int v2GroupSize = this.groupSizeMap.get(v2Ancestor);

            V bigAncestor;
            V smallAncestor;
            if (v1GroupSize >= v2GroupSize) {
                bigAncestor = v1Ancestor;
                smallAncestor = v2Ancestor;
            } else {
                bigAncestor = v2Ancestor;
                smallAncestor = v1Ancestor;
            }

            this.parentMap.put(smallAncestor, bigAncestor);
            this.groupSizeMap.put(bigAncestor, v1GroupSize + v2GroupSize);
            this.groupSizeMap.remove(smallAncestor);
        }

        public int groupNum() {
            return this.groupSizeMap.size();
        }

        private V findAncestor(V v) {
            if (v == null) {
                return null;
            }

            Stack<V> stack = new Stack<>();

            V parent;
            while (true) {
                parent = this.parentMap.get(v);
                if (parent == v) {
                    break;
                }

                v = parent;
                stack.push(v);
            }

            // 重要优化，第一次查的时候整理指针，后面每次查都是O(1)
            while (!stack.isEmpty()) {
                this.parentMap.put(stack.pop(), v);
            }

            return v;
        }
    }
}

package algorithm.greedy;


import java.util.*;

/**
 * 工程最大利润
 */
public class Code04_MaxProjectProfit {

    public static void main(String[] args) {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project(10, 20));
        projects.add(new Project(30, 100));
        projects.add(new Project(200, 100));

        List<Project> myProjects = greedySolve(200, 2, projects);
        System.out.println(myProjects);
    }

    public static class Project {
        public int cost;
        public int profit;

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }

        @Override
        public String toString() {
            return "{" +
                    "cost=" + cost +
                    ", profit=" + profit +
                    '}';
        }
    }

    private static class LowCostHighProfitComparator implements Comparator<Project> {

        @Override
        public int compare(Project o1, Project o2) {
            int r1 = o1.cost - o2.cost;
            if (r1 != 0) {
                return r1;
            } else {
                return o2.profit - o1.profit;
            }
        }
    }

    private static class HighProfitLowCostComparator implements Comparator<Project> {

        @Override
        public int compare(Project o1, Project o2) {
            int r1 = o2.profit - o1.profit;
            if (r1 != 0) {
                return r1;
            } else {
                return o1.cost - o2.cost;
            }
        }
    }

    /**
     * @param startCapital 初始资金
     * @param maxProject   最多能做几个项目
     * @param projects     项目
     * @return
     */
    public static List<Project> greedySolve(int startCapital, int maxProject, List<Project> projects) {
        if (projects == null) {
            return new ArrayList<>();
        }

        PriorityQueue<Project> minCostHeap = new PriorityQueue<Project>(new LowCostHighProfitComparator());
        PriorityQueue<Project> maxProfitHeap = new PriorityQueue<Project>(new HighProfitLowCostComparator());

        for (Project project : projects) {
            if (project.profit > 0) {
                minCostHeap.add(project);
            }
        }

        List<Project> myProjects = new ArrayList<>();

        int money = startCapital;
        while (myProjects.size() < maxProject) {
            while (!minCostHeap.isEmpty() && minCostHeap.peek().cost <= money) {
                maxProfitHeap.add(minCostHeap.poll());
            }

            if (!maxProfitHeap.isEmpty()) {
                Project myProject = maxProfitHeap.poll();
                money = money + myProject.profit;
                myProjects.add(myProject);
            } else {
                break;
            }
        }

        return myProjects;
    }
}

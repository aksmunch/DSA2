import java.util.*;

// Water Pipeline Network Monitoring using BFS & DFS

class Graph {

    private Map<String, List<String>> adjList;

    Graph() {
        adjList = new HashMap<>();
    }

    // Add Water Tank
    void addTank(String tank) {
        adjList.putIfAbsent(tank, new ArrayList<>());
    }

    // Add Pipeline Connection
    void addPipeline(String tank1, String tank2) {

        adjList.get(tank1).add(tank2);
        adjList.get(tank2).add(tank1);
    }

    // BFS Traversal
    void bfs(String start) {

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        System.out.println("\n===== BFS WATER FLOW ANALYSIS =====");

        while (!queue.isEmpty()) {

            String tank = queue.poll();

            System.out.println(tank);

            for (String neighbor : adjList.get(tank)) {

                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        System.out.println(
                "\nTotal Connected Tanks : "
                        + visited.size());
    }

    // DFS Traversal
    void dfs(String tank, Set<String> visited) {

        visited.add(tank);

        System.out.println(tank);

        for (String neighbor : adjList.get(tank)) {

            if (!visited.contains(neighbor)) {

                dfs(neighbor, visited);
            }
        }
    }
}

// Main Class
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Graph g = new Graph();

        System.out.print(
                "Enter Number of Water Tanks: ");

        int n = sc.nextInt();
        sc.nextLine();

        // Add Tanks
        for (int i = 0; i < n; i++) {

            System.out.print(
                    "Enter Tank Name: ");

            String tank = sc.nextLine();

            g.addTank(tank);
        }

        // Add Pipelines
        System.out.print(
                "\nEnter Number of Pipeline Connections: ");

        int m = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < m; i++) {

            System.out.print(
                    "Enter Source Tank: ");

            String tank1 = sc.nextLine();

            System.out.print(
                    "Enter Connected Tank: ");

            String tank2 = sc.nextLine();

            g.addPipeline(tank1, tank2);
        }

        // Starting Tank
        System.out.print(
                "\nEnter Starting Tank: ");

        String startTank = sc.nextLine();

        // BFS
        g.bfs(startTank);

        // DFS
        System.out.println(
                "\n===== DFS PIPELINE INSPECTION =====");

        Set<String> visited =
                new HashSet<>();

        g.dfs(startTank, visited);

        sc.close();
    }
}

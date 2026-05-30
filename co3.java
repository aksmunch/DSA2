import java.util.*;

// College Social Network Analysis using BFS and DFS

class Graph {

    private Map<String, List<String>> adjList;

    Graph() {
        adjList = new HashMap<>();
    }

    // Add Student
    void addStudent(String student) {
        adjList.putIfAbsent(student, new ArrayList<>());
    }

    // Add Friendship
    void addFriendship(String s1, String s2) {

        adjList.get(s1).add(s2);
        adjList.get(s2).add(s1);
    }

    // BFS Traversal
    void bfs(String start) {

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        System.out.println("\n===== BFS TRAVERSAL =====");

        while (!queue.isEmpty()) {

            String student = queue.poll();

            System.out.println(student);

            for (String friend : adjList.get(student)) {

                if (!visited.contains(friend)) {

                    visited.add(friend);
                    queue.add(friend);
                }
            }
        }

        System.out.println("\nTotal Connected Students : "
                + visited.size());
    }

    // DFS Traversal
    void dfs(String student, Set<String> visited) {

        visited.add(student);

        System.out.println(student);

        for (String friend : adjList.get(student)) {

            if (!visited.contains(friend)) {
                dfs(friend, visited);
            }
        }
    }
}

// Main Class
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Graph g = new Graph();

        System.out.print("Enter Number of Students: ");
        int n = sc.nextInt();
        sc.nextLine();

        // Add Students
        for (int i = 0; i < n; i++) {

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            g.addStudent(name);
        }

        // Add Friendships
        System.out.print("\nEnter Number of Friendships: ");
        int m = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < m; i++) {

            System.out.print("Enter Friend 1: ");
            String s1 = sc.nextLine();

            System.out.print("Enter Friend 2: ");
            String s2 = sc.nextLine();

            g.addFriendship(s1, s2);
        }

        // Starting Student
        System.out.print("\nEnter Starting Student: ");
        String start = sc.nextLine();

        // BFS
        g.bfs(start);

        // DFS
        System.out.println("\n===== DFS TRAVERSAL =====");

        Set<String> visited = new HashSet<>();
        g.dfs(start, visited);

        sc.close();
    }
}
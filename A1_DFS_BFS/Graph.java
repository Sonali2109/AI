import java.util.*;

public class Graph {
    private Map<Integer, List<Integer>> graph;

    public Graph() {
        graph = new HashMap<>();
    }

    public void addEdge(int u, int v) {
        graph.putIfAbsent(u, new ArrayList<>());
        graph.putIfAbsent(v, new ArrayList<>());
        graph.get(u).add(v);
        graph.get(v).add(u); // Since the graph is undirected
    }

    public void dfs(int start) {
        Set<Integer> visited = new HashSet<>();
        System.out.print("DFS of Graph: ");
        dfsHelper(start, visited);
        System.out.println();
    }

    private void dfsHelper(int vertex, Set<Integer> visited) {
        visited.add(vertex);
        System.out.print(vertex + " ");

        for (int neighbor : graph.getOrDefault(vertex, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    public void bfs(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        System.out.print("BFS of Graph: ");
        bfsHelper(queue, visited);
        System.out.println();
    }

    private void bfsHelper(Queue<Integer> queue, Set<Integer> visited) {
        if (queue.isEmpty()) {
            return;
        }
        int vertex = queue.poll();
        System.out.print(vertex + " ");

        for (int neighbor : graph.getOrDefault(vertex, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                queue.add(neighbor);
            }
        }
        bfsHelper(queue, visited);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the graph: ");
        int size = scanner.nextInt();
        Graph g = new Graph();

        System.out.print("Enter the size of input: ");
        int inputSize = scanner.nextInt();

        for (int i = 0; i < inputSize; i++) {
            System.out.print("Enter edges " + (i + 1) + " of graph: ");
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            g.addEdge(u, v);
        }

        while (true) {
            System.out.println("\nMenu");
            System.out.println("1. Depth First Search (DFS)");
            System.out.println("2. Breadth First Search (BFS)");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter starting vertex for DFS: ");
                int start = scanner.nextInt();
                g.dfs(start);
            } else if (choice == 2) {
                System.out.print("Enter starting vertex for BFS: ");
                int start = scanner.nextInt();
                g.bfs(start);
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}

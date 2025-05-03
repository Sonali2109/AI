import java.util.*;

public class GraphColoring {
    private static boolean isValid(String node, String color, Map<String, String> assign, Map<String, List<String>> graph) {
        for (String neighbor : graph.get(node)) {
            if (assign.containsKey(neighbor) && assign.get(neighbor).equals(color)) {
                return false;
            }
        }
        return true;
    }

    private static void bnb(Map<String, String> assign, Map<String, List<String>> graph, List<String> colors, int[] minUsed, Map<String, String> best) {
        if (assign.size() == graph.size()) {
            Set<String> used = new HashSet<>(assign.values());
            if (used.size() < minUsed[0]) {
                minUsed[0] = used.size();
                best.clear();
                best.putAll(assign);
            }
            return;
        }

        String node = null;
        for (String n : graph.keySet()) {
            if (!assign.containsKey(n)) {
                node = n;
                break;
            }
        }

        for (String color : colors) {
            if (isValid(node, color, assign, graph)) {
                assign.put(node, color);
                if (assign.values().stream().distinct().count() <= minUsed[0]) {
                    bnb(assign, graph, colors, minUsed, best);
                }
                assign.remove(node);
            }
        }
    }

    public static Map<String, String> solve(Map<String, List<String>> graph, List<String> colors) {
        Map<String, String> assign = new HashMap<>();
        Map<String, String> best = new HashMap<>();
        int[] minUsed = {colors.size() + 1};
        bnb(assign, graph, colors, minUsed, best);
        return best.isEmpty() ? null : best;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> graph = new HashMap<>();
        List<String> colors = new ArrayList<>();
        Map<String, String> result = null;

        while (true) {
            System.out.println("\n--- Graph Coloring Menu ---");
            System.out.println("1. Input Graph & Colors");
            System.out.println("2. Solve");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                System.out.print("Enter node names (space-separated): ");
                String[] nodes = scanner.nextLine().split(" ");
                for (String node : nodes) {
                    System.out.print("Enter neighbors of " + node + " (space-separated): ");
                    String[] neighbors = scanner.nextLine().split(" ");
                    graph.put(node, Arrays.asList(neighbors));
                }
                System.out.print("Enter colors (space-separated): ");
                colors = Arrays.asList(scanner.nextLine().split(" "));
            } else if (choice.equals("2")) {
                if (!graph.isEmpty() && !colors.isEmpty()) {
                    result = solve(graph, colors);
                    if (result != null) {
                        System.out.println("\nColor Assignment:");
                        for (Map.Entry<String, String> entry : result.entrySet()) {
                            System.out.println(entry.getKey() + ": " + entry.getValue());
                        }
                        System.out.println("Colors Used: " + new HashSet<>(result.values()).size() + " / " + colors.size());
                    } else {
                        System.out.println("No valid coloring found.");
                    }
                } else {
                    System.out.println("Please input the graph and colors first.");
                }
            } else if (choice.equals("3")) {
                System.out.println("Exiting program...");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}

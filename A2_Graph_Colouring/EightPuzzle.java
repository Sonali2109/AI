import java.util.*;

public class EightPuzzle {
    private static final int[][] GOAL_STATE = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
    };

    private static final int[][] DIRECTIONS = {
            {-1, 0}, // up
            {1, 0},  // down
            {0, -1}, // left
            {0, 1}   // right
    };

    public static void main(String[] args) {
        int[][] initialState = getInputState();
        List<int[][]> solutionPath = aStar(initialState);

        if (solutionPath != null) {
            printPath(solutionPath);
        } else {
            System.out.println("No solution found.");
        }
    }

    private static int[][] getInputState() {
        Scanner scanner = new Scanner(System.in);
        int[][] state = new int[3][3];
        System.out.println("Enter the initial state as 9 numbers (0 represents the blank tile):");
        for (int i = 0; i < 3; i++) {
            System.out.printf("Enter row %d (3 numbers separated by spaces): ", i + 1);
            String[] input = scanner.nextLine().split(" ");
            for (int j = 0; j < 3; j++) {
                state[i][j] = Integer.parseInt(input[j]);
            }
        }
        return state;
    }

    private static List<int[][]> aStar(int[][] startState) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.f));
        Set<String> visited = new HashSet<>();

        Node startNode = new Node(startState, 0, hMisplacedTiles(startState), null);
        pq.add(startNode);

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            String currentTuple = stateToTuple(currentNode.state);

            if (visited.contains(currentTuple)) {
                continue;
            }
            visited.add(currentTuple);

            if (Arrays.deepEquals(currentNode.state, GOAL_STATE)) {
                return constructPath(currentNode);
            }

            int[] blankPosition = findBlank(currentNode.state);
            int x = blankPosition[0];
            int y = blankPosition[1];

            for (int[] direction : DIRECTIONS) {
                int nx = x + direction[0];
                int ny = y + direction[1];
                if (isValid(nx, ny)) {
                    int[][] newState = cloneState(currentNode.state);
                    // Swap the blank tile with the adjacent tile
                    newState[x][y] = newState[nx][ny];
                    newState[nx][ny] = 0;

                    if (!visited.contains(stateToTuple(newState))) {
                        int newG = currentNode.g + 1;
                        int newH = hMisplacedTiles(newState);
                        Node newNode = new Node(newState, newG, newH, currentNode);
                        pq.add(newNode);
                    }
                }
            }
        }
        return null; // No solution found
    }

    private static List<int[][]> constructPath(Node node) {
        List<int[][]> path = new ArrayList<>();
        while (node != null) {
            path.add(0, node.state);
            node = node.parent;
        }
        return path;
    }

    private static int hMisplacedTiles(int[][] state) {
        int misplaced = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] != 0 && state[i][j] != GOAL_STATE[i][j]) {
                    misplaced++;
                }
            }
        }
        return misplaced;
    }

    private static int[] findBlank(int[][] state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return null; // Should never reach here if the state is valid
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < 3 && y >= 0 && y < 3;
    }

    private static String stateToTuple(int[][] state) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : state) {
            for (int num : row) {
                sb.append(num).append(",");
            }
        }
        return sb.toString();
    }

    private static int[][] cloneState(int[][] state) {
        int[][] newState = new int[3][3];
        for (int i = 0; i < 3; i++) {
            newState[i] = Arrays.copyOf(state[i], state[i].length);
        }
        return newState;
    }

    private static void printPath(List<int[][]> path) {
        for (int step = 0; step < path.size(); step++) {
            System.out.printf("Step %d:\n", step);
            for (int[] row : path.get(step)) {
                System.out.println(Arrays.toString(row));
            }
            System.out.println();
        }
    }

    // Node class to represent each state in the search
    private static class Node {
        int[][] state; // Current state of the puzzle
        int g; // Cost from start to this node
        int h; // Heuristic cost to goal
        int f; // Total cost (g + h)
        Node parent; // Parent node

        public Node(int[][] state, int g, int h, Node parent) {
            this.state = state;
            this.g = g;
            this.h = h;
            this.f = g + h;
            this.parent = parent;
        }
    }
}

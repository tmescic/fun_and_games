import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Finds the minimum distance starting from [0, 0] to the destination (marked with 2). Can only travel
 * up, down, left and right through nodes marked with 1. Nodes marked with 0 are not accessible.
 *
 * Example:
 * 1 1 1 0 1
 * 1 0 1 1 0
 * 1 0 2 1 0
 * 1 0 1 0 1
 * 1 1 1 0 1
 *
 * Min distance: 4
 * (0, 0) -> (0, 1) -> (0, 2) -> (1, 2) -> (2, 2)
 *
 * Solved using breadth first search.
 *
 *
 * A more complex case (with weights) could be solved by using Dijkstra's algorithm.
 */
public class MinDistanceMatrix {

    /**
     * @param area the area we are searching.
     * @return minimum distance from (0, 0) to a node marked with 2, or -1 if there is no path.
     */
    public int minDistance(int[][] area) {

        // used to hold distances
        int[][] distance = new int[area.length][area[0].length];
        // used to mark nodes as visited distances
        boolean[][] visited = new boolean[area.length][area[0].length];
        // do a breadth first search

        List<List<Integer>> queue = new ArrayList<>();
        queue.add(Arrays.asList(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {

            List<Integer> current = queue.remove(0);
            int i = current.get(0);
            int j = current.get(1);

            // did we reach teh destination?
            if (area[i][j] == 2) {
                return distance[i][j];
            }

            int currentDistance = distance[i][j];

            // get reachable neighbours that were not already visited
            List<List<Integer>> neighbours = getNeighbours(i, j, area, visited);

            for (List<Integer> n : neighbours) {
                distance[n.get(0)][n.get(1)] = currentDistance + 1;
                visited[n.get(0)][n.get(1)] = true;
                queue.add(n);
            }

        }

        return -1;
    }

    private List<List<Integer>> getNeighbours(int i, int j, int[][] area, boolean[][] visited) {

        List<List<Integer>> neighbours = new ArrayList<>();

        // find neighbours (up, down, left & right) with these checks
        // 1. not out of matrix bounds
        // 2. element is accessible ( != 0)
        // 3. not already visited


        // up
        if (i != 0 && !visited[i - 1][j] && area[i - 1][j] != 0) {
            neighbours.add(Arrays.asList(i - 1, j));
        }

        // down
        if (i != area.length - 1 && !visited[i + 1][j] && area[i + 1][j] != 0) {
            neighbours.add(Arrays.asList(i + 1, j));
        }

        // left
        if (j != 0 && !visited[i][j - 1] && area[i][j - 1] != 0) {
            neighbours.add(Arrays.asList(i, j - 1));
        }

        // right
        if (j != area[0].length - 1 && !visited[i][j + 1] && area[i][j + 1] != 0) {
            neighbours.add(Arrays.asList(i, j + 1));
        }

        return neighbours;
    }

    public static void main (String ... args) {
        int[][] area1 = new int[][] {
                {1, 1, 1, 0, 1},
                {1, 0, 1, 1, 0},
                {1, 0, 2, 1, 0},
                {1, 0, 1, 0, 1},
                {1, 1, 1, 0, 1}
        };

        int[][] area2 = new int[][] {
                {1, 1, 1, 0, 1},
                {1, 0, 1, 1, 0},
                {1, 0, 1, 1, 0},
                {1, 0, 1, 0, 2}
        };

        int[][] area3 = new int[][] {
                {1, 1, 1, 0},
                {1, 0, 1, 1},
                {1, 0, 0, 1},
                {1, 0, 1, 0},
                {1, 1, 2, 0}
        };

        int[][] area4 = new int[][] {
                {2, 1, 1, 0, 1},
                {1, 0, 1, 1, 0},
                {1, 0, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {1, 1, 1, 0, 1}
        };


        int[][] area5 = new int[][] {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };

        int[][] area6 = new int[][] {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 2}
        };


        System.out.println("Min. distance1: " + new MinDistanceMatrix().minDistance(area1));
        System.out.println("Min. distance2: " + new MinDistanceMatrix().minDistance(area2));
        System.out.println("Min. distance3: " + new MinDistanceMatrix().minDistance(area3));
        System.out.println("Min. distance4: " + new MinDistanceMatrix().minDistance(area4));
        System.out.println("Min. distance5: " + new MinDistanceMatrix().minDistance(area5));
        System.out.println("Min. distance6: " + new MinDistanceMatrix().minDistance(area6));
    }
}


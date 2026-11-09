
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class FireDrillExits {

    public static int[][] exitDistance(char[][] plan) {

        int rows = plan.length;
        int cols = plan[0].length;

        int[][] dist = new int[rows][cols];

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            Arrays.fill(dist[i], -1);

            for (int j = 0; j < cols; j++) {

                if (plan[i][j] == 'E') {
                    dist[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int[] cell = queue.poll();

            int r = cell[0];
            int c = cell[1];

            for (int k = 0; k < 4; k++) {

                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr >= 0 && nr < rows &&
                        nc >= 0 && nc < cols &&
                        plan[nr][nc] != '#' &&
                        dist[nr][nc] == -1) {

                    dist[nr][nc] = dist[r][c] + 1;

                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {

        char[][] plan = {
                {'.', '#', '.', 'E'},
                {'.', '.', '.', '#'},
                {'E', '#', '.', '.'}
        };

        int[][] result = exitDistance(plan);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}

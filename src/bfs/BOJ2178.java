package bfs;

import java.io.*;
import java.util.*;

public class BOJ2178 {
    /**
     * 1. 아이디어
     * - 이중 for문 : 값 == 1 && 방문x => BFS
     * - BFS 돌면서 이동 가능한 다음 인덱스의 값을 현재 인덱스의 값+1
     *
     * 2. 시간복잡도
     * - BFS = O(V+E)
     * - V : 100 * 100
     * - E : 4 * 100 * 100
     * - V + E : 5 * 10000 = 5만 < 1억 (가능)
     *
     * 3. 자료구조
     * - 미로 : int[][]
     * - 방문여부 : boolean[][]
     * - BFS : Queue
     */

    static int[][] map;
    static boolean[][] visit;
    static int N, M;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new boolean[N][M];

        for(int i=0; i<N; i++) {
            String temp = br.readLine();
            for(int j=0; j<temp.length(); j++) {
                map[i][j] = temp.charAt(j) - '0';
            }
        }

        bfs(0, 0);
        System.out.println(map[N-1][M-1]);
    }

    static void bfs(int y, int x) {
        visit[y][x] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int cy = current[0];
            int cx = current[1];

            for(int i=0; i<4; i++) {
                int my = cy + dy[i];
                int mx = cx + dx[i];

                if(0 <= my && my < N && 0 <= mx && mx < M) {
                    if(map[my][mx] > 0 && !visit[my][mx]) {
                        visit[my][mx] = true;
                        queue.add(new int[]{my, mx});
                        map[my][mx] = map[cy][cx] + 1;
                    }
                }
            }
        }
    }
}

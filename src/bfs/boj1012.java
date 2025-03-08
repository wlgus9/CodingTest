package bfs;

import java.io.*;
import java.util.*;

public class boj1012 {
    /**
     * 1. 아이디어
     * - T : 테스트 케이스 수만큼 반복
     * - 이중 for : 값 == 1 && 방문X => BFS
     * - BFS를 통해 찾은 값 저장 후 출력
     *
     * 2. 시간 복잡도
     * - BFS : O(V+E)
     * - V : 50 * 50
     * - E : 4 * 50 * 50
     * - V+E : 5 * 2500 = 12500 < 1억 (가능)
     *
     * 3. 자료구조
     * - 그래프 : int[][]
     * - 방문여부 : boolean[][]
     */

    private static int[][] map;
    private static boolean[][] visit;
    private static final int[] dy = {0, 1, 0, -1};
    private static final int[] dx = {-1, 0, 1, 0};
    private static int T, N, M, K, sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine()); // 테스트케이스 수

        for(int t=0; t<T; t++) {
            sum = 0;

            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken()); // 세로
            M = Integer.parseInt(st.nextToken()); // 가로
            K = Integer.parseInt(st.nextToken()); // 배추 위치의 수

            map = new int[N][M];
            visit = new boolean[N][M];

            for(int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                map[a][b] = 1;
            }

            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(map[i][j] == 1 && !visit[i][j]) {
                        sum++;
                        bfs(i, j);
                    }
                }
            }

            System.out.println(sum);
        }
    }

    private static void bfs(int y, int x) {
        visit[y][x] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            int cy = current[0];
            int cx = current[1];

            for(int i=0; i<4; i++) {
                int my = cy + dy[i];
                int mx = cx + dx[i];

                if((0 <= my && my < N) && (0 <= mx && mx < M)) {
                    if(map[my][mx] == 1 && !visit[my][mx]) {
                        visit[my][mx] = true;
                        queue.add(new int[]{my, mx});
                    }
                }
            }
        }
    }
}

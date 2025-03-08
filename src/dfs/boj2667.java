package dfs;

import java.io.*;
import java.util.*;

public class boj2667 {
    /**
     * 1. 아이디어
     * - 이중 for문 : 값 == 1 && 방문x => DFS
     * - DFS를 통해 찾은 값 저장 후 정렬해서 출력
     *
     * 2. 시간복잡도
     * - DFS : O(V+E)
     * - V : N^2
     * - E : 4N^2
     * - V + E : 5N^2 -> N^2 -> 625 < 2억 => 가능!
     *
     * 3. 자료구조
     * - 그래프 저장 : int[][]
     * - 방문여부 : boolean[][]
     */

    private static int[][] map;
    private static boolean[][] visit;
    private static List<Integer> result = new ArrayList<>();
    private static int each = 0;
    private static final int[] dy = {0, 1, 0, -1};
    private static final int[] dx = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 총 단지 수

        map = new int[N][N];
        visit = new boolean[N][N];

        for(int i=0; i<N; i++) {
            String[] temp = br.readLine().split("");
            for(int j=0; j< temp.length; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] == 1 && !visit[i][j]) {
                    each = 0;
                    dfs(i, j);
                    result.add(each);
                }
            }
        }

        Collections.sort(result);
        System.out.println(result.size());
        for(int r : result) {
            System.out.println(r);
        }
    }

    private static void dfs(int y, int x) {
        visit[y][x] = true;
        each++;

        for(int i=0; i<4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if((0 <= ny && ny < map.length) && (0 <= nx && nx < map.length)) {
                if(map[ny][nx] == 1 && !visit[ny][nx]) {
                    dfs(ny, nx);
                }
            }
        }
    }
}

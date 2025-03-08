package dfs;

import java.io.*;
import java.util.*;

public class boj2606 {
    /**
     * 1. 아이디어
     * - 이중 for문 : 값 == 1 && 방문x => DFS
     * - DFS릁 통해 감염된 컴퓨터 수 합산하고 출력
     *
     * 2. 시간 복잡도
     * - DFS : O(V+E)
     * - V : 100 * 100
     * - E : 4 * 100 * 100
     * - V + E : 5 * 10000 = 5만 < 1억 => 가능
     *
     * 3. 자료구조
     * - 그래프 : int[][]
     * - 방문여부 : boolean[]
     */

    private static int[][] map;
    private static boolean[] visit;
    private static int count, com, conn = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        com = Integer.parseInt(br.readLine()); // 컴퓨터의 수
        conn = Integer.parseInt(br.readLine()); // 연결되어 있는 컴퓨터 쌍의 수

        map = new int[com+1][com+1]; // 1번부터 시작해야 하기 때문에 인덱스는 +1 해줌
        visit = new boolean[com+1];

        StringTokenizer st;
        for(int i=0; i<conn; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = map[b][a] = 1; // 간선은 방향성이 없음 (A to B, B to A 동일)
        }

        dfs(1); // 1번부터 감염 시작

        System.out.println(count-1); // 1번 컴퓨터는 제외

        br.close();
    }

    private static void dfs(int i) {
        visit[i] = true;
        count++;

        for(int j=1; j<=com; j++) {
            if(map[i][j] == 1 && !visit[j]) {
                dfs(j);
            }
        }
    }
}

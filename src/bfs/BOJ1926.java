package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1926 {
    /**
     * 1. 아이디어
     * - 이중 for문 : 값 == 1 && 방문x => BFS
     * - BFS 돌면서 그림 개수+1, 최댓값 갱신
     *
     * 2. 시간복잡도
     * - BFS : O(V+E)
     * - V : 500 * 500
     * - E : 4 * 500 * 500
     * - V + E : 5 * 250000 = 100만 < 2억 => 가능!
     *
     * 3. 자료구조
     * - 그래프 전체 지도 : int[][]
     * - 방문여부 : boolean[][]
     * - Queue : BFS
     */

    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    private static int[][] map;
    private static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 세로
        int m = Integer.parseInt(st.nextToken()); // 가로

        map = new int[n][m];
        visit = new boolean[n][m];

        // 그림 정보 입력
        for (int i = 0; i < map.length; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < temp.length; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        List<Integer> list = new ArrayList<>();

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                // 해당 좌표가 1이고 방문하지 않았으면 탐색 후 리스트에 추가
                if(map[i][j] == 1 && !visit[i][j]) {
                    list.add(bfs(i, j));
                }
            }
        }

        Collections.sort(list);
        System.out.println(list.size()); // 그림 개수 출력

        // 그림의 넓이 출력
        if (list.isEmpty()) {
            System.out.println("0");
        } else {
            System.out.println(list.get(list.size() - 1));
        }
    }

    public static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y}); // 큐에 현재 좌표 추가

        visit[x][y] = true; // 현재 좌표는 방문 처리

        int sum = 1;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for(int i=0; i<4; i++) {
                // 이동할 좌표
                int mx = cx + dx[i];
                int my = cy + dy[i];

                // 이동한 좌표가 0보다 작거나 그림의 크기를 벗어나면 pass
                if (mx < 0 || mx >= map.length || my < 0 || my >= map[0].length){
                    continue;
                }

                // 이동한 좌표가 1이고 방문하지 않았으면
                if(map[mx][my] == 1 && !visit[mx][my]) {
                    queue.add(new int[]{mx, my}); // 다시 탐색할 수 있게 큐에 추가
                    visit[mx][my] = true; // 해당 좌표 방문 처리
                    sum++; // 그림 크기 갱신
                }
            }
        }

        return sum;
    }
}

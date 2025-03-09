package backtracking;

import java.io.*;
import java.util.*;

public class BOJ15649 {
    /**
     * 1. 아이디어
     * - 재귀함수 안에서 for 돌면서 숫자 선택 (방문여부 체크)
     * - 재귀함수에서 m개를 선택할 경우 print
     *
     * 2. 시간복잡도
     * - N! (가능)
     *
     * 3. 자료구조
     * - 결과값 저장 : List
     * - 방문여부 : boolean[]
     */

    static int[] result;
    static boolean[] visit;
    static int N, M = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        result = new int[M+1];
        visit = new boolean[N+1];

        back(0);
    }

    private static void back(int num) {
        if(num == M) {
            for(int i=0; i<M; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i=1; i<=N; i++) {
            if(!visit[i]) {
                visit[i] = true;
                result[num] = i;
                back(num+1);
                visit[i] = false;
            }
        }
    }
}

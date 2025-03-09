package backtracking;

import java.io.*;
import java.util.*;

public class BOJ15650 {
    /**
     * 1. 아이디어
     * - 재귀함수 안에서 for 돌면서 숫자 선택
     * - 재귀함수에서 m개를 선택할 경우 print
     *
     * 2. 시간복잡도
     * - N! (가능)
     *
     * 3. 자료구조
     * - 결과값 저장 : int[]
     */

    static int[] result;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        result = new int[M+1];

        back(1, 0);
    }

    static void back(int start, int depth) {
        if(depth == M) {
            for(int i=0; i<M; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();

            return;
        }

        for(int i=start; i<=N; i++) {
            result[depth] = i;
            back(i+1, depth+1);
        }
    }
}

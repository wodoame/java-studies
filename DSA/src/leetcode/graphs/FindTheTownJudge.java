package leetcode.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindTheTownJudge {
    static void main() {
        System.out.println(findJudge(4, new int[][]{
                {1,3},{1,4},{2,3}
        }));
    }

    // This is another way of writing the solution below using an array
    public static int findJudgeCompact2(int n, int[][] trust) {
        int[] delta = new int[n + 1];
        for(var edge: trust){
            delta[edge[0]]--; // outgoing
            delta[edge[1]]++; // incoming
        }
        for (int i = 1; i < n + 1; i++) {
            if(delta[n] == n - 1)return i;
        }
        return - 1;
    }

    // This is derived from the solution below by computing the incoming  - outgoing which should be (n - 1) for a judge
    public static int findJudgeCompact(int n, int[][] trust) {
        Map<Integer, Integer> delta = new HashMap<>();
        for(var edge: trust){
             int node1 = edge[0];
             int node2 = edge[1];
             delta.put(node1, delta.getOrDefault(node1, 0) - 1); // outgoing
             delta.put(node2, delta.getOrDefault(node2, 0) + 1); // incoming
        }
        for (int i = 1; i < n + 1; i++) {
           if(delta.getOrDefault(i, 0) == n - 1)return i;
        }
        return - 1;
    }

    public static int findJudge(int n, int[][] trust) {
        Map<Integer, Integer> incoming = new HashMap<>();
        Map<Integer, Integer> outgoing = new HashMap<>();
        for(var edge: trust){
             int node1 = edge[0];
             int node2 = edge[1];
             outgoing.put(node1, outgoing.getOrDefault(node1, 0) + 1);
             incoming.put(node2, incoming.getOrDefault(node2, 0) + 1);
        }
        for (int i = 1; i < n + 1; i++) {
           if(incoming.getOrDefault(i, 0) == n - 1 && outgoing.getOrDefault(i, 0) == 0)return i;
        }
        return - 1;
    }

}

package leetcode.graphs;

import java.util.*;

// leetcode 1971
// not optimal but will study optimal solution later
public class FindIfPathExistsInGraph {
    static void main() {
        int n = 3;
        int source = 0;
        int destination = 2;
        int[][] edges = {{0,1},{1,2},{2,0}};
        System.out.println(validPath(n, edges, source, destination));
    }
    static boolean validPath(int n, int[][] edges, int source, int destination) {
       var graph = buildGraph(edges);
       Set<Integer> visited = new HashSet<>();
       return hasPath(graph, source, destination, visited);
    }

    static boolean hasPath(Map<Integer, List<Integer>> graph, int source, int destination, Set<Integer> visited){
        if(visited.contains(source))return false;
        if(source == destination)return true;
        visited.add(source);
        for(var neighbor: graph.get(source)){
            if(hasPath(graph, neighbor, destination, visited))return true;
        }
        return false;
    }

    static Map<Integer, List<Integer>> buildGraph(int[][] edges){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(var edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            var node1Neighbors = graph.getOrDefault(node1, new ArrayList<>());
            var node2Neighbors = graph.getOrDefault(node2, new ArrayList<>());
            node1Neighbors.add(node2);
            node2Neighbors.add(node1);
            graph.put(node1, node1Neighbors);
            graph.put(node2, node2Neighbors);
        }
        return graph;
    }


}

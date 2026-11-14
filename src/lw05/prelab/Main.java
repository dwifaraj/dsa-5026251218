package lw05.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    static int size;
    static String[] names;
    static int[][] graph;

    static ArrayList<Integer> dfsOrder = new ArrayList<>();

    // Add an undirected edge
    static void addEdge(int u, int v, int weight) {
        graph[u][v] = weight;
        graph[v][u] = weight;
    }

    // Print visiting path
    static void printPath(List<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(names[path.get(i)]);

            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    // Breadth-First Search
    static List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);

            for (int i = 0; i < size; i++) {
                if (graph[current][i] != 0
                        && !visited.contains(i)) {
                    visited.add(i);
                    queue.add(i);
                }
            }
        }

        return order;
    }

    // Depth-First Search
    static void dfs(int current, Set<Integer> visited) {
        visited.add(current);
        dfsOrder.add(current);

        for (int i = 0; i < size; i++) {
            if (graph[current][i] != 0
                    && !visited.contains(i)) {
                dfs(i, visited);
            }
        }
    }

    // Dijkstra's Algorithm
    static void dijkstraAlg(int start, int destination) {
        int[] dist = new int[size];
        int[] prev = new int[size];
        boolean[] visited = new boolean[size];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);

        dist[start] = 0;

        for (int i = 0; i < size; i++) {
            int u = findTheNextNode(dist, visited);

            if (u == -1) {
                break;
            }

            visited[u] = true;

            for (int v = 0; v < size; v++) {
                if (graph[u][v] != 0 && !visited[v]
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                    prev[v] = u;
                }
            }
        }

        System.out.println("=== Shortest Time from "
                + names[start] + " ===");

        for (int i = 0; i < size; i++) {
            System.out.println(names[i] + " : "
                    + dist[i] + " min");
        }

        System.out.println("=== Shortest Route from "
                + names[start] + " to "
                + names[destination] + " ===");

        route(destination, prev);
        System.out.println();

        System.out.println("Total time: "
                + dist[destination] + " min");
    }

    // Find closest unvisited node
    static int findTheNextNode(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int nextNode = -1;

        for (int i = 0; i < size; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                nextNode = i;
            }
        }

        return nextNode;
    }

    // Print shortest route recursively
    static void route(int destination, int[] prev) {
        if (prev[destination] != -1) {
            route(prev[destination], prev);
        }

        System.out.print(names[destination]);

        if (destination != prev[destination]
                && destination != findStart(prev)) {
            System.out.print(" -> ");
        }
    }

    static int findStart(int[] prev) {
        for (int i = 0; i < size; i++) {
            if (prev[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        try {
            Scanner input = new Scanner(new File("src/lw05/prelab/campus.txt"));

            size = input.nextInt();

            names = new String[size];
            graph = new int[size][size];

            for (int i = 0; i < size; i++) {
                names[i] = input.next();
            }

            int edges = input.nextInt();

            for (int i = 0; i < edges; i++) {
                int u = input.nextInt();
                int v = input.nextInt();
                int weight = input.nextInt();

                addEdge(u, v, weight);
            }

            int start = input.nextInt();
            int destination = input.nextInt();

            input.close();

            System.out.println("=== BFS from "
                    + names[start] + " ===");
            printPath(bfs(start));

            System.out.println("=== DFS from "
                    + names[start] + " ===");

            dfsOrder.clear();
            dfs(start, new HashSet<>());
            printPath(dfsOrder);

            dijkstraAlg(start, destination);

        } catch (FileNotFoundException e) {
            System.out.println("campus.txt not found!");
        }
    }
}
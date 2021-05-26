// Bellman-Ford shortest path algorithm for directed, connected
// and weighted graph. Algorithm can handle negative weights and
// detect negative cycles.

package com.company;
import java.lang.*;


public class Graph {
    // Directed, connected and weighted graph
    class Edge {
        // Weighted edge in graph
        int start, end, weight;
        Edge() {
            start = end = weight = 0;
        }
    };

    int V, E;
    Edge edge[];

    Graph(int vertices, int edges) {
        V = vertices;
        E = edges;
        edge = new Edge[edges];
        for (int i = 0; i < edges; i++) {
            edge[i] = new Edge();
        }
    }

    void bellmanFord(Graph graph, int src) {

        int V = graph.V;
        int E = graph.E;
        int distance[] = new int[V];

        // Initializing distance table infinite values
        for (int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[src] = 0;

        for (int i = 1; i < V; ++i) {
            for (int j = 0; j < E; ++j) {
                int u = graph.edge[j].start;
                int v = graph.edge[j].end;
                int weight = graph.edge[j].weight;
                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v])
                    distance[v] = distance[u] + weight;
            }
        }

        // Checking negative cycles
        for (int j = 0; j < E; ++j) {
            int u = graph.edge[j].start;
            int v = graph.edge[j].end;
            int weight = graph.edge[j].weight;
            if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                System.out.println("negative cycle");
                return;
            }

        }
        printArr(distance, V);
    }

    void printArr(int distance[], int V) {
        System.out.println("Distance");
        for (int i = 0; i < V; ++i)
            System.out.println(i + "\t\t" + distance[i]);
        }

    public static void main(String[] args)
    {
        int vertices = 5;
        int edges = 8;

        Graph graph = new Graph(vertices, edges);

        graph.edge[0].start = 0;
        graph.edge[0].end = 1;
        graph.edge[0].weight = -1;

        graph.edge[1].start = 0;
        graph.edge[1].end = 2;
        graph.edge[1].weight = 4;

        graph.edge[2].start = 1;
        graph.edge[2].end = 2;
        graph.edge[2].weight = 3;

        graph.edge[3].start = 1;
        graph.edge[3].end = 3;
        graph.edge[3].weight = 2;

        graph.edge[4].start = 1;
        graph.edge[4].end = 4;
        graph.edge[4].weight = 2;

        graph.edge[5].start = 3;
        graph.edge[5].end = 2;
        graph.edge[5].weight = 5;

        graph.edge[6].start = 3;
        graph.edge[6].end = 1;
        graph.edge[6].weight = 1;

        graph.edge[7].start = 4;
        graph.edge[7].end = 3;
        graph.edge[7].weight = -3;

        graph.bellmanFord(graph, 0);
    }

}

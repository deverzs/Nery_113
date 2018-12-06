package edu.miracosta.cs113;

import java.util.HashSet;

/**
 * Dijkstra's Algorithm Class
 * Contains the algorithm for the Graph. Based on the code from Koffman and Wolfgang
 *
 * @author Oscar Fernandez, Zsuzsanna Dianovics, Jacob Valenzuela
 * @version 1.0
 */
public class DijkstraAlgorithm {

    /**
     * Shortest path algorithm
     * @param graph The weighted graph to be searched
     * @param start The start vertex
     * @param pred Array to contain predecessors
     * @param dist Array for shortest distance path
     */
    public static void dijkstrasAlgorithm(Graph graph, int start, int[] pred, double[] dist) {
        int numV = graph.getNumberOfVertices() ;
        HashSet<Integer> vMinusS = new HashSet<Integer>(numV) ;

        // Initializee V-S
        for (int i =0 ;i < numV ; i++) {
            if (i != start) {
                vMinusS.add(i) ;
            }
        }

        // Initialize pred and dest
        for (int v: vMinusS) {
            pred[v] = start ;
            dist[v] = graph.getEdge(start, v).getWeight() ;
        }

        // Main loop
        while (vMinusS.size() != 0) {
            double minDist = Double.POSITIVE_INFINITY ;
            int u = -1 ;

            // find the value u in V-S with smallest distance
            for (int v : vMinusS) {
                if (dist[v] < minDist) {
                    minDist = dist[v] ;
                    u = v ;
                }
            }

            // remove u from vMinusS
            vMinusS.remove(u) ;

            // update the distances
            for (int v : vMinusS) {
                if (graph.isEdge(u, v)) {
                    double weight = graph.getEdge(u, v).getWeight() ;
                    if (dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight ;
                        pred[v] = u ;
                    }
                }
            }
        }



    }
}

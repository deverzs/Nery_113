package edu.miracosta.cs113;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * DijkstraAlgorithm.java
 * Contains the algorithm for the Graph. Based on the code from Koffman and Wolfgang
 *
 * @author Oscar Fernandez, Zsuzsanna Dianovics, Jacob Valenzuela
 * @version 1.0
 */
public class DijkstraAlgorithm {

    private int[] pred ;
    private double[] dist ;
    private int start ;

    /**
     * Shortest path algorithm
     * @param graph The weighted graph to be searched
     * @param start The start vertex
     */
    public DijkstraAlgorithm(Graph graph, int start) {
        this.start = start ;
        int numV = graph.getNumberOfVertices() ;
        pred = new int [numV] ;
        dist = new double[numV] ;

        HashSet<Integer> vMinusS = new HashSet<Integer>(numV) ;

        // Initializes V-S
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

        int k = 1 ;
        // Main loop
        while (vMinusS.size() != 0) {
            double minDist = Double.POSITIVE_INFINITY ;
            int u = -1 ;

            // find the value u in V-S with smallest distance
            for (int v : vMinusS) {
                if (dist[v] <= minDist) {
                    minDist = dist[v] ;
                    u = v ;
                }
            }

            // remove u from vMinusS
            vMinusS.remove(u) ;


            // update the distances
            Iterator<Edge> edgeIter = graph.edgeIterator(u) ;

            while (edgeIter.hasNext()) {
                Edge edge = edgeIter.next() ;
                int v = edge.getDestination() ;
                if (vMinusS.contains(new Integer(v))) {
                    double weight = edge.getWeight() ;
                    if (dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight ;
                        pred[v] = u ;
                    }
                }
            }
        }



    }

    /**
     * Finds the shortest distance to the end from the beginning
     * @param end where to head
     * @return the directions to get there
     */
    public String findFromToEnd(int end) {
        StringBuilder sb = new StringBuilder() ;
        ArrayList paths = new ArrayList() ;
        while (end != start) {

            paths.add("From " + pred[end] + " go to " + end + "\n") ;
            end = pred[end] ;

        }
        int count = paths.size() ;
        while (count > 0) {
            sb.append(paths.get(count-1)) ;
            count-- ;
        }
        return sb.toString() ;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder() ;
        for (int i : pred) {
            sb.append(i + " ") ;
        }
        return sb.reverse().toString() ;
    }

}

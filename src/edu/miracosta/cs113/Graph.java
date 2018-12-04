package edu.miracosta.cs113;
/**
 * Graph.java : Interface for graph data structure. Implementation should provide a constructor with parameters
 * for number of vertices and whether or not the graph is directed. Based on code from Data Structures by
 * Koffman and Wolfgang
 *
 * @author Oscar Fernandez
 * @version 1.0
 */
import edu.miracosta.cs113.Edge;

import java.util.Iterator;

public interface Graph {
    /**
     * Gets the number of vertices in graph
     * @return The number of vertices
     */
    public int getNumberOfVertices();

    /**
     * Checks if graph is directed
     * @return True if graph is directed
     */
    public boolean isDirected();

    /**
     * Add new edge into graph
     * @param edge The new edge
     */
    public void insert(Edge edge);

    /**
     * Checks if an edge exists
     * @param source The source vertex
     * @param destination The destination vertex
     * @return True if there is and edge from source to destination
     */
    public boolean isEdge(int source, int destination);

    /**
     * Get the edge between two vertices
     * @param source The source vertex
     * @param destination The destination vertex
     * @return The edge between these two vertices or Edge with a weight if Double.POSITIVE_INFINITY if there is no edge
     */
    public Edge getEdge(int source, int destination);

    /**
     * Return an iterator to the edges connected to paramter vertex
     * @param source The source vertex
     * @return An Iterator to the vertices connected to source
     */
    public Iterator<Edge> edgeIterator(int source);
}

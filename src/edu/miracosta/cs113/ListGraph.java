package edu.miracosta.cs113;
/**
 * ListGraph.java : Graph class implemented using adjacency list method . Based on code from Data Structures by
 * Koffman and Wolfgang
 *
 * @author Oscar Fernandez, Zsuzsanna Dianovics, Jacob Valenzuela
 * @version 1.0
 */
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ListGraph<T> implements Graph {
    private List<Edge>[] edges;
    private T[] vertices;
    private int numVertices;
    private boolean isDirected;

    /**
     * Full Constructor to specify if graph is directed and number of vertices
     * @param numVertices
     * @param isDirected
     */
    public ListGraph(int numVertices, boolean isDirected){
        this.edges = new List[this.numVertices];
        this.vertices = (T[]) new Object[this.numVertices];
        this.numVertices = numVertices;
        this.isDirected = isDirected;

        for(int i = 0; i < numVertices; i++){
            this.edges[i] = new LinkedList<Edge>();
        }
    }

    @Override
    public int getNumberOfVertices() {
        return this.numVertices;
    }

    @Override
    public boolean isDirected() {
        return this.isDirected;
    }

    @Override
    public void insert(Edge edge) {
        this.edges[edge.getSource()].add(edge);
        if(!this.isDirected()){
            this.edges[edge.getDestination()].add(new Edge(edge.getDestination(), edge.getSource(), edge.getWeight()));
        }
    }

    @Override
    public boolean isEdge(int source, int destination) {
        return edges[source].contains(new Edge(source, destination));
    }

    @Override
    public Edge getEdge(int source, int destination) {
        Edge target = new Edge(source, destination, Double.POSITIVE_INFINITY);
        for(Edge edge : edges[source]){
            if(edge.equals(target)){
                return edge;
            }
        }
        return target;
    }

    @Override
    public Iterator<Edge> edgeIterator(int source) {
        return edges[source].iterator();
    }

    /**
     * Loads edges from an input file. Each line should contain three data values. "source dest weight"
     * @param scan Scanner connected to dataFile
     */
    public void loadEdgesFromFile(Scanner scan){
    }

    /**
     * Creates graph and loads data from input file. The first line should contain the number of vertices. The remaining lines should contain
     * the edge data as describes in loadEdgesFromFile
     * @param scan Scanner connected to dataFile
     * @param isDirected True if graph i directed, false otherwise
     * @return Graph with edges read from file
     */
    public Graph createGraph(Scanner scan, boolean isDirected){
        //TODO: fill vertices
        int numVertices = scan.nextInt();
        ListGraph<T> loadedGraph = new ListGraph<T>(numVertices, isDirected);
        loadedGraph.loadEdgesFromFile(scan);
        return loadedGraph;
    }
}

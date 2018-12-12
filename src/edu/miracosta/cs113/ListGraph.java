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
        this.edges = new List[numVertices];
        this.vertices = (T[]) new Object[numVertices];
        this.numVertices = numVertices;
        this.isDirected = isDirected;

        for(int i = 0; i < numVertices; i++){
            this.edges[i] = new LinkedList<Edge>();
        }
    }

    public ListGraph(T[] vertices, boolean isDirected){
        this.numVertices = vertices.length;
        this.isDirected = isDirected;
        this.edges = new List[this.numVertices];
        this.vertices = (T[]) new Object[this.numVertices];
        this.setVertices(vertices);

        for(int i = 0; i < this.numVertices; i++){
            this.edges[i] = new LinkedList<Edge>();
        }
    }

    /**
     * Sets current vertices to parameter values
     * @param vertices Array with vertices object for graph
     */
    public void setVertices(Object[] vertices){
        if(vertices.length > this.numVertices){
            throw new IllegalArgumentException("Number of vertices greater than capacity");
        } else{
            this.vertices = (T[]) vertices;
        }
    }

    /**
     * Gets vertex at give location
     * @param index Location of Object in vertices array. Must be within instantiated numVertices
     * @return Shallow copy of vertex at given index
     */
    public T getVertex(int index){
        return this.vertices[index];
    }

    /**
     * Gets list of all vertices information in graph
     * @return String with all [index]: vertexData
     */
    public String getVerticesString(){
        StringBuilder verts = new StringBuilder();
        for(int i = 0; i < this.vertices.length; i++){
            verts.append("[" + i + "]: " + this.vertices[i] + "\n");
        }
        return verts.toString();
    }

    /**
     * Gets String with list of all edges in graph
     * @return List of edges with [source]--- weight --->[dest]
     */
    public String getEdgesString(){
        StringBuilder listGraph = new StringBuilder();
        for(int i = 0; i < this.numVertices; i++){
            for(Edge edge : edges[i]){
                listGraph.append(edge + "\n");
            }
        }
        return listGraph.toString();
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
     * If weight is not given, default value of 1.0 will be used
     * @param scan Scanner connected to dataFile
     */
    public void loadEdgesFromFile(Scanner scan){
        //If only 2 values, use default weight of 1.0
        String line;
        String[] tokens;
        int source, destination;
        double weight;
        while (scan.hasNextLine()){
            line = scan.nextLine();
            tokens = line.split(" ");
            source = Integer.parseInt(tokens[0]);
            destination = Integer.parseInt(tokens[1]);
            weight = (tokens.length == 2 ? 1.0 : Double.parseDouble(tokens[2]));
            this.edges[source].add(new Edge(source, destination, weight));
        }
    }

    /**
     * Creates graph and loads data from input file. The first line should contain the number of vertices. The remaining lines should contain
     * the edge data as describes in loadEdgesFromFile
     * @param scan Scanner connected to dataFile
     * @param isDirected True if graph i directed, false otherwise
     * @return Graph with edges read from file
     */
    public static <E> ListGraph<E> createGraph(Scanner scan, boolean isDirected){
        //TODO: fill vertices
        int numVertices = Integer.parseInt(scan.nextLine());
        ListGraph<E> loadedGraph = new ListGraph<E>(numVertices, isDirected);
        loadedGraph.loadEdgesFromFile(scan);
        return loadedGraph;
    }

    @Override
    public String toString(){
        StringBuilder listGraph = new StringBuilder();
        for(int i = 0; i < this.numVertices; i++){
            listGraph.append("[" + i + "]: ");
            for(Edge edge : edges[i]){
                listGraph.append("(" + edge + ") ");
            }
            listGraph.append("\n");
        }
        return listGraph.toString();
    }


}

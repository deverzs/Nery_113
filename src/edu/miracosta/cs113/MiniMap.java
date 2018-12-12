package edu.miracosta.cs113;
/**
 * MiniMap.java : Class used to build map using a text file with all connected edges in a graph. Source and destination of a graph is tracked
 * to provide users with the shortest path to destination using Dijkstras algorithm.
 *
 * @author Oscar Fernandez, Zsuzsanna Dianovics, Jacob Valenzuela
 * @version 1.0
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MiniMap {
    //Constants
    public static final String MAP_FILE = "localMap.txt";
    public static final boolean DEFAULT_DIRECTED_CHOICE = true;

    //Instance Variables
    private int source;
    private int destination;
    private DijkstraAlgorithm shortCut;
    private ListGraph<?> currentMap;

    /**
     * Source and destination constructor that uses MAP_FILE for a directed graph of String objects
     * @param source Starting position
     * @param destination Ending position on map
     */
    public MiniMap(int source, int destination){
        this.source = source;
        this.destination = destination;
        this.currentMap = MiniMap.<String>buildMap(MAP_FILE, DEFAULT_DIRECTED_CHOICE);
        this.shortCut = new DijkstraAlgorithm(this.currentMap, this.source);
    }

    /**
     * Full Constructor with graph and Dijkstra algorithm parameters
     * @param mapFile File with all adjacent edges and number of vertices
     * @param isDirected True if graph is directed
     * @param source Starting position
     * @param destination Ending position on map
     */
    public MiniMap(String mapFile, boolean isDirected, int source, int destination){
        this.source = source;
        this.destination = destination;
        this.currentMap = MiniMap.buildMap(mapFile, isDirected);
        this.shortCut = new DijkstraAlgorithm(this.currentMap, this.source);
    }

    /**
     * Gets shortest path from current starting position
     * @return String with direction to current destination
     */
    public String getShortestPath(){
       return this.shortCut.findFromToEnd(this.destination);
    }

    /**
     * Gets shortest path from current parameter position
     * @param source Starting position
     * @return String with direction to current destination
     */
    public String getShortestPath(int source){
        this.shortCut = new DijkstraAlgorithm(this.currentMap, source);
        return this.shortCut.findFromToEnd(this.destination);
    }

    /**
     * Builds map from given file and direction choice
     * @param file Text file with number of vertices and list of edges
     * @param isDirected True if directed graph
     * @param <E> Type for ListGraph object
     * @return ListGraph with edges and vertices from file
     */
    public static <E> ListGraph<E> buildMap(String file, boolean isDirected){
        Scanner inFile = null;
        try{
            inFile = new Scanner(new FileInputStream(file));
        } catch (FileNotFoundException e){
            System.out.println("Error Loading File");
        }
        ListGraph<E> temp = ListGraph.createGraph(inFile, isDirected);
        inFile.close();
        return temp;
    }

    /**
     * Sets source for algorithm
     * @param source Starting position
     */
    public void setSource(int source){
        this.source = source;
    }

    /**
     * Sets destination on map
     * @param destination Ending position
     */
    public void setDestination(int destination){
        this.destination = destination;
    }

    /**
     * Gets starting position
     * @return Integer starting position
     */
    public int getSource(){
        return this.source;
    }

    /**
     * Gets starting position
     * @return Integer starting position
     */
    public int getDestination(){
        return this.destination;
    }

    /**
     * Sets data for each spot on the map
     * @param locations Array of location specific data of map
     */
    public void setMapVertices(Object[] locations){
        currentMap.setVertices(locations);
    }

    /**
     * Get location data from a specific location on map
     * @param index spot in array of vertices
     * @return Object found at given index
     */
    public Object getLocationData(int index){
        return currentMap.getVertex(index);
    }

    @Override
    public String toString(){
        StringBuilder map = new StringBuilder();
        map.append("Source: " + this.source + "\n");
        map.append("Destination: " + this.destination + "\n");
        map.append(this.currentMap.toString());
        return map.toString();
    }

    @Override
    public boolean equals(Object other){
        if(other == null || !(other instanceof MiniMap)){
            return false;
        } else{
            MiniMap temp = (MiniMap) other;
            return this.source == temp.source && this.destination == temp.destination &&
                    this.currentMap.toString().equals(temp.toString());
        }
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(this.source) + Integer.hashCode(this.destination) + this.currentMap.toString().hashCode();
    }
}

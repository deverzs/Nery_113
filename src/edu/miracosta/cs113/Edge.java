package edu.miracosta.cs113;

import java.util.Map;

/**
 * Edge.java : Edge for graph data structure that tracks source, destination and weight . Based on code from Data Structures by
 * Koffman and Wolfgang
 *
 * @author Oscar Fernandez, Zsuzsanna Dianovics, Jacob Valenzuela
 * @version 1.0
 */

public class Edge {
    // Instance Variables
    private int source;
    private int destination;
    private double weight;

    /**
     * Constructor for Edge
     * @param source vertex for Edge
     * @param destination  destination for vertex
     */
    public Edge(int source, int destination){
        this.source = source;
        this.destination = destination;
        this.weight = 1.0;
    }

    /**
     * Full constructor for Edge
     * @param source vertex for Edge
     * @param destination destination for vertex
     * @param weight the weight
     */
    public Edge(int source, int destination, double weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    /**
     * Return the source
     * @return source
     */
    public int getSource(){
        return this.source ;
    }

    /**
     * Return the destination
     * @return destination
     */
    public int getDestination(){
        return this.destination ;
    }

    /**
     * Returns the weight
     * @return weight
     */
    public double getWeight(){
        return this.weight ;
    }

    @Override
    public String toString(){
        return "[" + this.source + "]--- " + this.weight + " --->["  + this.destination + "]";
    }

    @Override
    public  int hashCode() {
        int sum = 0 ;
        Integer sourceHash = new Integer(source) ;
        Integer destHash = new Integer(destination) ;
        sum += sourceHash.hashCode() + destHash.hashCode() ;
        return sum ;

    }

    @Override
    public boolean equals(Object o ) {
        if (o == null ) {
            return false ;
        }
        if (!(o instanceof Edge)) {
            return false ;
        }
        Edge anotherEdge = (Edge) o ;
        return ( (destination == anotherEdge.getDestination()) )&&
                (source == anotherEdge.getSource())  ;
    }
}

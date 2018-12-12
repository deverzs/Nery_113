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
     * Default constructor.
     * Source, destination is 0 and weight is 1.
     */
    public  Edge() {
        this.source = 0;
        this.destination = 0;
        this.weight = 1;
    }

    /**
     * Copy Constuctor
     * @param e Another edge object
     */
    public Edge(Edge e) {
        this.source = e.source;
        this.destination = e.destination;
        this.weight = e.weight;
    }

    /**
     * Set the source
     * @param source new source
     */
    public void setSource(int source) {
        this.source = source ;
    }


    /**
     * Set destination
     * @param destination new destination
     */
    public void setDestination(int destination) {
        this.destination = destination ;
    }

    /**
     * Set weight
     * @param weight new weight
     */
    public void setWeight(double weight) {
        this.weight = weight ;
    }

    /**
     * Set all instance variables
     * @param source from
     * @param destination  going to
     * @param weight weight
     */
    public void setAll(int source, int destination, double weight) {
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

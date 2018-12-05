package edu.miracosta.cs113;
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

    public Edge(int source, int destination){
        this.source = source;
        this.destination = destination;
        this.weight = 1.0;
    }

    public Edge(int source, int destination, double weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public int getSource(){
        return 0;
    }

    public int getDestination(){
        return 0;
    }

    public double getWeight(){
        return 0.0;
    }

    public String toString(){
        return "Source: " + this.source + "\nDestination: " + this.destination + "\nWeight: " + this.weight;
    }
}

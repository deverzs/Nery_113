package edu.miracosta.cs113;

import org.w3c.dom.ls.LSException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MiniMap {
    public static final String MAP_FILE = "localMap.txt";
    public static final boolean DEFAULT_DIRECTED_CHOICE = true;

    private int source;
    private int destination;
    private DijkstraAlgorithm shortCut;
    private ListGraph<?> currentMap;

//    public MiniMap(int source)

    public MiniMap(int source, int destination){
        this.source = source;
        this.destination = destination;
        this.currentMap = MiniMap.buildMap(MAP_FILE, DEFAULT_DIRECTED_CHOICE);
        this.shortCut = new DijkstraAlgorithm(this.currentMap, this.source);
    }



    public String getShortestPath(){
       return this.shortCut.findFromToEnd(this.destination);
    }

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

    public void setSource(int source){
        this.source = source;
    }

    public void setDestination(int destination){
        this.destination = destination;
    }

    public void setMapVertices(Object[] locations){
        currentMap.setVertices(locations);
    }

    public Object getLocationData(int index){
        return currentMap.getVertex(index);
    }

    //return shortest path


}

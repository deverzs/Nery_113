package edu.miracosta.cs113;

import java.util.Scanner ;
import java.io.FileInputStream ;
import java.io.FileNotFoundException ;

public class TempDriver {

    public static void main (String[] args) {
        Scanner read = null ;
        System.out.println("teting") ;

        try {
            read = new Scanner(new FileInputStream("localMap.txt")) ;
        }
        catch (FileNotFoundException e) {
            System.out.println("Error.") ;
            System.exit(0) ;
        }
        ListGraph<String> localMap = ListGraph.createGraph(read, true);
//        System.out.println(localMap.getNumberOfVertices());
//        System.out.println(localMap);
        DijkstraAlgorithm dj = new DijkstraAlgorithm(localMap,0) ;
        System.out.println(dj.findFromToEnd(18)) ;
//
//        ListGraph graph = new ListGraph(5, false) ;
//        graph.loadEdgesFromFile(read) ;
//        DijkstraAlgorithm dj = new DijkstraAlgorithm(graph,1 ) ;
//        System.out.println(dj.findFromToEnd(3)) ;
//
//
//
//
//        System.out.println("Here.") ;


    } // end main



} // end class
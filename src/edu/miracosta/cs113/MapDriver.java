package edu.miracosta.cs113;

import java.util.Scanner ;
import java.io.FileInputStream ;
import java.io.FileNotFoundException ;

public class MapDriver {

    public static void main (String[] args) {
        Scanner read = null ;


        try {
            read = new Scanner(new FileInputStream("localMap.txt")) ;
        }
        catch (FileNotFoundException e) {
            System.out.println("Error.") ;
            System.exit(0) ;
        }
        ListGraph<String> localMap = ListGraph.createGraph(read, true);

        int start = 5;
        int end = 13 ;

        Display display = new Display() ;

        DijkstraAlgorithm dj = new DijkstraAlgorithm(localMap,start) ;
        System.out.println(dj.findFromToEnd(end)) ;




    } // end main



} // end class
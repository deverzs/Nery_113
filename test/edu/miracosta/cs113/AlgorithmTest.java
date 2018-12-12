package edu.miracosta.cs113;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * DijkstraAlgorithm.java
 * Dijkstra's Algorithm Test Class
 *  @author Oscar Fernandez, Zsuzsanna Dianovics, Jacob Valenzuela
 *  @version 1.0
 */
public class AlgorithmTest {

    public static final String EDGES_FILE = "directedDataTest.txt";
    public static final String EDGES_FILE2 = "undirectedDataTest.txt";
    private ListGraph<String> testGraph;


    public void loadEdgesFromFileTest(){

        testGraph = new ListGraph<>(5, true);
        Scanner inFile = null;
        try {
            inFile = new Scanner(new FileInputStream(EDGES_FILE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        testGraph.loadEdgesFromFile(inFile);
        inFile.close();
    }

    public void loadEdgesFromFileTest2(){

        testGraph = new ListGraph<>(5, false);
        Scanner inFile = null;
        try {
            inFile = new Scanner(new FileInputStream(EDGES_FILE2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        testGraph.loadEdgesFromFile(inFile);
        inFile.close();
    }

    @Test
    public  void testDijkstraUndirectedFrom0() {
        loadEdgesFromFileTest2();
        DijkstraAlgorithm dj = new DijkstraAlgorithm(testGraph, 0 ) ;
        String expected = "From 0 go to 1\n" ;

        assertEquals(dj.findFromToEnd(1), expected);

        expected = "From 0 go to 2\n" ;
        assertEquals(dj.findFromToEnd(2), expected);

        expected = "From 0 go to 2\nFrom 2 go to 3\n" ;
        assertEquals(dj.findFromToEnd(3), expected);

        expected = "From 0 go to 2\nFrom 2 go to 4\n" ;
        assertEquals(dj.findFromToEnd(4), expected);

    }

    @Test
    public  void testDijkstraUndirectedFrom1() {
        loadEdgesFromFileTest2();
        DijkstraAlgorithm dj = new DijkstraAlgorithm(testGraph, 1 ) ;
        String expected = "From 1 go to 2\n" ;

        assertEquals(dj.findFromToEnd(2), expected);

        expected = "From 1 go to 2\nFrom 2 go to 3\n" ;
        assertEquals(dj.findFromToEnd(3), expected);

        expected = "From 1 go to 2\nFrom 2 go to 4\n" ;
        assertEquals(dj.findFromToEnd(4), expected);

        expected = "From 1 go to 0\n" ;
        assertEquals(dj.findFromToEnd(0), expected);

    }

    @Test
    public  void testDijkstraUndirectedFrom2() {
        loadEdgesFromFileTest2();
        DijkstraAlgorithm dj = new DijkstraAlgorithm(testGraph, 2 ) ;
        String expected = "From 2 go to 3\n" ;

        assertEquals(dj.findFromToEnd(3), expected);

        expected = "From 2 go to 4\n" ;
        assertEquals(dj.findFromToEnd(4), expected);

        expected = "From 2 go to 0\n" ;
        assertEquals(dj.findFromToEnd(0), expected);

        expected = "From 2 go to 1\n" ;
        assertEquals(dj.findFromToEnd(1), expected);

    }

    @Test
    public  void testDijkstraUndirectedFrom3() {
        loadEdgesFromFileTest2();
        DijkstraAlgorithm dj = new DijkstraAlgorithm(testGraph, 3 ) ;
        String expected = "From 3 go to 4\n" ;

        assertEquals(dj.findFromToEnd(4), expected);

        expected = "From 3 go to 2\nFrom 2 go to 0\n" ;
        assertEquals(dj.findFromToEnd(0), expected);

        expected = "From 3 go to 2\n" ;
        assertEquals(dj.findFromToEnd(2), expected);

        expected = "From 3 go to 2\nFrom 2 go to 1\n" ;
        assertEquals(dj.findFromToEnd(1), expected);

    }

    @Test
    public  void testDijkstraUndirectedFrom4() {
        loadEdgesFromFileTest2();
        DijkstraAlgorithm dj = new DijkstraAlgorithm(testGraph, 4 ) ;

        String expected = "From 4 go to 2\n" ;
        assertEquals(dj.findFromToEnd(2), expected);

        expected = "From 4 go to 3\n" ;
        assertEquals(dj.findFromToEnd(3), expected);

        expected = "From 4 go to 2\nFrom 2 go to 1\n" ;
        assertEquals(dj.findFromToEnd(1), expected);

        expected = "From 4 go to 2\nFrom 2 go to 0\n" ;
        assertEquals(dj.findFromToEnd(0), expected);

    }

    @Test
    public  void testDijkstraDirectedFrom0() {
        loadEdgesFromFileTest();
        DijkstraAlgorithm dj = new DijkstraAlgorithm(testGraph, 0 ) ;
        String expected = "From 0 go to 1\n" ;

        assertEquals(dj.findFromToEnd(1), expected);

        expected = "From 0 go to 2\n" ;
        assertEquals(dj.findFromToEnd(2), expected);

        expected = "From 0 go to 2\nFrom 2 go to 3\n" ;
        assertEquals(dj.findFromToEnd(3), expected);

        expected = "From 0 go to 2\nFrom 2 go to 3\nFrom 3 go to 4\n" ;
        assertEquals(dj.findFromToEnd(4), expected);

    }

    @Test
    public  void testDijkstraDirectedFrom1() {
        loadEdgesFromFileTest();
        DijkstraAlgorithm dj = new DijkstraAlgorithm(testGraph, 1 ) ;
        String expected = "From 1 go to 2\n" ;

        assertEquals(dj.findFromToEnd(2), expected);

        expected = "From 1 go to 2\nFrom 2 go to 3\n" ;
        assertEquals(dj.findFromToEnd(3), expected);

        expected = "From 1 go to 2\nFrom 2 go to 3\nFrom 3 go to 4\n" ;
        assertEquals(dj.findFromToEnd(4), expected);

        expected = "From 1 go to 0\n" ;
        assertEquals(dj.findFromToEnd(0), expected);

    }

    @Test
    public  void testDijkstraDirectedFrom2() {
        loadEdgesFromFileTest();
        DijkstraAlgorithm dj = new DijkstraAlgorithm(testGraph, 2 ) ;
        String expected = "From 2 go to 3\n" ;

        assertEquals(dj.findFromToEnd(3), expected);

        expected = "From 2 go to 3\nFrom 3 go to 4\n" ;
        assertEquals(dj.findFromToEnd(4), expected);

        expected = "From 2 go to 0\n" ;
        assertEquals(dj.findFromToEnd(0), expected);

        expected = "From 2 go to 1\n" ;
        assertEquals(dj.findFromToEnd(1), expected);

    }

    @Test
    public  void testDijkstraDirectedFrom3() {
        loadEdgesFromFileTest();
        DijkstraAlgorithm dj = new DijkstraAlgorithm(testGraph, 3 ) ;
        String expected = "From 3 go to 4\n" ;

        assertEquals(dj.findFromToEnd(4), expected);

        expected = "From 3 go to 0\n" ;
        assertEquals(dj.findFromToEnd(0), expected);

        System.out.println(testGraph.toString()) ;
        expected = "From 3 go to 4\nFrom 4 go to 2\n" ;
        assertEquals(dj.findFromToEnd(2), expected);

    }

    @Test
    public  void testDijkstraDirectedFrom4() {
        loadEdgesFromFileTest();
        DijkstraAlgorithm dj = new DijkstraAlgorithm(testGraph, 4 ) ;
        String expected = "From 4 go to 2\n" ;

        assertEquals(dj.findFromToEnd(2), expected);

        expected = "From 4 go to 2\nFrom 2 go to 3\n" ;
        assertEquals(dj.findFromToEnd(3), expected);

    }


}

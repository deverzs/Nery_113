package edu.miracosta.cs113;
/**
 * ListGraphTest.java : Tester for ListGraph methods
 *
 * @author Oscar Fernandez, Zsuzsanna Dianovics, Jacob Valenzuela
 * @version 1.0
 */

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

import static org.junit.Assert.*;
public class ListGraphTest {
    public static final String EDGES_FILE = "directedDataTest.txt";
    public static final String GRAPH_FILE = "buildGraphTest.txt";
    private ListGraph<String> testGraph;

    public ListGraph<String> createGraph(String file, boolean isDirected){
        ListGraph<String> test;
        Scanner inFile = null;
        try {
            inFile = new Scanner(new FileInputStream(GRAPH_FILE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        test = ListGraph.createGraph(inFile, isDirected);
        inFile.close();
        return test;
    }

    @Test
    public void loadEdgesFromFileTest(){
        String expectedEdges = "[0]--- 0.5 --->[1]\n[0]--- 0.3 --->[2]\n[1]--- 0.5 --->[2]\n[2]--- 0.3 --->[3]\n[3]--- 0.5 --->[4]\n[4]--- 0.3 --->[2]\n";

        ListGraph<String> test = new ListGraph<>(5, true);
        Scanner inFile = null;
        try {
            inFile = new Scanner(new FileInputStream(EDGES_FILE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        test.loadEdgesFromFile(inFile);
        assertEquals("Unexpected set of edges",expectedEdges, test.getEdgesString());
        inFile.close();
    }

    @Test
    public void createGraphTest(){
        String expectedEdges = "[0]--- 0.5 --->[1]\n[0]--- 0.3 --->[2]\n[1]--- 0.5 --->[2]\n[2]--- 0.3 --->[3]\n[3]--- 0.5 --->[4]\n[4]--- 0.3 --->[2]\n";
        int expectedVertices = 5;
        ListGraph<String> test;
        Scanner inFile = null;
        try {
            inFile = new Scanner(new FileInputStream(GRAPH_FILE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        test = ListGraph.createGraph(inFile, true);
        inFile.close();
        assertEquals("Unexpected set of edges", expectedEdges, test.getEdgesString());
        assertEquals("Unexpected num of vertices", expectedVertices, test.getNumberOfVertices());
    }
    @Test
    public void testGetNumVertices(){
        ListGraph<String> test;
        int vertices;

        for(int i = 1; i < 5; i++){
            test = new ListGraph<>(i, true);
            vertices = test.getNumberOfVertices();
            assertEquals("Number of vertices are not matching", vertices, i);
        }
    }

    @Test
    public void testGetEdge(){
        Edge testEdge = new Edge(0, 2, 0.3);
        this.testGraph = this.createGraph(GRAPH_FILE, true);

        assertEquals("Expected Edge was not found", this.testGraph.getEdge(0, 2), testEdge);
        assertNotEquals("Are not equal", this.testGraph.getEdge(0, 5), testEdge);
    }

    @Test
    public void testInsert(){
        String expectedEdges = "[0]--- 0.5 --->[1]\n[0]--- 0.3 --->[2]\n[1]--- 0.5 --->[2]\n[2]--- 0.3 --->[3]\n[3]--- 0.5 --->[4]\n[4]--- 0.3 --->[2]\n[4]--- 0.5 --->[0]\n";
        Edge testEdge = new Edge(4, 0, 0.5);
        this.testGraph = this.createGraph(GRAPH_FILE, true);
        testGraph.insert(testEdge);
        assertEquals("Edge not inserted properly.", expectedEdges, testGraph.getEdgesString());
    }
    @Test
    public void testIsEdge(){
        this.testGraph = this.createGraph(GRAPH_FILE, true);
        assertTrue("Expected edge to exist", testGraph.isEdge(0, 2));
        assertFalse("Expected edge to not exist", testGraph.isEdge(0, 4));
    }

    @Test
    public void testEdgeIterator(){
        String expectedEdges = "[0]--- 0.5 --->[1]\n[0]--- 0.3 --->[2]\n";
        this.testGraph = this.createGraph(GRAPH_FILE, true);
        StringBuilder result = new StringBuilder();
        Iterator<Edge> edgeIt = testGraph.edgeIterator(0);
        while(edgeIt.hasNext()){
            result.append(edgeIt.next().toString() + "\n");
        }
        assertEquals("Expected same list of edges", expectedEdges, result.toString());
    }

    @Test
    public void testSetVertices(){
        String expectedVerts = "[0]: l1\n[1]: l2\n[2]: l3\n[3]: l4\n[4]: l5\n";
        String[] testLocations = {"l1", "l2", "l3", "l4", "l5"};
        this.testGraph = this.createGraph(GRAPH_FILE, true);
        this.testGraph.setVertices(testLocations);

        assertEquals("Expected equal vents", expectedVerts, testGraph.getVerticesString());
    }

    @Test
    public void testGetVertex(){
        String expectedVertex = "l4";
        String[] testLocations = {"l1", "l2", "l3", "l4", "l5"};
        this.testGraph = this.createGraph(GRAPH_FILE, true);
        this.testGraph.setVertices(testLocations);
        assertEquals("unexpected vertex detected", expectedVertex, testGraph.getVertex(3));
    }

}

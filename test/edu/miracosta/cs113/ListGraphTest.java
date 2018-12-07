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
        assertEquals(expectedEdges, test.getEdgesString());
        inFile.close();
    }
}

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
    @Test
    public void loadEdgesFromFileTest(){
        ListGraph<String> test = new ListGraph<>(5, true);
        Scanner inFile = null;
        Iterator<Edge> list;
        try {
            inFile = new Scanner(new FileInputStream("directedDataTest.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        test.loadEdgesFromFile(inFile);
        System.out.println("Edges loaded");
        for(int i = 0; i < test.getNumberOfVertices(); i++){
            list = test.edgeIterator(i);
            while (list.hasNext()){
                System.out.println(list.next());
            }
        }

        inFile.close();
    }
}

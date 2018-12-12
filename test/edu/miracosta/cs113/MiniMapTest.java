package edu.miracosta.cs113;
/**
 * MiniMapTest.java : Tester for MiniMap methods
 *
 * @author Oscar Fernandez, Zsuzsanna Dianovics, Jacob Valenzuela
 * @version 1.0
 */
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MiniMapTest {
    public static final String GRAPH_FILE = "buildGraphTest.txt";

    @Test
    public void testToString(){
        String expected = "Source: 1\nDestination: 3\n[0]: ([0]--- 0.5 --->[1]) ([0]--- 0.3 --->[2]) \n" +
                "[1]: ([1]--- 0.5 --->[2]) \n[2]: ([2]--- 0.3 --->[3]) \n[3]: ([3]--- 0.5 --->[4]) \n" +
                "[4]: ([4]--- 0.3 --->[2]) \n";
        MiniMap test = new MiniMap(GRAPH_FILE, true, 1, 3);
        assertEquals(expected, test.toString());
    }

    @Test
    public void testEquals(){
        MiniMap test = new MiniMap(GRAPH_FILE, true, 1, 3);
        MiniMap test2 = new MiniMap(GRAPH_FILE, true, 1, 3);
        assertEquals(test, test2);
        test2.setDestination(4);
        assertNotEquals(test, test2);
    }

    @Test
    public void testHashCode(){
        int expectedHash = 1707888898;
        MiniMap test = new MiniMap(GRAPH_FILE, true, 1, 3);
        assertEquals(expectedHash, test.hashCode());
    }

    @Test
    public void testGetShortestPath(){
        String expected ="From 1 go to 2\nFrom 2 go to 3\n";
        MiniMap test = new MiniMap(GRAPH_FILE, true, 1, 3);
        assertEquals(expected, test.getShortestPath());
    }

    @Test
    public void testGetShortestPathWithSource(){
        String expected ="From 2 go to 3\nFrom 3 go to 4\n";
        MiniMap test = new MiniMap(GRAPH_FILE, true, 0, 4);
        assertEquals(expected, test.getShortestPath(2));
    }

    @Test
    public void testReadLocationData(){
        String[] locations = new String[20];

        MiniMap.readLocationData(locations, "locationInformation.txt");

        for(String loc : locations){
            System.out.println(loc);
        }
    }
}

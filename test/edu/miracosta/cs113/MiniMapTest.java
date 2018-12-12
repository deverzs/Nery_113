package edu.miracosta.cs113;
/**
 * MiniMapTest.java : Tester for MiniMap methods
 *
 * @author Oscar Fernandez, Zsuzsanna Dianovics, Jacob Valenzuela
 * @version 1.0
 */
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MiniMapTest {
    public static final String GRAPH_FILE = "buildGraphTest.txt";

    @Test
    public void testToString(){
        String expected = "Source: 1\nDestination: 3\n[0]: ([0]--- 0.5 --->[1]) ([0]--- 0.3 --->[2]) \n" +
                "[1]: ([1]--- 0.5 --->[2]) \n[2]: ([2]--- 0.3 --->[3]) \n[3]: ([3]--- 0.5 --->[4]) \n" +
                "[4]: ([4]--- 0.3 --->[2]) \n";
        MiniMap test = new MiniMap(GRAPH_FILE, true, 1, 3);
        System.out.println(test);
        assertEquals(expected, test.toString());
    }
}

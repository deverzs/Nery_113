package edu.miracosta.cs113;

import org.junit.Test;


import static org.junit.Assert.*;

public class EdgeTest {

    Edge test = new Edge(0, 1) ;
    Edge test2 = new Edge(1,2,.34) ;

    @Test
    public void testGetSource() {

        assertEquals("oops! Wrong output!", 0, test.getSource());
        assertEquals("oops! Wrong output!", 1, test2.getSource());

    }

    @Test
    public void testGetSDestination() {

        assertEquals("oops! Wrong output!", 1, test.getDestination());
        assertEquals("oops! Wrong output!", 2, test2.getDestination());

    }

    @Test
    public void testGetWeight() {
        double expected = 1.0 ;
        double actual = test.getWeight() ;
        assertTrue("oops! That didn't work!", (expected==actual));

        expected = .34 ;
        actual = test2.getWeight() ;
        assertTrue("oops! That didn't work!", (expected==actual));

    }

    @Test
    public void testToString() {
        String expected = "[0]--- 1.0 --->[1]" ;
        String actual = test.toString() ;
        assertEquals(expected, actual);

         expected = "[1]--- 0.34 --->[2]" ;
         actual = test2.toString() ;
        assertEquals(expected, actual);

    }

    @Test
    public void testHashCode() {
        int expected = 1 ;
        int actual = test.hashCode() ;
        assertEquals(expected, actual);

        expected = 3 ;
        actual = test2.hashCode() ;
        assertEquals(expected, actual);
    }

    @Test
    public void testEqualsTrue() {
        Edge one = new Edge(12,22,.45) ;
        Edge two = new Edge(12, 22, .45) ;
        assertTrue(one.equals(two));
    }

    @Test
    public void testEqualsFalse() {
        Edge one = new Edge(12,22,.45) ;
        Edge two = new Edge(12, 21, .45) ;
        assertFalse(one.equals(two));
    }

    @Test
    public void testSetSource() {
        test.setSource(122) ;
        assertEquals(122, test.getSource());
    }

    @Test
    public void testSetDestination() {
        test.setDestination(132); ;
        assertEquals(132, test.getDestination());
    }

    @Test
    public void testSetWeight() {
        test.setWeight(.99);
        double actual = test.getWeight();
        double expected = .99 ;
        assertTrue(actual==expected);
    }

    @Test
    public void testSetAll() {
        test.setAll(32, 67, .22) ;
        assertEquals(32, test.getSource());
        assertEquals(67, test.getDestination());

        double actual = test.getWeight();
        double expected = .22 ;
        assertTrue(actual==expected);
    }
}

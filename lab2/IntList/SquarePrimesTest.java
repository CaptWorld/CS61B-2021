package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes() {
        IntList lst1 = IntList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        IntList expected1 = IntList.of(1, 4, 9, 4,25, 6, 49, 8, 9, 10, 121);
        boolean changed1 = IntListExercises.squarePrimes(lst1);
        assertEquals(expected1.toString(), lst1.toString());
        assertTrue(changed1);

        IntList lst3 = IntList.of(1, 4, 6, 8, 9, 10);
        IntList expected3 = IntList.of(1, 4, 6, 8, 9, 10);
        boolean changed3 = IntListExercises.squarePrimes(lst3);
        assertEquals(expected3.toString(), lst3.toString());
        assertFalse(changed3);
    }
}

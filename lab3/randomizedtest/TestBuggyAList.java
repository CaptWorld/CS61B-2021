package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        AList<Integer> correct = new AList<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();
        correct.addLast(10);
        correct.addLast(14);
        correct.addLast(-2);
        buggy.addLast(10);
        buggy.addLast(14);
        buggy.addLast(-2);

        assertEquals(correct.removeLast(), buggy.removeLast());
        assertEquals(correct.removeLast(), buggy.removeLast());
        assertEquals(correct.removeLast(), buggy.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> LBuggy = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                LBuggy.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int sizeBuggy = LBuggy.size();
                System.out.println("size: " + size);
                System.out.println("buggy size: " + sizeBuggy);
                assertEquals(size, sizeBuggy);
            } else if (operationNumber == 2) {
                // getLast
                if (L.size() > 0) {
                    int last = L.getLast();
                    int lastBuggy = LBuggy.getLast();
                    System.out.println("getLast: " + last);
                    System.out.println("buggy getLast: " + lastBuggy);
                    assertEquals(last, lastBuggy);
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (L.size() > 0) {
                    int last = L.removeLast();
                    int lastBuggy = LBuggy.removeLast();
                    System.out.println("removeLast: " + last);
                    System.out.println("buggy removeLast: " + lastBuggy);
                    assertEquals(last, lastBuggy);
                }
            }
        }

    }
}

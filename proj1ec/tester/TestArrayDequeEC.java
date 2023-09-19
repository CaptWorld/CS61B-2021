package tester;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

import static org.junit.Assert.assertEquals;

public class TestArrayDequeEC {
    @Test
    public void testStudentArrayDeque() {
        int iterations = 5000;
        int methodsToTest = 4;
        StringBuilder message = new StringBuilder();

        StudentArrayDeque<Integer> studentDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> correctDeque = new ArrayDequeSolution<>();

        for (int i = 0; i < iterations; i++) {
            double methodToTest = StdRandom.uniform(methodsToTest);

            if (methodToTest == 0) {
                Integer newItem = StdRandom.uniform(iterations);
                message.append("\naddFirst(").append(newItem).append(")");
                studentDeque.addFirst(newItem);
                correctDeque.addFirst(newItem);
            } else if (methodToTest == 1) {
                Integer newItem = StdRandom.uniform(iterations);
                message.append("\naddLast(").append(newItem).append(")");
                studentDeque.addLast(newItem);
                correctDeque.addLast(newItem);
            } else if (methodToTest == 2) {
                if (!studentDeque.isEmpty() && !correctDeque.isEmpty()) {
                    message.append("\nremoveFirst()");
                    Integer correctFirstRemoved = correctDeque.removeFirst();
                    Integer studentFirstRemoved = studentDeque.removeFirst();
                    assertEquals(message.toString(), correctFirstRemoved, studentFirstRemoved);
                }
            } else {
                if (!studentDeque.isEmpty() && !correctDeque.isEmpty()) {
                    message.append("\nremoveLast()");
                    Integer correctLastRemoved = correctDeque.removeLast();
                    Integer studentLastRemoved = studentDeque.removeLast();
                    assertEquals(message.toString(), correctLastRemoved, studentLastRemoved);
                }
            }
        }
    }
}

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

public class TestArrayDequeGold {
    static StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
    static ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();

    @Test
    public void testArrayDeque() {
        Integer num1 = StdRandom.uniform(1, 100);
        Integer num2 = StdRandom.uniform(1, 100);
        Integer num3 = StdRandom.uniform(1, 100);
        Integer num4 = StdRandom.uniform(1, 100);
        Integer num5 = StdRandom.uniform(1, 100);
        Integer num6 = StdRandom.uniform(1, 100);
        Integer num7 = StdRandom.uniform(1, 100);
        Integer num8 = StdRandom.uniform(1, 100);

        sad.addFirst(num4);
        sad.addFirst(num4);
        sad.addFirst(num4);
        sad.addFirst(num4);
        sad.addFirst(num4);
        sad.addFirst(num1);
        sad.addLast(num2);
        sad.addLast(num3);
        sad.addFirst(num4);
        sad.removeLast();
        sad.addLast(num5);
        sad.removeFirst();
        sad.addFirst(num6);
        sad.addLast(num7);
        sad.addFirst(num8);
        sad.removeLast();
        sad.removeLast();
        sad.removeLast();
        sad.removeLast();
        sad.removeLast();

        ads.addFirst(num4);
        ads.addFirst(num4);
        ads.addFirst(num4);
        ads.addFirst(num4);
        ads.addFirst(num4);
        ads.addFirst(num1);
        ads.addLast(num2);
        ads.addLast(num3);
        ads.addFirst(num4);
        ads.removeLast();
        ads.addLast(num5);
        ads.removeFirst();
        ads.addFirst(num6);
        ads.addLast(num7);
        ads.addFirst(num8);
        ads.removeLast();
        ads.removeLast();
        ads.removeLast();
        ads.removeLast();
        ads.removeLast();

        //assertEquals(""+"sad.removeFirst()",ads.removeFirst(),sad.removeFirst());
        assertEquals("\naddFirst(" + num4 + ");\n" +
                "addFirst(" + num4 + ");\n" +
                "addFirst(" + num4 + ");\n" +
                "addFirst(" + num4 + ");\n" +
                "addFirst(" + num4 + ");\n" +
                "addFirst(" + num1 + ");\n" +
                "addLast(" + num2 + ");\n" +
                "addLast(" + num3 + ");\n" +
                "addFirst(" + num4 + ");\n" +
                "removeLast();\n" +
                "addLast(" + num5 + ");\n" +
                "removeFirst();\n" +
                "addFirst(" + num6 + ");\n" +
                "addLast(" + num7 + ");\n" +
                "addFirst(" + num8 + ");\n" +
                "removeLast();\n" +
                "removeLast();\n" +
                "removeLast();\n" +
                "removeLast();\n" +
                "removeLast();\n" +
                "removeLast();\n", ads.removeLast(), sad.removeLast());
    }
}

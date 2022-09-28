package deque;

import static org.junit.Assert.*;
import org.junit.Test;


/**
 *
 */
public class ArrayDequeTest {


    @Test
    /**
     * add something into a deque, check whether methods isEmpty() and size()
     * are correct.
     */
    public void addIsEmptySizeTest(){
        var s = new ArrayDeque<Integer>();
        assertTrue("A newly created deque should be empty!",s.isEmpty());
        s.addFirst(123);

        assertEquals(1,s.size());
        assertFalse("after add one item, the deque should not be empty",s.isEmpty());
//        满了的情况会不会出现误判？

        s.addLast(456);
        s.addFirst(789);
        assertEquals(3,s.size());

        System.out.println("Printing deque as fllow:");
        s.printDeque();
    }


    @Test
    public void addRemoveTest(){
        var s = new ArrayDeque<String>();
        assertTrue("A new String List should be empty.",s.isEmpty());

        s.addFirst("First");
        assertFalse("Now it should not be empty.",s.isEmpty());

        s.addLast("Second");
        var t = s.removeFirst();
        assertEquals("the item should be First","First",t);
        assertEquals("remove from head then first should be zero","Second",s.get(0));

        t = s.removeLast();
        assertEquals("the item should be Second","Second",t);
        assertTrue("now the deque should be empty.",s.isEmpty());
    }

    @Test
    public void removeEmptyTest(){
        var s = new ArrayDeque<String>();

        s.addFirst("First");
        s.addLast("Second");

        s.removeLast();
        s.removeLast();
        s.removeLast();

        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + s.size() + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg,0,s.size());
    }


}

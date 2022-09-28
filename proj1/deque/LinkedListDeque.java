package deque;
import java.util.Iterator;
public class LinkedListDeque<T> {
    private final class Node {
        public T data;
        public Node next;
        public Node prev;

        private Node() {
            this.data = null;
            this.next = null;
            this.prev = null;
        }

        private Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        /*
        用到toString(), 这个方法应该被每个可用的T类型实现
        递归实现即可
        */

        private void printdata() {
            var rest = this.next;
            System.out.print(data.toString() + " ");
            if (rest == sentinel){
                return;
            }
            rest.printdata();
        }
    }

    //    双端队列
    int size;
    Node sentinel;
//    哨兵不储存，只是提供一种指针，所以优化掉单独last指针的原因就是因为有时last指向sentinel，而sentinel的data是null

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
//       问题：哨兵的data仍然是null
    }

    public void addFirst(T item) {
//        不用考虑item为null的情况
        var temp = new Node(item);
        sentinel.next.prev = temp;
        temp.next = sentinel.next;
        temp.prev = sentinel;
        sentinel.next = temp;
        size++;

    }


    public void addLast(T item) {
        var temp = new Node(item);
        sentinel.prev.next = temp;
        temp.prev = sentinel.prev;
        temp.next = sentinel;
        sentinel.prev = temp;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (sentinel.next == null)
            return;
        sentinel.next.printdata();
        System.out.println();
    }

    public T removeFirst(){
        if (sentinel.next == sentinel)
            return null;
        var temp = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.next.prev = sentinel;
//        显式的清除掉引用
        temp.prev = null;
        temp.next = null;
        size--;
        return temp.data;
    }

    public T removeLast(){
        if (sentinel.prev == sentinel)
            return null;
        var temp = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.prev.next = sentinel;
//        显式清除引用
        temp.prev = null;
        temp.next = null;
        size--;
        return temp.data;
    }

    public T get(int index){
//        非递归的实现（循环）
        Node p = this.sentinel;
        if (index > size || index < 0)
            return null;
//        int realindex = index % size();
//        是实现循环好还是直接检测好
        for (int i=0;i<index;i++){
            p = p.next;
        }
        return p.data;
    }

    public T getRecursive(int index){
        Node p = this.sentinel;
        if (index > size || index < 0)
            return null;

        return null;
    }

}

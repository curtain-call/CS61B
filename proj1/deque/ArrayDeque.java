package deque;

import java.util.Iterator;

/**
 * deque using array as core data structure.
 * fields are as follow.
 *
 * <pre>
 * adq, a T array.
 * front, the head pointer of Deque.
 * back, the tail pointer of deque.
 * capacity, the max capacity of deque.
 */
public class ArrayDeque<T> implements Iterable<T> {
    private T[] adq;
    private int front;
    private int back;
    private int size;
    private final int capacity;

    public ArrayDeque() {
        adq = (T[]) new Object[8];
        back = 0;
        front = 0;
        capacity = 8;
//        circular可以用%capacity来控制
    }

    public ArrayDeque(int capacity) {
        this.adq = (T[]) new Object[capacity];
        back = 0;
        front = 0;
        this.capacity = capacity;

    }

    public ArrayDeque(ArrayDeque<T> other) {
        this.adq = (T[]) new Object[8];
        size = other.size;
        capacity = other.capacity;
        front = other.front;
        back = other.back;
    }


    public void addLast(T item) {
//        assert item!=null;
        if (size > capacity / 2)
//      绝对值判断，因为是判断是否到一半，刚好
            resize(this);
//        if empty space is too small, resize it
        adq[back] = item;
        back = (back + 1) % capacity;
//        保证不会越界，首索引和尾索引都不超过capacity
        size++;

    }

    public void addFirst(T item) {
//        循环队列提高效率，头插法从数组倒序开始
        if (size > capacity / 2)
            resize(this);
        front = (front - 1 + capacity) % capacity;
        adq[front] = item;
        size++;
    }

    public T removeFirst() {
        if (isEmpty())
            return null;
        T temp = adq[front];
        front = (front + 1) % capacity;
        size--;
        return temp;
    }

    public T removeLast() {
        if (isEmpty())
            return null;
        T temp = adq[(back - 1 + capacity) % capacity];
        back = (back - 1 + capacity) % capacity;
        size--;
        return temp;
    }

    public T get(int index) {
//        index从1开始就不用+1了
        return adq[(front + index) % capacity];
    }

    private static <T> void resize(ArrayDeque<T> now) {

        var next = new ArrayDeque<T>(now.capacity * 2);

        System.arraycopy(now.adq, now.front, next.adq, 0, now.capacity - now.front);
        System.arraycopy(now.adq, 0, next.adq, now.capacity - now.front, now.back);

        now.front = 0;
        now.back = next.adq.length;
        now.adq = next.adq;
        next = null;
    }

    public boolean isEmpty() {
        return front == back;
//        判断不太好emmm, 但是保证使用率低于25%时可以判断
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        while (front != back) {
            System.out.println(adq[front] + " ");
            front = (front + 1) % capacity;
        }
        System.out.println("");
    }


    @Override
    public Iterator<T> iterator() {
        return null;
    }
}

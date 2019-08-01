package ds;

import ds.protocol.IQueue;

public class LoopQueue<E> implements IQueue<E> {
    private E[] data;
    private int front;
    private int tail;
    private int size;

    public LoopQueue() {
        data = (E[]) new Object[10];
        front = 0;
        tail = 0;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void enqueue(E element) {

    }

    @Override
    public E dequeue() {
        return null;
    }

    @Override
    public E getFront() {
        return null;
    }
}

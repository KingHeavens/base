package ds;

import ds.protocol.IQueue;

public class ArrayQueue<E> implements IQueue<E> {
    private ArrayList<E> mArray;

    public ArrayQueue() {
        mArray = new ArrayList<>();
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

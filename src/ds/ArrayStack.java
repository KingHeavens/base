package ds;

import ds.protocol.IStack;

public class ArrayStack<E> implements IStack<E> {
    private ArrayList<E> mArray;

    public ArrayStack() {
        mArray = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return mArray.isEmpty();
    }

    @Override
    public int getSize() {
        return mArray.getSize();
    }

    @Override
    public E pop() {
        if (getSize() > 0) {
            return mArray.removeLast();
        }
        return null;
    }

    @Override
    public E peek() {
        if (getSize() > 0) {
            return mArray.get(getSize() - 1);
        }
        return null;
    }

    @Override
    public void push(E element) {
        mArray.addLast(element);
    }
}

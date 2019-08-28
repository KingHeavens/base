package ds;

import ds.exception.NoElementException;
import ds.protocol.IStack;

/**
 * 链式栈
 * <p>
 *     链表结构实现的栈
 * </p>
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/8/2
 **/
public class LinkedStack<E> implements IStack<E> {
    private LinkedList<E> mData;

    public LinkedStack() {
        mData = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return mData.isEmpty();
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public int getSize() {
        return mData.getSize();
    }

    /**
     * {@inheritDoc}
     *
     * @return
     * @throws NoElementException 栈中没有元素时
     */
    @Override
    public E pop() {
        if (getSize() <= 0) {
            throw new NoElementException("LinkedStack");
        }
        return mData.remove(0);
    }

    /**
     * {@inheritDoc}
     *
     * @return
     * @throws NoElementException 栈中没有元素时
     */
    @Override
    public E peek() {
        if (getSize() <= 0) {
            throw new NoElementException("LinkedStack");
        }
        return mData.get(0);
    }

    /**
     * {@inheritDoc}
     *
     * @param element 元素
     */
    @Override
    public void push(E element) {
        mData.add(0, element);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("LinkedStack size = %s \n", getSize()));
        builder.append("[ top <--");
        for (int i = 0; i < getSize(); i++) {
            builder.append(mData.get(i));
            if (i < getSize() - 1) builder.append(",");
        }
        builder.append("] <-- bottom");
        return builder.toString();
    }
}

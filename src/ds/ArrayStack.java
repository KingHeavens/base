package ds;

import ds.exception.NoElementException;
import ds.protocol.IStack;

/**
 * 线性栈
 *
 * <p>
 *     基于线性表实现的栈结构
 *
 * </p>
 *
 * @author J.Heavens
 * @since create at 2019/7/28
 * @version v1.0
 * @param <E> 元素
 */
public class ArrayStack<E> implements IStack<E> {
    private ArrayList<E> mArray;

    public ArrayStack() {
        mArray = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return mArray.isEmpty();
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public int getSize() {
        return mArray.getSize();
    }

    /**
     * {@inheritDoc}
     *
     * @return
     * @throws NoElementException 栈内没有元素
     */
    @Override
    public E pop() {
        if (getSize() <= 0) {
            throw new NoElementException("ArrayStack");
        }
        return mArray.removeLast();
    }

    /**
     * {@inheritDoc}
     *
     * @return
     * @throws NoElementException 栈内没有元素
     */
    @Override
    public E peek() {
        if (getSize() <= 0) {
            throw new NoElementException("ArrayStack");
        }
        return mArray.get(getSize() - 1);
    }

    /**
     * {@inheritDoc}
     *
     * @param element 元素
     */
    @Override
    public void push(E element) {
        mArray.addLast(element);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("ArrayStack size = %s  capacity = %s \n", getSize(), mArray.getCapacity()));
        builder.append("[ top <--");
        for (int i = getSize() - 1; i >= 0; i--) {
            builder.append(mArray.get(i));
            if (i > 0) builder.append(",");
        }
        builder.append("] <-- bottom");
        return builder.toString();
    }
}

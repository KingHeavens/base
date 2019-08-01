package ds;

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
     */
    @Override
    public E pop() {
        if (getSize() > 0) {
            return mArray.removeLast();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public E peek() {
        if (getSize() > 0) {
            return mArray.get(getSize() - 1);
        }
        return null;
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
}

package ds;

import ds.protocol.IList;

/**
 * 单链表
 *
 * @author J.Heavens
 * @since create at 2019/08/01
 * @version 1.0
 **/
public class LinkedList<E> implements IList<E> {

    /**
     * 链表节点
     *
     */
    private class Node {
        E element;
        Node next;

        Node(E element) {
            this.element = element;
        }

        Node(E element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public int getSize() {
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void add(int index, E element) {

    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public E remove(int index) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void set(int index, E element) {

    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public E get(int index) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

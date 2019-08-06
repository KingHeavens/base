package ds;

import ds.protocol.IQueue;

/**
 * 循环队列
 * <p>
 *     数组结构实现的队列，包含一个头指针{@code front}和一个尾指针{@code tail}
 *     当{@code front == tail}时，队列为空
 *     当{@code (front + 1) % size == tail}时，队列满
 * </p>
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2019/8/2
 **/
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
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }
}

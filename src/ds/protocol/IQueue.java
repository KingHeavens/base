package ds.protocol;

/**
 * Author: Aaron
 * Create Date: 2019/7/30
 * Version: v1.0
 * <p>
 * 队列结构定义
 */
public interface IQueue<E> {

    /**
     * 判断队列是否为空
     *
     * @return true -> 没有元素
     */
    boolean isEmpty();

    /**
     * 返回队列的大小
     *
     * @return 大小
     */
    int getSize();

    /**
     * 入队列
     * @param element 元素
     */
    void enqueue(E element);

    /**
     * 出队列
     * @return 元素
     */
    E dequeue();

    /**
     * 返回队列头部元素
     * @return 元素
     */
    E getFront();
}

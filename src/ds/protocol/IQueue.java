package ds.protocol;

/**
 * Author: Aaron
 * Create Date: 2019/7/30
 * Version: v1.0
 * <p>
 * 队列结构定义
 * 这里的队列，都是从队列头部入队列，从队列尾部出队列
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
     * 入队列，从队列尾部
     *
     * @param element 元素
     */
    void enqueue(E element);

    /**
     * 出队列从队列头部（删除），如果队列空抛出异常
     *
     * @return 元素
     * @throws ds.exception.NoElementException 如果队列为空
     */
    E dequeue();

    /**
     * 出队列从队列头部（删除），如果队列为空返回null
     *
     * @return null - 队列为空，元素 - 队列不为空
     */
    E poll();

    /**
     * 返回队列头部元素（不删除），如果队列为空抛出异常
     *
     * @return 元素
     * @throws ds.exception.NoElementException 如果队列为空
     */
    E element();

    /**
     * 返回队列头部元素（不删除），如果队列为空返回null
     *
     * @return null - 队列为空，元素 - 队列不为空
     */
    E peek();
}

package ds.protocol;

/**
 * Author: Aaron
 * Create Date: 2019/7/30
 * Version: v1.0
 * <p>
 *  栈结构定义
 */
public interface IStack<E> {

    /**
     * 判断栈是否为空
     *
     * @return true 没有元素
     */
    boolean isEmpty();

    /**
     * 返回栈的大小
     *
     * @return 大小
     */
    int getSize();

    /**
     * 移除并返回栈顶元素
     *
     * @return 元素
     */
    E pop();

    /**
     *  获取栈顶元素
     *
     * @return 元素
     */
    E peek();

    /**
     * 元素入栈
     *
     * @param element 元素
     */
    void push(E element);
}

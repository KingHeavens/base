package ds;

import ds.exception.IndexOutOfBoundsException;
import ds.protocol.IList;

/**
 * 线性表
 * * <p>
 * 优点：随机访问索引位置快，时间复杂度是 O(1)
 * 缺点：删除和添加相对慢，时间复杂 O(n)
 *      需要连续的存储空间，不能时刻保证空间最大利用，可以通过动态扩容和缩容减少空间浪费
 *
 * 添加：
 *      1、从表头添加    O(n)
 *      2、从表尾添加    O(1) 扩容均摊
 *      3、随机添加      O(n) 平均
 * 删除：
 *      1、从表头删除 O(n)
 *      2、从表尾删除 O(1) 缩容均摊
 *      3、随机删除 O(n) 平均
 * 修改：
 *      索引位置修改 O(1)
 * 查询：
 *      遍历查询 O(n)
 * </p>
 *
 * @author J.Heavens
 * @since create at 2019/7/28
 * @version v1.0
 */

public class ArrayList<E> implements IList<E> {
    private E[] data;
    private int size;

    /**
     * 根据容量构造线性表
     *
     * @param capacity 容量
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 构造线性表，初始值为10
     */
    public ArrayList() {
        this(10);
    }

    /**
     * 判断线性表是否为空
     *
     * @return 是否为空
     */
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * 获取线性表大小
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 获取线性表的容量
     *
     * @return 容量
     */
    public int getCapacity() {
        return data != null ? data.length : 0;
    }

    /**
     * 获取索引位置的元素
     *
     * @param index 索引
     * @return 元素
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    /**
     * 在线性表index位置添加一个元素
     *
     * @param index   添加元素的索引位置
     * @param element 元素
     */
    @Override
    public void add(int index, E element) {
        checkCapacity();
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(getCapacity(), index);
        }
        //把index往后的元素向后移动一位
        //方法一
        if (size - index >= 0) {
            System.arraycopy(data, index, data, index + 1, size - index);
        }
        //方法二
//        for (int i = size - 1; i >= index; i--) {
//            data[i + 1] = data[i];
//        }
        data[index] = element;
        size++;
    }

    /**
     * 在线性表头部添加一个元素
     *
     * @param element 元素
     */
    public void addFirst(E element) {
        add(0, element);
    }

    /**
     * 在线性表尾部添加一个元素
     *
     * @param element 元素
     */
    public void addLast(E element) {
        add(size, element);
    }

    /**
     * 删除索引位置元素并且返回删除元素
     *
     * @param index 索引
     * @return 删除元素
     */
    @Override
    public E remove(int index) {
        checkIndex(index);
        size--;
        E element = data[index];
        for (int i = index; i < size; i++) {
            data[i] = data[i + 1];
        }
        return element;
    }

    /**
     * 删除线性表第一个元素
     *
     * @return 删除的元素
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除线性表最后一个元素
     *
     * @return 删除的元素
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 从线性表起始位置查找删除第一个元素
     *
     * @param element 元素
     */
    public void removeElementFromStart(E element) {
        int index = findFromFirst(element);
        if (index != -1) {
            remove(index);
        }
    }

    /**
     * 从线性表结尾位置往前查找删除第一个元素
     *
     * @param element 元素
     * @return 删除的元素
     */
    public void removeElementFromLast(E element) {
        int index = findFromLast(element);
        if (index != -1) {
            remove(index);
        }
    }

    /**
     * 删除所有的元素
     *
     * @param element 元素
     */
    public void removeAllElement(E element) {
        int index;
        while ((index = findFromFirst(element)) != -1) {
            remove(index);
        }
    }

    /**
     * 清除线性表数据
     */
    public void clear() {
        while (size > 0) {
            remove(size - 1);
        }
    }

    /**
     * 修改元素
     *
     * @param index   索引
     * @param element 元素
     */
    @Override
    public void set(int index, E element) {
        checkIndex(index);
        data[index] = element;
    }

    /**
     * 判断是否包含此元素
     *
     * @param element 元素
     */
    @Override
    public boolean contains(E element) {
        return findFromFirst(element) != -1;
    }

    /**
     * 查找元素位置,从表头开始
     *
     * @param element 元素
     * @return 元素索引
     */
    public int findFromFirst(E element) {
        for (int index = 0; index < size; index++) {
            if (element == data[index]) {
                return index;
            }
        }
        return -1;
    }

    /**
     * 查找元素位置，从表尾开始
     *
     * @param element 元素
     * @return 元素索引
     */
    public int findFromLast(E element) {
        for (int index = size - 1; index >= 0; index--) {
            if (element == data[index]) {
                return index;
            }
        }
        return -1;
    }

    /**
     * 改变线性表容量
     *
     * @param capacity 容量
     */
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        if (capacity <= 0) {
            return;
        }
        E[] newData = (E[]) new Object[capacity];
        for (int index = 0; index < size; index++) {
            newData[index] = data[index];
        }
        data = newData;
    }

    /**
     * 检查索引是否越界
     *
     * @param index 索引
     */
    private void checkIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException(size, index);
        }
    }

    /**
     * 检查当前线性表的容量
     *
     */
    private void checkCapacity() {
        if (size == data.length) {
            //throw new OutOfCapacityException(data.length);
            resize(size * 2);
        } else if (data.length / 4 > 0 && size == data.length / 4) {
            resize(data.length / 2);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("ArrayList size = %s  capacity = %s \n", size, data.length));
        builder.append("[");
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i < size - 1) builder.append(",");
        }
        builder.append("]");
        return builder.toString();
    }
}

package ds.protocol;

/**
 * Author: Aaron
 * Create Date: 2019/7/31
 * Version: v1.0
 * <p>
 * 表结构定义，支持增删改查
 *
 */
public interface IList<E> {

    /**
     * 判断表是否为空
     *
     * @return true -> 空, false -> 非空
     */
    boolean isEmpty();

    /**
     * 获取表长度
     *
     * @return 长度
     */
    int getSize();

    /**
     * 在索引处添加一个元素
     *
     * @param index 索引
     * @param element 元素
     */
    void add(int index, E element);

    /**
     * 在索引处移除一个元素
     *
     * @param index 索引
     * @return 删除的元素
     */
    E remove(int index);

    /**
     * 修改表中的元素
     *
     * @param index 索引
     * @param element 元素
     */
    void set(int index, E element);

    /**
     * 获取索引处元素
     *
     * @param index 索引
     * @return 元素
     */
    E get(int index);

    /**
     * 查询表，判断是否包含元素
     *
     * @param element 元素
     * @return 是否包含 元素
     */
    boolean contains(E element);
}

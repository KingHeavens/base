package algorithm.linkedlist.node;

/**
 * 单链表带随机节点
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2020/11/10
 **/
@SuppressWarnings({"WeakerAccess", "unused"})
public class RNode {
    public RNode() {
    }
    public RNode(int value) {
        this.value = value;
    }
    public int value;
    public RNode next;
    public RNode rand;
}

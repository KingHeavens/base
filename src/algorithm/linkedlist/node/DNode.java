package algorithm.linkedlist.node;

/**
 * 双向链表节点
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2020/11/10
 **/
@SuppressWarnings({"WeakerAccess", "unused"})
public class DNode {
    public DNode() {
    }
    public DNode(int value) {
        this.value = value;
    }
    public int value;
    public DNode pre;
    public DNode next;
}

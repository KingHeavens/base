package algorithm.linkedlist.node;

/**
 * 单链表节点
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2020/11/10
 **/
@SuppressWarnings({"WeakerAccess", "unused"})
public class Node {
    public Node() {
    }
    public Node(int value) {
        this.value = value;
    }
    public int value;
    public Node next;
}

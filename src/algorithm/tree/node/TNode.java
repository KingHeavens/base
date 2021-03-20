package algorithm.tree.node;

/**
 * 二叉树
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2020/11/14
 **/
public class TNode implements Comparable<TNode> {
    //父节点
    public TNode parent;
    //左-子节点
    public TNode left;
    //右-子节点
    public TNode right;
    //数组节点(方便打印)
    public int num;
    //节点颜色，用以区分红黑树节点
    public Colour colour = Colour.BLACK;


    //值
    public String value;

    public TNode(int value) {
        this.value = String.valueOf(value);
    }

    public TNode(int len, char fill) {
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = fill;
        }
        value = String.valueOf(chars);
    }

    @Override
    public int compareTo(TNode o) {
        return Integer.valueOf(value) - Integer.valueOf(o.value);
    }
}

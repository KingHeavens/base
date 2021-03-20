package algorithm.tree;

import algorithm.tree.node.TNode;

/**
 * 获取二叉树的最大距离
 *
 * 距离表示 一个节点 到另外一个节点相差的节点数，节点往上往下都有距离
 *
 * 分析：
 * 1、最大距离的路径中不包含当前节点的情况
 *     maxDistance = max(leftMaxDistance, rightMaxDistance)
 * 2、最大距离的路径中包含当前节点的情况
 *      maxDistance = maxLeftDepth + maxRightDepth + 1;
 *
 * 结合两种情况返回最大距离：
 *      maxDistance = max(情况一， 情况二)
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2021/3/20
 **/
public class MaxTreeDistance {
    public static void main(String[] args) {

    }

    static class Info{
        Info() { }
        Info(int distance, int depth) {
            this.maxDistance = distance;
            this.height = depth;
        }
        int maxDistance;
        int height;
    }

    private Info getMaxTreeDistane(TNode node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info info = new Info();
        Info leftInfo = getMaxTreeDistane(node.left);
        Info rightInfo = getMaxTreeDistane(node.right);

        int distance1 = Math.max(leftInfo.maxDistance, rightInfo.maxDistance);
        int distance2 = leftInfo.height + rightInfo.height + 1;

        info.maxDistance = Math.max(distance1, distance2);
        info.height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return info;
    }
}

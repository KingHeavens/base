package algorithm.tree.utils;

import algorithm.tree.node.Colour;
import algorithm.tree.node.TNode;

/**
 * Author:liuyu
 * Date:2019-02-21
 */
public class TreeUtils {

    //打印树的时候依据每行来打印，每行初始2048个字符
    private static char[] line = new char[2048];

    //判断二叉树是否为平衡二叉树的参数，min为二叉树最低高度，max为最高
    private static int min;
    private static int max;

    static {
        for (int i = 0; i < line.length; i++) {
            line[i] = ' ';
        }
    }

    //将结构化的数组，转变为二叉树
    public static TNode transfer(Integer[] arr) {
        if (arr[0] == null) {
            throw new RuntimeException("first element can't be null!");
        }
        TNode[] nodes = new TNode[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                continue;
            }
            nodes[i] = new TNode(arr[i]);
            if (i != 0) {
                int parent = i - 1 >>> 1;
                if (nodes[parent] == null) {
                    throw new RuntimeException("can't transfer!");
                }
                nodes[i].parent = nodes[parent];
                if (i % 2 == 0) {
                    nodes[parent].right = nodes[i];
                } else {
                    nodes[parent].left = nodes[i];
                }
            }
        }
        return nodes[0];
    }

    public static void printTree(TNode root) {
        printTree(root, false);
    }

    //打印二叉树
    public static void printTree(TNode root, boolean hasNum) {
        if (root == null) {
            System.out.println("tree is null");
        }

        //如果没有节点值，添加
        if (!hasNum) {
            add_num(root);
        }
        System.out.println("----------------------------------------------------------------------------------");

        //探测树转变为数组后需要的长度
        int length = length(root, 0);

        //根据长度计算树的高度
        int height = 0;
        while ((length + 1 >>> height + 1) > 0) {
            height++;
        }
        TNode[] nodes = new TNode[(1 << height + 1) - 1];

        //遍历，将树放入数组,并返回树中最长的节点，方便计算格式
        int len = insert_tree(root, nodes) + 1;

        //判断底层长度是否超出范围
        if ((1 << height + 1) * len > line.length) {
            throw new RuntimeException("the height of tree is too large!");
        }

        //为了观看方便，将空节点塞满 '*'
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) {
                nodes[i] = new TNode(len, '*');
            }
        }

        //根据数组逐层打印树结构
        int point = 0;
        for (int i = 0; i <= height; i++) {
            char[] value = line.clone();

            //计算每行第一个元素的缩进距离
            int cursor = len * ((1 << height - i) - 1);
            char[] node = nodes[point].value.toCharArray();
            for (char aNode : node) {
                value[cursor++] = aNode;
            }
            if (nodes[point].colour == Colour.RED) {
                value[cursor] = 'R';
            }
            point++;

            //遍历元素，将每层剩下的元素加入行中
            while (point < (1 << i + 1) - 1 && point < nodes.length) {
                char[] no = nodes[point].value.toCharArray();
                cursor = cursor + ((1 << height - i + 1) - 1) * len + len - nodes[point - 1].value.length();
                for (char aNo : no) {
                    value[cursor++] = aNo;
                }
                if (nodes[point].colour == Colour.RED) {
                    value[cursor] = 'R';
                }
                point++;
            }
            System.out.println(String.valueOf(value));
            if (point >= nodes.length) {
                break;
            }
        }
        System.out.println("----------------------------------------------------------------------------------");
    }

    //判断二叉树是否为平衡二叉树
    public static boolean isbalance(TNode root) {
        min = Integer.MAX_VALUE;
        max = 0;
        return isbalance(root, 0);
    }

    private static boolean isbalance(TNode root, int height) {
        if (root == null) {
            if (height < min) {
                min = height;
            }
            if (height > max) {
                max = height;
            }
            return !(max - min > 1);
        }
        height++;
        return isbalance(root.left, height) && isbalance(root.right, height);
    }

    //查找树结构需要多大的数组
    private static int length(TNode root, int length) {
        if (root == null) {
            return length;
        }
        int left = length(root.left, root.num);
        int right = length(root.right, root.num);
        return left > right ? left : right;
    }

    //将树结构塞入数组
    private static int insert_tree(TNode root, TNode[] arr) {
        if (root == null) {
            return 0;
        }
        arr[root.num] = root;
        int left = insert_tree(root.left, arr);
        int right = insert_tree(root.right, arr);
        return Math.max(root.value.length(), Math.max(left, right));
    }

    //为树中的每个元素添加数组节点
    private static void add_num(TNode root) {
        if (root == null) {
            return;
        }
        if (root.parent == null) {
            root.num = 0;
        } else if (root == root.parent.left) {
            root.num = ((root.parent.num + 1) << 1) - 1;
        } else if (root == root.parent.right) {
            root.num = ((root.parent.num + 1) << 1);
        }
        add_num(root.left);
        add_num(root.right);
    }
}

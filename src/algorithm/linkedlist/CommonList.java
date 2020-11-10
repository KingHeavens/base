package algorithm.linkedlist;

import algorithm.linkedlist.helper.LinkedTestHelper;
import algorithm.linkedlist.node.Node;
import test.Printer;

/**
 * 打印两个有序链表的公共节点
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2020/11/10
 **/
@SuppressWarnings("WeakerAccess")
public class CommonList {
    public void printCommonList(Node head1, Node head2) {
        Printer.println("CommonList:");
        Node n1 = head1;
        Node n2 = head2;
        while (n1 != null && n2 != null) {
            if (n1.value < n2.value) {
                n1 = n1.next;
            } else if (n1.value > n2.value) {
                n2 = n2.next;
            } else {
                Printer.print(n1.value + ",");
                n1 = n1.next;
                n2 = n2.next;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr1 = {
            1,3,5,7,9,10,20,21,22,23
        };
        Integer[] arr2 = {
            2,4,6,7,8,9,10,21,22,25
        };
        Node head1 = LinkedTestHelper.arrayToLinkedList(arr1);
        Node head2 = LinkedTestHelper.arrayToLinkedList(arr2);

        CommonList commonList = new CommonList();
        commonList.printCommonList(head1, head2);
    }
}

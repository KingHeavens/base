package algorithm.tree;

import java.util.List;

/**
 * 员工的最的快乐值
 *
 * 员工有自己的下属，每个人有自己的快乐值，现在举办一场聚会，已知领导参加聚会，下属就不会参加。
 * 求所有员工的最大快乐值。
 *
 * 分析：
 * 1、当前员工参加的最大快乐值
 *   快乐值 = 当前员工快乐值 + 当前员工下属不参加情况下所有快乐值总和
 *
 * 2、当前员工不参加的最大快乐值
 *   快乐值 = max(下属员工参加情况下快乐值，下属员工不参加情况下快乐值) 累加
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2021/3/20
 **/
public class EmployeeMaxHappy {
    public static void main(String[] args) {

    }

    private int getMaxHappy(Employee head) {
        Info result = proccess(head);
        return Math.max(result.joinHappy, result.unJoinHappy);
    }

    private Info proccess(Employee head) {
        if (head == null) {
            return new Info();
        }

        //当员工是最底层员工时
        if (head.employees.isEmpty()) {
            Info info = new Info();
            info.joinHappy = head.happy;
            info.unJoinHappy = 0;
            return info;
        }

        Info result = new Info();
        result.joinHappy += head.happy;
        result.unJoinHappy = 0;
        for (Employee employee : head.employees) {
            Info info = proccess(employee);
            result.joinHappy += info.unJoinHappy;
            result.unJoinHappy += Math.max(info.joinHappy, info.unJoinHappy);
        }
        return result;
    }

    static class Info {
        int joinHappy;
        int unJoinHappy;
    }
}

class Employee {
    int happy;
    List<Employee> employees;
}
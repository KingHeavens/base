package leetcode;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 有效字符串需满足：
 左括号必须用相同类型的右括号闭合。
 左括号必须以正确的顺序闭合。
 注意空字符串可被认为是有效字符串。

 示例 1:

 输入: "()"
 输出: true
 示例 2:

 输入: "()[]{}"
 输出: true
 示例 3:

 输入: "(]"
 输出: false
 示例 4:

 输入: "([)]"
 输出: false
 示例 5:

 输入: "{[]}"
 输出: true


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/valid-parentheses
 */
public class Topic20 {
    public static void main(String[] args) {
        String test1 = "()";
        String test2 = "()[]{}";
        String test3 = "(]";
        String test4 = "([)]";
        String test5 = "{[]}";
        String test6 = "]]]][][][][]";

        test(test1, true);
        test(test2, true);
        test(test3, false);
        test(test4, false);
        test(test5, true);
        test(test6, false);
    }

    public static boolean test(String input, boolean expect) {
        Topic20 topic20 = new Topic20();
        boolean valid = topic20.isValid(input);

        boolean result = valid == expect;
        showLog("input: " + input);
        if (result) {
            showLog("nice!");
        } else {
            showLog("not passed!");
        }
        return result;
    }

    public static void showLog(String log) {
        System.out.println(log);
    }

    public boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i ++) {
            char c = str.charAt(i);
            if ('(' == c || '[' == c || '{' == c) {
                stack.push(c);
            } else {
                if (')' == c) {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                } else if (']' == c) {
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                } else if ('}' == c) {
                    if (stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}

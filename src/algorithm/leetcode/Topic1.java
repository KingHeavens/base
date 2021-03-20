package algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 *
 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。

 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

 你可以按任意顺序返回答案。

  

 示例 1：

 输入：nums = [2,7,11,15], target = 9
 输出：[0,1]
 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 示例 2：

 输入：nums = [3,2,4], target = 6
 输出：[1,2]
 示例 3：

 输入：nums = [3,3], target = 6
 输出：[0,1]
  

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/two-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * @author J.Heavens
 * @version 1.0
 * @since create at 2021/3/20
 **/
public class Topic1 {
    public static void main(String[] args) {

    }

    private int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length ; i ++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {
                   i, map.get(target - nums[i])
                };
            }
            map.put(nums[i], i);
        }
        return null;
    }
}

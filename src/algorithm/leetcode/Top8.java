package algorithm.leetcode;

import java.util.Arrays;
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
public class Top8 {
    public static void main(String[] args) {
//        int[] nums = new int[] { 2,7,11,15 };
//        int target = 9;

        int[] nums = new int[] { 3,2,4 };
        int target = 6;

//        int[] nums = new int[] { 3, 3 };
//        int target = 6;

        int[] result = twoSum(nums, target);
        System.out.println("result:" + Arrays.toString(result));
    }

    /**
     * 	 1、遍历数组将遍历到的值和inde 作为key和value放入哈希表中
     * 	 2、 每遍历到一个位置时，用target - 当前数值，查看是不是已在哈希表中
     * 	 3、如果在就找到了这两个索引值，可以返回结果
     * 	 4、如果不在将此遍历的值加入哈希表中
     * 	 *
     * 	 看清题意索引从几开始
     *
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoSum(int[] nums, int target) {
        int[] res = new int[0];
        if (nums == null || nums.length <= 1) {
            return res;
        }
        HashMap<Integer, Integer> maps = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int key = target - nums[i];
            if (maps.containsKey(key)) {
                return new int[]{maps.get(key), i};
            } else {
                maps.put(nums[i], i);
            }
        }
        return res;
    }
}

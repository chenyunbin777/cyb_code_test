package main.java.leetcode;

import com.alibaba.fastjson.JSON;

/**
 * @author cyb
 * @date 2020/12/04 2:13 下午
 */
public class 加油站_134 {

    public static void main(String[] args) {
        Solution solution = new 加油站_134().new Solution();
//        int[] gas = {1, 2, 3, 4, 5};
//        int[] cost = {3, 4, 5, 1, 2};

        int[] gas = {3, 1,1};
        int[] cost = {1, 2, 2};

//        boolean desc = false;
//        int[] arraysort = solution.arraySort(cost, desc);
//        System.out.println("arraysort:" + JSON.toJSONString(arraysort));
//        System.out.println("gas:"+JSON.toJSONString(gas));

        int result = solution.canCompleteCircuit(gas, cost);
        System.out.println("result:" + result);
    }

    /**
     * 贪心算法
     */
    class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {

            int result = 0;
            int[] gasCopy = gas.clone();
            //从油最多的地方开始走
            //特殊情况，如果有两个同样大的gas[i] gas[j]  我们需要都考虑进来，因为是否成功取决于 gas[i] gas[j]的下一端路程的cost，如果足够则以，不够则不成功。
            //所以我们要把gas排序并且保存其对应的下标
            int[] indexs = arraySort(gas, false);
            //找出gas中最大的值开始跑
            for (int i = 0; i < indexs.length; i++) {
                int gasIndex = indexs[i];
                int remainGas = 0;//此时所剩余的油

                int nowIndex = gasIndex;
                int beginIndex = gasIndex;
                int flag = 1;
                do {

                    //从头再开始
                    if (nowIndex >= gas.length) {
                        nowIndex = 0;
                        //如果走完了一轮了，发现是起点，那么就直接结束
                        if(nowIndex == beginIndex){
                            break;
                        }
                    }
                    int costGas = cost[nowIndex];
                    //先加油
                    remainGas = remainGas + gasCopy[nowIndex];

                    // 在扣油
                    remainGas -= costGas;
                    //油不够了 直接结束
                    if (remainGas < 0) {
                        flag = 0;
                        break;
                    }
                    nowIndex++;

                } while (nowIndex != beginIndex);

                if (flag == 1) {
                    return gasIndex;
                }

            }


            return -1;
        }

        /**
         * 排序并返回对应原始数组的下标
         *
         * @param arr
         * @param order: true 正序 false逆序
         * @return
         */
        public int[] arraySort(int[] arr, boolean order) {
            int temp;
            int index;
            int k = arr.length;
            int[] Index = new int[k];
            for (int i = 0; i < k; i++) {
                Index[i] = i;
            }

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length - i - 1; j++) {
                    if (order) {
                        if (arr[j] > arr[j + 1]) {
                            temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;

                            index = Index[j];
                            Index[j] = Index[j + 1];
                            Index[j + 1] = index;
                        }
                    } else {
                        if (arr[j] < arr[j + 1]) {
                            temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;

                            index = Index[j];
                            Index[j] = Index[j + 1];
                            Index[j + 1] = index;
                        }

                    }
                }
            }
            return Index;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}

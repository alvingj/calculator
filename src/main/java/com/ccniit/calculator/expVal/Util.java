/**
 *
 */
package com.ccniit.calculator.expVal;

/**
 * @author 龚军
 */
public class Util {

    /**
     * @param args
     */
    public static void main(String[] args) {

        System.out.println(Util.isSign("-(7-8)", 3));

        System.out.println(isNumber(".1983"));

    }

    /**
     * 测试 正负号 加号
     *
     * @param 要测试字符串 字符位子(字符位子按数组下标计算)
     * @return 返回ture 为正号 false为加号
     */
    public static boolean isSign(String expStr, int index) {
        if (expStr.charAt(index) == '+' || expStr.charAt(index) == '-') {
            if (index > 0) {
                char ch = expStr.charAt(index - 1);

                boolean isNum = ch >= '0' && ch <= '9';
                boolean isRightBranket = ch == ')';

                if (isNum || isRightBranket) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }

    }


    /**
     * 判断是否是数字
     *
     * @param 要测试字符串 (注意前面加.和输入为空)
     * @return 返回ture 为数字 false为不正确的表达式
     */
    public static boolean isNumber(String str) {

        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            // TODO 自动生成 catch 块
            return false;
        }
    }
}

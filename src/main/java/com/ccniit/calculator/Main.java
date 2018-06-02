package com.ccniit.calculator;

import com.ccniit.calculator.expVal.ExpBracket;

import javax.swing.*;

import static com.ccniit.calculator.expVal.ExpBracket.isValide;

/**
 * @Auther: gjuse
 * @Date: 2018/6/2 22:37
 * @Description:
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String str = "";
        //
        String str1 = "";
        String postStr1 = "";
        //
        str = JOptionPane.showInputDialog("请输入表达式: ");
        str1 = str;
        if (str == null) {
            return;
        }
        if (isValide(str)) {

            str = ExpBracket.isfunction(str);
            str = ExpBracket.reoloce(str);

            // ----------------------------------------------------------------------------------------------------------------------
            String postStr = ExpBracket.convertFromMiddleToPost(str);
            // ----------------------------------------------------------------------------------------------------------------------
            postStr1 = ExpBracket.isjj(postStr);

            double result = ExpBracket.expVal(postStr);

            JOptionPane.showMessageDialog(null, "\n中缀表达式为:  " + str1
                    + "\n后缀表达式为:  " + postStr1 + "\n计算结果:  " + result);

        } else {
            JOptionPane.showMessageDialog(null, "不匹配！");
        }
    }
}

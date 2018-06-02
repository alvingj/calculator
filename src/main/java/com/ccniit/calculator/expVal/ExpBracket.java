package com.ccniit.calculator.expVal;


import com.ccniit.calculator.expVal.operator.OperatorControl;
import com.ccniit.calculator.expValTool.StatcImpOfArray;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author 龚军
 */
public class ExpBracket {
    public static Stack stack = new Stack();

    public static StringBuffer sb = new StringBuffer();// 输入结果

    public static OperatorControl op = null;

    // 处理函数用
    static StringBuffer sb1 = new StringBuffer("");

    static StringBuffer sb2 = new StringBuffer("");

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

            str = isfunction(str);
            str = reoloce(str);

            // ----------------------------------------------------------------------------------------------------------------------
            String postStr = convertFromMiddleToPost(str);
            // ----------------------------------------------------------------------------------------------------------------------
            postStr1 = isjj(postStr);

            double result = expVal(postStr);

            JOptionPane.showMessageDialog(null, "\n中缀表达式为:  " + str1
                    + "\n后缀表达式为:  " + postStr1 + "\n计算结果:  " + result);

        } else {
            JOptionPane.showMessageDialog(null, "不匹配！");
        }
    }

    /**
     * 验证表示式是否合法
     *
     * @param exp 要验证的表达式字符串
     * @return 表达式合法返回true 不合法返回false
     */
    public static boolean isValide(String exp) {
        StatcImpOfArray stack = new StatcImpOfArray(exp.length());
        for (int i = 0; i < exp.length(); i++) {
            // 将exp字符串一个一个附给ch
            char ch = exp.charAt(i);

            switch (ch) {
                case '(':
                    // 转换为对象 chObj 再将 ( 入栈
                    Character chObj = new Character(ch);
                    stack.push(chObj);
                    break;
                case ')':
                    Object obj = stack.pop();
                    if (obj == null) {
                        return false;
                    }
                    break;
            }
        }
        if (stack.isEmpty()) {
            return true;

        } else {
            return false;

        }
    }

    /**
     * 正负号处理
     *
     * @param
     * @return 处理后的表达式
     */
    public static String reoloce(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Util.isSign(str, i)) {
                char ch = str.charAt(i);
                if (ch == '+') {

                    str = str.substring(0, i) + "p" + str.substring(i + 1);
                } else if (ch == '-') {
                    str = str.substring(0, i) + "n" + str.substring(i + 1);

                }
            }
        }

        return str;
    }

    /**
     * 变回加减号
     *
     * @param
     * @return 正确的后缀表达式
     */
    public static String isjj(String postStr) {
        for (int i = 0; i < postStr.length(); i++) {
            char ch = postStr.charAt(i);
            if (ch == 'p') {
                postStr = postStr.substring(0, i) + "+"
                        + postStr.substring(i + 1);
            } else if (ch == 'n') {
                postStr = postStr.substring(0, i) + "-"
                        + postStr.substring(i + 1);
            }
        }

        return postStr;
    }

    /**
     * 中缀表达式到后缀表达式
     *
     * @param ：合法的中缀表达式
     * @return 此表达式的后缀字符串
     * @throws IOException
     */
    public static String convertFromMiddleToPost(String exp)
            throws IOException {

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if ((ch >= '0' && ch <= '9') || ch == '.') {
                do// 对多位数操作 加分隔符 是数字的话 始终会执行一次 所以用do...
                {

                    sb.append(ch);
                    ch = (i < exp.length() - 1) ? exp.charAt(++i) : ' ';
                } while ((ch >= '0' && ch <= '9') || ch == '.');

                sb.append(" ");
                if (ch != ' ') {
                    i--;// 跳过+号 不对其处理 所以-
                }

            } else if (ch == '(') {
                stack.push(ch + "");
            } else if (ch == ')') {
                String str = (String) stack.pop();
                while (!str.equals("(")) {

                    sb.append(str + " ");
                    str = (String) stack.pop();// 防止死循环
                }

            } else {
                // if (Util.isSign(exp, i))
                // {
                // sb.append(ch);
                //
                // } else
                {
                    produceOperator(ch + "");
                }

            }
        }
        while (!stack.isEmpty()) {
            String data = (String) stack.pop();
            sb.append(data + " ");
        }
        return sb.toString();

    }

    /**
     * 在中缀转后缀的表达式中对操作符进行处理
     *
     * @param
     * @throws IOException
     */
    public static void produceOperator(String str) throws IOException {

        if (op == null) {
            op = new OperatorControl();// 创建全局变量 有异常无法抛出时 这样创建
        }
        while (true) {
            if (stack.isEmpty() || ((String) stack.peek()).equals("(")) {
                stack.push(str);
                break;
            } else {
                String topData = (String) stack.peek();
                String topPri = op.getPri(topData);

                String strPri = op.getPri(str);
                if (strPri.compareTo(topPri) > 0) {
                    stack.push(str);
                    break;
                } else {
                    sb.append((String) stack.pop() + " ");// 出栈后继续判定或入栈
                    // 所以不break;

                }
            }
        }
    }

    /**
     * 计算后缀表达式
     *
     * @param postStr 转换后的后缀表达式
     * @return 表达式计算结果
     * @throws IOException
     */
    public static double expVal(String postStr) throws IOException {

        stack.clear();
        double result = 0.0;
        StringTokenizer st = new StringTokenizer(postStr, " ");

        while (st.hasMoreElements()) {
            String str = st.nextToken();
            if (Util.isNumber(str))// 如果是数字 入栈
            {
                stack.push(str);
            } else {

                if (op == null) {
                    op = new OperatorControl();// 创建全局变量 有异常无法抛出时 这样创建
                }
                String numOfOperand = op.getOperandNum(str);

                if (numOfOperand.equals("1")) {
                    double operand1 = Double.parseDouble((String) stack.pop());
                    result = val(operand1, operand1, str);
                } else if (numOfOperand.equals("2")) {
                    double operand2 = Double.parseDouble((String) stack.pop());
                    double operand1 = Double.parseDouble((String) stack.pop());
                    result = val(operand1, operand2, str);
                }
                stack.push(result + "");
            }
        }
        if (stack.isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble((String) stack.pop());

    }

    public static double val(double operand1, double operand2, String str) {
        double re = 0.0;

        if (str.equals("+")) {
            re = operand1 + operand2;
        } else if (str.equals("-")) {
            re = operand1 - operand2;
        } else if (str.equals("*")) {
            re = operand1 * operand2;
        } else if (str.equals("/")) {
            re = operand1 / operand2;
        } else if (str.equals("%")) {
            re = operand1 % operand2;
        } else if (str.equals("&")) {
            re = (char) operand1 & (char) operand2;
        } else if (str.equals("^")) {
            re = Math.pow(operand1, operand2);
        } else if (str.equals("|")) {
            re = (char) operand1 | (char) operand2;
        } else if (str.equals("~")) {
            re = ~(char) operand1;
        } else if (str.equals("n")) {
            re = -operand1;
        } else if (str.equals("p")) {
            re = operand1;
        }
        return re;
    }

    /**
     * 函数处理
     *
     * @param
     * @return 函数计算完成后的表达式
     */
    public static String isfunction(String strhs) {
        HashMap map = new HashMap();// 生成一个空的映射表
        String stred = "";
        String op = "";
        String sz = "";
        String strsz = "";
        boolean bn = false;
        for (int i = 0; i < strhs.length(); i++) {
            char ch = strhs.charAt(i);
            if (ch >= 'a' && ch <= (char) 'a' + 26) {
                sb1.append(ch);
                op = sb1.toString();
            }

            if (i > 0 && strhs.charAt(i - 1) >= 'a'
                    && strhs.charAt(i - 1) <= (char) 'a' + 26 && ch == '(') {
                bn = true;
            }
            if (bn) {
                sb1.append(ch);
                sb2.append(ch);
            }
            if (ch == ')') {
                bn = false;
            }/*
             * else{ return strhs; }
             */

        }

        sz = sb1.toString();
        if (sz.equals("")) {
            return stred = strhs;
        }
        strsz = sb2.toString().substring(1, sb2.toString().length() - 1);

        String[] temp = strsz.split(",");

        try {
            Class cls = Class.forName("java.lang.Math");
            Method[] methods = cls.getMethods();
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                String returnType = method.getReturnType().getSimpleName();

                if (returnType.equals("double")) {
                    map.put(method.getName(), method.getParameterTypes());
                }

            }

            Class partypes[] = (Class[]) map.get(op);

            Method meth
                    = cls.getMethod(op, partypes);
            Object[] arjlist = new Object[partypes.length];
            for (int i = 0; i < arjlist.length; i++) {
                arjlist[i] = new Double(temp[i]);
            }
            Object retobj = meth.invoke(null, arjlist);
            Double retval = (Double) retobj;

            stred = strhs.replace(sb1.toString(), retval + "");

        } catch (Exception e) {

            e.printStackTrace();
        }

        return stred;

    }
}

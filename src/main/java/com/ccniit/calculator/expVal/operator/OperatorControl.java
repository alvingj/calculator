/**
 *
 */
package com.ccniit.calculator.expVal.operator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;


/**
 * @author 龚军
 */
public class OperatorControl {// 对数据进行操作

    /**
     * @param args
     */
    private HashMap map = new HashMap();

    public static void main(String[] args) throws IOException {
        OperatorControl operatorControl = new OperatorControl();
        System.out.println("操作符的优先级:" + operatorControl.getPri("+"));
        System.out.println("操作数:" + operatorControl.getOperandNum("+"));

    }

    // 把oppri.txt操作符定义全部导入 注意那个循环
    public OperatorControl() throws IOException {
        // 查找具有给定名称的资源。---getResourceAsStream
        InputStream in = OperatorData.class.getResourceAsStream("/oppri.txt");
        Properties properties = new Properties();// 构造成属性对象
        properties.load(in);

        Enumeration eu = properties.keys();// keys()返回此哈希表中的键的枚举。
        while (eu.hasMoreElements()) {// 测试此枚举是否包含更多的元素。
            String name = (String) eu.nextElement();
            String value = properties.getProperty(name);
            int index = value.indexOf(",");
            String pri = value.substring(0, index);
            String operandNum = value.substring(index + 1);

            OperatorData operatorDedine = new OperatorData(name, pri,
                    operandNum);

            map.put(name, operatorDedine);// 封装到map
        }
    }

    /**
     * 获得指定操作符的优先级
     *
     * @param name 操作符名字
     * @return 返回 操作符的优先级。
     */
    public String getPri(String name) {
        OperatorData operatorDedine = (OperatorData) map.get(name);

        return operatorDedine.getPri();
    }

    /**
     * 获得指定操作数
     *
     * @param name 操作符名字
     * @return 返回 操作数
     */
    public String getOperandNum(String name) {
        OperatorData operatorDedine = (OperatorData) map.get(name);
        return operatorDedine.getOperandNum();
    }

    /**
     * 判定是否为运算符
     *
     * @param op 判断的字符
     * @return 返回 true是运算符 false不是
     */
    public boolean isOperator(String op) {

        return map.containsKey(op);

    }
}

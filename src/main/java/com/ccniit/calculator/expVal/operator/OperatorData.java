/**
 *
 */
package com.ccniit.calculator.expVal.operator;

/**
 * @author 龚军
 */
public class OperatorData {// 封装数据
    private String name;

    private String pri;

    private String operandNum;

    // -------------------------------------------------------------------------

    public OperatorData(String name, String pri, String operandNum) {
        this.name = name;
        this.pri = pri;
        this.operandNum = operandNum;
    }

    /**
     * @return 返回 name。
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 要设置的 name。
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 返回 operadNum。
     */
    public String getOperandNum() {
        return operandNum;
    }

    /**
     * @param
     */
    public void setOperandNum(String operandNum) {
        this.operandNum = operandNum;
    }

    /**
     * @return 返回 pri。
     */
    public String getPri() {
        return pri;
    }

    /**
     * @param pri 要设置的 pri。
     */
    public void setPri(String pri) {
        this.pri = pri;
    }

}
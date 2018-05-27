package com.ccniit.calculator.expValTool;

public interface Stactc
{
	/**
	 * 判定栈是否为空
	 *
	 * @return
	 */
	public boolean isEmpty();

	/**
	 * 判定栈是否为满
	 *
	 * @return
	 */
	public boolean isFull();

	/**
	 * 将指定的数据入栈
	 *
	 * @param o
	 */
	public void push(Object o);

	/**
	 * 出栈
	 *
	 * @return
	 */
	public Object pop();

	/**
	 * 获得栈顶元素
	 *
	 * @return
	 */
	public Object get();

}
package com.ccniit.calculator.expValTool;

public class StatcImpOfArray implements Stactc
{
	private Object[] datas;

	private int top;

	// -----------------------------------------
	public StatcImpOfArray()
	{
		top = -1;
		datas = null;
	}

	public StatcImpOfArray(int size)
	{
		datas = new Object[size];
		top = -1;
	}

	// ------------------------成员方法------------------------

	/*
	 * （非 Javadoc）
	 *
	 * @see com.ccniit.ds.lineList.stact.Stactc#get()
	 */
	public Object get()
	{
		return datas[top];
	}

	/*
	 * （非 Javadoc）
	 *
	 * @see com.ccniit.ds.lineList.stact.Stactc#isEmpty()
	 */
	public boolean isEmpty()
	{
		return top < 0;
	}

	/*
	 * （非 Javadoc）
	 *
	 * @see com.ccniit.ds.lineList.stact.Stactc#isFull()
	 */
	public boolean isFull()
	{
		return top >= datas.length - 1;
	}

	/*
	 * （非 Javadoc）
	 *
	 * @see com.ccniit.ds.lineList.stact.Stactc#pop()
	 */
	public Object pop()
	{
		if (this.isEmpty())
		{
			// System.out.println("栈已空，无法出栈");
			return null;
		} else
		{
			// return datas[top--];
			Object result = this.datas[top];
			this.top = this.top - 1;
			return result;
		}

	}

	/*
	 * （非 Javadoc）
	 *
	 * @see com.ccniit.ds.lineList.stact.Stactc#push(java.lang.Object)
	 */
	public void push(Object o)
	{

		if (this.isFull())
		{

			System.out.println("栈已满，无法入栈！");
		} else
		{
			// this.datas[++top] = o;
			top = top + 1;
			this.datas[top] = o;
		}

	}

}

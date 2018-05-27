/**
 *
 */
package com.ccniit.calculator.expVal.function;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author 龚军
 */
public class InvokeMethod {

    /**
     * @param args
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("java.lang.Math");
        HashMap map = new HashMap();// 生成一个空的映射表
        Method[] methods = cls.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            String returnType = method.getReturnType().getSimpleName();// 获得方法放回类型
            // .
            // 返回名字
            // System.out.println(method.getName()+" "+returnType);
            if (returnType.equals("double")) {
                map.put(method.getName(), method.getParameterTypes());// 放入哈希表
            }

        }
        //---------------------------------------------------------------------------
        Class[] parType = (Class[]) map.get("pow");
        Method method = cls.getMethod("pow", parType);

        Object[] arjlist = new Object[parType.length];
        arjlist[0] = new Double(2);
        arjlist[1] = new Double(3);
        Object obj = method.invoke(null, arjlist);

        System.out.println(((Double) obj).doubleValue());
    }

}

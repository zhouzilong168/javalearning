package javaele.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName Prox
 * @Description TODO CGLIB 动态代理demo
 * @Author thinkpad
 * @Date 2020/3/19 11:34
 * @Version 1.0
 **/
public class CglibProxy {
    /**
     * 被代理类需要为public，否则抛出InvocationTargetException
     */
    public static class RealService {
        /**
         * 方法非public，默认则反射实现出现问题，无法成功实现代理
         */
        public void sayHello() {
            System.out.println("hello");
        }
    }

    static class SimpleInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object object, Method method,
                                Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("entering " + method.getName());
            Object result = proxy.invokeSuper(object, args);
            System.out.println("leaving " + method.getName());
            return result;
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T getProxy(Class<T> cls) {
        Enhancer enhancer = new Enhancer(); // 接口默认方法也不可，必须为实现类
        enhancer.setSuperclass(cls);// 设置被代理的类，应该是具体有方法实体的类，如果是接口应该是接口的实现类的Class对象
        enhancer.setCallback(new SimpleInterceptor());// 设置被代理类的public非final方法时被调用时的处理类
        return (T) enhancer.create(); // 安全的转换成被代理类的类型
    }

    public static void main(String[] args) throws Exception {
        RealService proxyService = getProxy(RealService.class);
        proxyService.sayHello();
    }
}
package javaSE.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName JDKProxy
 * @Description TODO JDK动态代理demo
 * @Author thinkpad
 * @Date 2020/4/11 9:45
 * @Version 1.0
 **/
public class JDKProxy {

    static interface IService {
        public void sayHello();
    }

    static class RealService implements IService {

        @Override
        public void sayHello() {
            System.out.println("hello");
        }
    }

    static class SimpleInvocationHandler implements InvocationHandler {
        private Object realObj;

        public SimpleInvocationHandler(Object realObj) {
            this.realObj = realObj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("entering " + method.getName());
            Object result = method.invoke(realObj, args);
            System.out.println("leaving " + method.getName());
            return result;
        }
    }

    public static void main(String[] args) {
        final IService realService = new RealService();
        IService proxyService = (IService) Proxy.newProxyInstance(RealService.class.getClassLoader(),
                new Class<?>[]{IService.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("entering" + method.getName());
                        Object result = method.invoke(realService, args);
                        System.out.println("leaving" + method.getName());
                        return result;
                    }
                });
        proxyService.sayHello();
    }

}
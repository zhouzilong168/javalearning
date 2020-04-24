package test;

import org.junit.Test;

import java.sql.Connection;

/**
 * @ClassName Entity
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/3/23 15:11
 * @Version 1.0
 **/
public class VariableType {
    static String a = "He";
    static String b = "llo";
    /*
        static final String a;
        static final String b;
        static{
            a="He";
            b="llo";
        }
        static final String a = "He";
        static final String b = "llo";
    */
    final String aa = "He";
    final String bb = "llo";

    public static void main(String[] args) {
        System.out.println("TEST……");
        System.out.println(staticClass.STATICFIANL);
        System.out.println(staticClass.STATIC);
        /**
         * 静态常量编译后就会加入到常量池，不需要将字节码加载到内存中
         * 静态变量需要加载后方可访问
         * 类常量属于非静态，只可通过对象访问
         * 注：经过编译优化，静态常量 FIANL_VALUE 已经存到NotInit类自身常量池中，不会加载StaticClass
         */
    }

    @Test
    public void test() {
        String ab = "Hello";
        String a_b = a + b;
        System.out.println(a_b == ab);
        ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    }
}

class staticClass {
    static {
        System.out.println("staticClass……static");
    }

    public static String STATIC = "static value";
    public static final String STATICFIANL = "static final value";
    public final String FINAL = "final value";
}
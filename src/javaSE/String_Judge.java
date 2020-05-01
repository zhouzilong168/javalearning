package javaSE;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @ClassName String_Judge
 * @Description TODO 解决String/StringBuffer/StringBuilder+常量池的疑惑
 * @Author thinkpad
 * @Date 2020/4/26 11:36
 * @Version 1.0
 **/
public class String_Judge {

    /*
     static” “he” “llo” “hello”都会进入Class的常量池，类加载阶段不会创建实例，更不会加入到字符串常量池，
        但，在类加载阶段中的初始化阶段，会为静态变量指定初始值，即为s1赋值
     会创建”static”字符串对象，并且会保存一个指向它的引用到字符串常量池，并赋s1
     */
    public static String s1 = "static";  // 第一句

    public static void main(String[] args) {
        /*
        先会创建 ”he”和”llo” 的对象，并且会保存引用到字符串常量池中；
            '+': 内部是创建了一个StringBuilder对象，一路append，最后调用StringBuilder对象的toString方法得到
        一个String对象（内容是hello，注意这个toString方法会new一个String对象），并把它赋值给s1。
        注意啊，没有把hello的引用放入字符串常量池。
         */
        String s1 = new String("he") + new String("llo"); //第二句
        //String s1 = new StringBuffer("he").append("llo").toString(); //第二句
        //String s1 = "he" + "llo"; //第二句

        /*
        此时将"hello"对象的引用即s1地址加入到字符串常量池
         */
        s1.intern();   //第三句
        /*
        检查字符串常量池，有直接返回，即返回"hello"引用,也就是s1的地址
         */
        String s2 = "hello";  //第四句
        /*
        '==': 比较地址
         */
        System.out.println(s1 == s2);//输出是true。
    }

    @Test
    /**
     * 《深入理解jvm虚拟机第三版》例子
     */
    public void test_jvm() {
        // 根据上面解释，运行时StringBuffer第一次拼接得到 "计算机软件" 字符串，加入常量池
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        // jvm启动初始化的时候，"java" 已经被被创建，加入到常量池中
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);

        // 调用 new StringBuilder("计算机软件") ，"计算机软件" 就被加入到常量池中，
        // 后面再执行toString方法实则是创建一个String对象赋给str3
        String str3 = new StringBuilder("计算机软件").toString();
        System.out.println(str3.intern() == str3);
    }

    @Test
    /**
     * 运行时，字符串常量池有："he" "llo" "h" "ello" "hello"
     */
    public void test() {
        String s1 = new String("he") + new String("llo");//1
        String s2 = new String("h") + new String("ello");//2
        String s3 = s1.intern();//3 保存s1的地址到常量池
        String s4 = s2.intern();//4 直接返回上
        System.out.println(s1 == s3);
        System.out.println(s1 == s4);
        System.out.println(s2 == s4);
    }


    @Test
    /**
     * 测试存储位置: " ",new String,new StringBuffer/Builder
     */
    public void test_store() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        /*
          1在堆中创建 char[] value， 然后再创建一个String对象赋值引用给s0
          2查找char[]数组保存"hello"的引用，利用该引用再创建一个对象赋值引用给s1
          3创建一个保存"hello"的chars，并利用他创建一个StringBuffer对象
         */
        String s0 = "hello"; // 1
        String s = new String("hello"); // 2
        StringBuffer s1 = new StringBuffer("hello"); // 3

        System.out.print("s0和s不是同一个对象： ");
        System.out.println(s0 == s);
        System.out.println("String对象hashcode是根据字符串内容计算的，StringBuffer是继承Object的：");
        System.out.println(s0.hashCode() + " " + s.hashCode() + " " + s1.hashCode());
        //Field field = s1.getClass().getSuperclass().getDeclaredField("value");
        Field field = String.class.getDeclaredField("value");
        field.setAccessible(true);
        char[] chars = (char[]) field.get(s0);
        char[] charss = (char[]) field.get(s);
        System.out.println("s0和s中是同一个chars数组：" + chars.hashCode() + "  " + charss.hashCode()); // 反射获得两个对象内部chars一样
        chars[0] = '@';
        System.out.println("反射更改char数组之后： " + s0 + " " + s + "  " + s1);


        System.out.println("---------------------");
        String a = "abc";
        String b = "abc";
        String c = new String("abc");
        System.out.println("改变char[]value之前：");
        System.out.println("a=" + a);
        System.out.println("b=" + b);
        System.out.println("c=" + c);

        System.out.print("a与b是否都指向常量池内的地址：");
        System.out.println(a == b);

        System.out.print("a与c是否都指向常量池内的地址：");
        System.out.println(a == c);


        Field valueFieldString = String.class.getDeclaredField("value");
        valueFieldString.setAccessible(true);
        char[] value = (char[]) valueFieldString.get(c);
        value[2] = '@';
        System.out.println("改变char[]value之后：");

        System.out.println("a=" + a);
        System.out.println("b=" + b);
        System.out.println("c=" + c);

        System.out.print("a与b是否都指向常量池内的地址：");
        System.out.println(a == b);

        System.out.print("a与c是否都指向常量池内的地址：");
        System.out.println(a == c);

    }

}
/*
常量池
1.Class文件中的常量池
    这里面主要存放两大类常量： 字面量(Literal)：文本字符串等
符号引用(Symbolic References)：属于编译原理方面的概念，包含三类常量： 类和接口的全限定名(Full Qualified Name),
字段的名称和描述符(Descriptor)
方法的名称和描述符这个用javap看一下就能明白，这里只涉及字符串就不谈其他的了。简单地说，
用双引号引起来的字符串字面量都会进这里面。
2.运行时常量池
    方法区的一部分。Class文件中除了有类的版本、字段、方法、接口等描述信息外，还有常量池(Constant Pool Table)，
存放编译期生成的各种字面量和符号引用，这部分内容将在类加载后进入方法区的运行时常量池。
3.全局字符串常量池
    HotSpot VM里，记录interned string的一个全局表叫做StringTable，它本质上就是个HashSet<String>。
这是个纯运行时的结构，而且是惰性（lazy）维护的。注意它只存储对java.lang.String实例的引用，而不存储String对象的内容。
 注意，它只存了引用，根据这个引用可以得到具体的String对象。
一般我们说一个字符串进入了全局的字符串常量池其实是说在这个StringTable中保存了对它的引用，反之，
如果说没有在其中就是说StringTable中没有对它的引用。
引用： https://blog.csdn.net/weixin_38719347/article/details/80968148
 */
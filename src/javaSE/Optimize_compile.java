package javaSE;

/**
 * @ClassName Optimize_compile
 * @Description TODO 测试一些Java易错的类型运算知识，有关编译器优化
 * @Author thinkpad
 * @Date 2020/5/5 11:02
 * @Version 1.0
 **/
public class Optimize_compile {
    /**
     * 测试编译器运算的优化，以及运算符隐含知识
     *
     * @param args
     */
    public static void main(String[] args) {
        testByte();
        testShort();
        testChar();
        test_plus_sub_equal();
    }

    /**
     * +=，-+，存在隐式强转
     */
    private static void test_plus_sub_equal() {
        int a = 126;
        byte b = 1;
        b += a; // 相当于 b=(byte)(b+a);
        b -= a; // 相当于 b=(byte)(b-a);
        //b = 1+a; // 需要强转

    }

    /**
     * 测试byte
     */
    private static void testByte() {
        int a = 126, b = 1;
        final int fa = 126, fb = 1, faa = 127, fbb = 1;

        // * 数字字面常量
        byte s1 = 126 + 1;// 编译器优化，编译后是 short s1 = 2;
        //byte s11 = 127+1; // 编译器报错，优化后的 128=2^7-1超过byte范围

        // * final常量
        byte s2 = fa + fb; // final常量可以优化
        //byte s22 = faa+fbb; // final常量越界

        // * 非常量
        //byte s222 = a+b; // 无法优化，int->short需要强转
    }

    /**
     * 测试Short
     */
    private static void testShort() {
        int a = 1, b = 1;
        final int fa = 1, fb = 1, faa = 32767, fbb = 1;

        // * 数字字面常量
        short s1 = 1 + 1;// 编译器优化，编译后是 short s1 = 2;
        //short s11 = 32767+1; // 编译器报错，优化后的 32768=2^15-1超过short范围

        // * final常量
        short s2 = fa + fb; // final常量可以优化
        //short s22 = faa+fbb; // final常量越界

        // * 非常量
        //short s222 = a+b; // 无法优化，int->short需要强转
    }

    /**
     * 测试char
     */
    private static void testChar() {
        int a = 1, b = 1;
        final int fa = 1, fb = 1, faa = 65535, fbb = 1;

        // * 数字字面常量
        char s1 = 1 + 1;// 编译器优化，编译后是 short s1 = 2;
        //char s11 = 65535+1; // 编译器报错，优化后的 65536=2^16-1超过char范围

        // * final常量
        char s2 = fa + fb; // final常量可以优化
        //char s22 = faa+fbb; // final常量越界

        // * 非常量
        //char s222 = a+b; // 无法优化，int->short需要强转
    }


}

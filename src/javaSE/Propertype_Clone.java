package javaSE;

import java.lang.reflect.Field;
import java.text.ChoiceFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @ClassName Propertype_cone
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/5/14 10:50
 * @Version 1.0
 **/
public class Propertype_Clone {
    static class Refer implements Cloneable {
        char a = 'a';

        @Override
        public String toString() {
            return "Refer{" +
                    "a=" + a +
                    '}';
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    static class Test implements Cloneable {
        int id; //基本类型
        Integer iid; // 包装类型
        String name; // String类型 有常量池类似基本类型
        NumberFormat decimalFormat; // 引用类型，单例
        Refer refer; // 引用类型

        public Test() {
        }

        public Test(int id, Integer iid, String name, NumberFormat decimalFormat) {
            this.id = id;
            this.iid = iid;
            this.name = name;
            this.decimalFormat = decimalFormat;
        }

        public Test(int id, Integer iid, String name, NumberFormat decimalFormat, Refer refer) {
            this.id = id;
            this.iid = iid;
            this.name = name;
            this.decimalFormat = decimalFormat;
            this.refer = refer;
        }

        @Override
        public String toString() {
            return "Test{" +
                    "id=" + id +
                    ", iid=" + iid +
                    ", name='" + name + '\'' +
                    ", decimalFormat=" + decimalFormat +
                    ", refer=" + refer +
                    '}';
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            Test t = (Test) super.clone(); // 基本类型和常量池类型
            t.decimalFormat = (NumberFormat) decimalFormat.clone();
            t.refer = (Refer) refer.clone();
            return t;
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Test t = new Test(1, 1, "1", new DecimalFormat(), new Refer());
        System.out.println(t);
        Test tt = (Test) t.clone();
        System.out.println(tt);
/*        // 更改指向
        tt.iid=2;tt.name="2";tt.decimalFormat = new DecimalFormat();tt.refer=new Refer();
        System.out.println(t);
        System.out.println(tt);*/
        // 更改内容
        tt.refer.a = 'g';
        System.out.println(t);
        System.out.println(tt);
    }

    /*public static void main(String[] args) {
        Propertype_Clone p = new Propertype_Clone();
        Field[] fields = p.getClass().getDeclaredFields();
        for (Field f :
                fields) {
            if(!Object.class.isAssignableFrom(f.getType())){
                System.out.println("here:  "+f.getName());
            }
            if(f.getType().isPrimitive()){
                System.out.println("primitive: "+f.getName());
            }
*//*            System.out.println(f);
            System.out.println(f.getName());
            System.out.println(f.getType());
            System.out.println(f.getDeclaringClass());
            System.out.println(f.getModifiers());*//*
        }
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object obj = super.clone(); // 克隆基本类型
        Field[] fields = this.getClass().getDeclaredFields();
        Class<Object> clz = Object.class;
        for (Field f :
                fields) {
            if(!f.getType().isPrimitive()){
                f.setAccessible(true);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Propertype_Clone{" +
                "id=" + id +
                ", iid=" + iid +
                ", name='" + name + '\'' +
                ", decimalFormat=" + decimalFormat +
                '}';
    }*/
}

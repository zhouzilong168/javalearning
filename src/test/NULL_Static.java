package test;

/**
 * @ClassName NULL_Static
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/4/16 12:02
 * @Version 1.0
 **/
public class NULL_Static {

    public static void haha() {
        System.out.println("haha");
    }

    public void hehe() {
        System.out.println("hehe");
    }

    public static void main(String[] args) {
        ((NULL_Static) null).haha();
        new NULL_Static().hehe();
        new NULL_Static().haha();
        try {
            ((NULL_Static) null).hehe();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Abb a = new Abb();
        a.t();
        System.out.println("-----------------");
        Parent pp = new Child();
        Child cc = (Child) pp;
        cc.print();
    }

}

class Parent {
    int p = 1;

    public void print() {
        System.out.println("parent " + p);
    }
}

class Child extends Parent {
    int p = 2;

    public void print() {
        System.out.println("child " + p);
    }
}

abstract class Ab {
    int a = 1;

    public void test() {
        a = 2;
    }
}

class Abb extends Ab {
    public void t() {
        System.out.println(a);
        test();
        System.out.println(a);
        a = 3;
        System.out.println(a);
    }
}

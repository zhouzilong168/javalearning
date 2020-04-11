package others;

import java.util.Arrays;

/**
 * @ClassName Custom
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/3/13 17:23
 * @Version 1.0
 **/
public class Custom {

    public static void main(String[] arg) {
        int originValue = 1;
        String originString = "最初的值";

        Custom costom = new Custom();
        //--------------------------类型1----------------------------------------
        costom.methodA(originValue);
        System.out.println("Int实参的值:" + originValue);
        System.out.println();

        //--------------------------类型2----------------------------------------
        System.out.println("原始originString的值---:" + originString + "hashcode: " + originString.hashCode());
        costom.methodB(originString);
        System.out.println("originString的值---:" + originString);
        System.out.println();

        //--------------------------类型3----------------------------------------
        Person person = new Person();
        System.out.println("-----C前------hashcode-----:" + person.hashCode()
                + " name值：" + person.getName());
        costom.methodC(person);
        System.out.println("-------C后--------hashcode-----:" + person.hashCode()
                + "name值----:" + person.getName());
        System.out.println();

        //--------------------------类型4----------------------------------------
        System.out.println("-----D前-------" + person.hashCode() + "------" + person.getName());
        costom.methodD(person);
        System.out.println("-----D后-------" + person.hashCode() + "------" + person.getName());
        System.out.println();

        int[] arr = new int[]{1, 2, 3};
        //--------------------------类型5----------------------------------------
        System.out.println("前实参arr：" + Arrays.toString(arr) + "\t hashcode:" + arr.hashCode());
        costom.methodE(arr);
        System.out.println("后实参arr：" + Arrays.toString(arr) + "\t hashcode:" + arr.hashCode());
        System.out.println();

        //--------------------------类型6----------------------------------------
        System.out.println("前实参arr：" + Arrays.toString(arr) + "\t hashcode:" + arr.hashCode());
        costom.methodF(arr);
        System.out.println("后实参arr：" + Arrays.toString(arr) + "\t hashcode:" + arr.hashCode());

    }


    private void methodA(int formalP) {
        formalP = 2;
        System.out.println("------A后------:" + formalP);

    }

    private void methodB(String formalP) {
        System.out.println("-----B-------前------:" + formalP.hashCode()
                + "----值：---：" + formalP);
        formalP = "methodB";
        System.out.println("-----B-------后------:" + formalP.hashCode()
                + "----值：---:" + formalP);
    }

    private void methodC(Person person) {
        System.out.println("---------C--------前---------hashCode值----:" + person.hashCode()
                + "------name------" + person.getName());
        person = new Person();
        person.setName("小明");
        System.out.println("---------C--------后----------hashCode值----:" + person.hashCode()
                + "------name------" + person.getName());
    }

    private void methodD(Person person) {
        System.out.println("------D-----前-------person的hashCode:"
                + person.hashCode() + "---------name-------------:"
                + person.getName());
        person.setName("小明");
        System.out.println("------D-----后-------person的hashCode:"
                + person.hashCode() + "--------name--------------" + person.getName());
    }

    private void methodE(int[] arr) {
        System.out.println("入参arr：" + Arrays.toString(arr) + "\t hashcode:" + arr.hashCode());
        arr[0] = 99;
        System.out.println("改后：" + Arrays.toString(arr) + "\t hashcode:" + arr.hashCode());
    }

    private void methodF(int[] arr) {
        System.out.println("入参arr：" + Arrays.toString(arr) + "\t hashcode:" + arr.hashCode());
        arr = new int[]{3, 2, 1};
        System.out.println("改后：" + Arrays.toString(arr) + "\t hashcode:" + arr.hashCode());
    }
}



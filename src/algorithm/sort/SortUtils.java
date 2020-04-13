package algorithm.sort;

import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName SortUtils
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/3/20 11:00
 * @Version 1.0
 **/
public class SortUtils {
    private static int[] array = {12, 45, 67, 10, 98, 46, 79, 12, 12, 45, 12, 53, 13};

    public static void swap(int[] array, int i, int j) {
/*        array[i]^=array[j];
        array[j]^=array[i];
        array[i]^=array[j];*/
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void swapRep(int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        array[i] ^= array[j];
        array[j] ^= array[i];
        array[i] ^= array[j];
    }

    public static void printInts(int[] array) {
        for (int t :
                array) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    public static void executeAll(Class<?> clasz, String name) {
        printInts(array);
        try {
            clasz.getMethod(name, int[].class).invoke(null, array);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        printInts(array);
    }
    /*public static void executeMethod(Object obj,Method method,Object...args){
        try {
            method.invoke(obj,args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }*/
}

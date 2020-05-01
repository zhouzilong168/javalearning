package javaSE;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;

/**
 * test bitmap，using BitSet
 * 可用于对很大数据文件排序（大于内存）
 */
public class BitMap_BitSet {
    private static final int count = 1_0000_0000;
    private int[] array0 = new int[count];
    private int[] array1 = new int[count];
    private int[] array2 = new int[count];

    @Before
    public void before() {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            array0[i] = random.nextInt(count);
        }
        System.arraycopy(array0, 0, array1, 0, count);
        System.arraycopy(array0, 0, array2, 0, count);
    }

    @Test
    public void test0() {
        //int[] array = {13, 2, 12, 6, 11, 3, 10, 5, 9, 1, 8, 4, 7};
        BitSet bitSet = new BitSet(count);
        for (int i :
                array0) {
            bitSet.set(i);
        }
        for (int i = 0; i < bitSet.size(); i++) {
            if (bitSet.get(i)) {
                //System.out.print(i+", ");
            }
        }
        //System.out.println();

    }

    @Test
    public void test1() {
        BitSet bitSet = new BitSet(count);
        for (int i :
                array1) {
            bitSet.set(i);
        }
        int i = bitSet.nextSetBit(0);
        while (i != -1) {
            //System.out.print(i+", ");
            i = bitSet.nextSetBit(i + 1);
        }
        //System.out.println();
    }

    @Test
    public void test2() {
        Arrays.sort(array2);
        System.out.println(Arrays.toString(array2));
    }

    //@Test
    public void testUsing() {
        BitSet bitSet = new BitSet();
        bitSet.set(3);
        System.out.println(bitSet.size());
        bitSet.set(8);
        System.out.println(bitSet.size());
        System.out.println(Arrays.toString(bitSet.toByteArray()));
        System.out.println(Arrays.toString(bitSet.toLongArray()));
        System.out.println(bitSet.toString());
        bitSet.set(16);
        System.out.println("nextClearBit: " + bitSet.nextClearBit(0));
        int i = bitSet.nextSetBit(0);
        while (i != -1) {
            System.out.print(i + ", ");
            i = bitSet.nextSetBit(i + 1);
        }
        System.out.println();
    }
}

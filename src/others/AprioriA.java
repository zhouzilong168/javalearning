package others;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
/**
 * @ClassName ApriorALI
 * @Description TODO 解决频繁项集问题
 * @Author thinkpad
 * @Date 2020/5/27 11:35
 * @Version 1.0
 **/
public class AprioriA {
    static class Itemset { // 项集内部类封装
        public ArrayList<TreeSet<String>> itemset = new ArrayList<>();
    }
    public static int K_Item = 3;// 迭代次数
    public static double min_support_count = 2;//最小支持度计数值
    // 对应文件夹下的数据集
    public static String path = "C:\\Users\\thinkpad\\Desktop\\test\\";
    // 所有项集
    public static List<Itemset> AllItemSet = new ArrayList<>();
    // 初始项集
    public static Itemset originalItem = new Itemset();

    public static void main(String[] args) {
        aprioriProcess();
    }

    /**
     * 算法主体
     */
    public static void aprioriProcess() {
        readItemFromText(path, originalItem);//读取硬盘上的数据集
        Itemset firstItemSet = new Itemset();
        gen_first_item_set(firstItemSet);//首先生成频繁1项集
        int K = K_Item;
        Itemset forwordItemSet = firstItemSet;
        delete_blew_support_count(forwordItemSet);//去除低于最小支持度计数的项集
        AllItemSet.add(firstItemSet);//将所有的频繁项集保存起来
        while (K-- != 0) {
            Itemset backwordItemSet = new Itemset();
            apriori_gen(forwordItemSet, backwordItemSet);//根据频繁K-1项集生成频繁K项集
            delete_blew_support_count(backwordItemSet);//去除小于支持度计数的项集
            AllItemSet.add(backwordItemSet);
            forwordItemSet = backwordItemSet;//将指针指向频繁K-1项集
        }
        printResult();//输出结果
    }

    public static void printResult() {
        for (int i = 0; i < AllItemSet.size(); i++) {
            Itemset itemset = AllItemSet.get(i);

            for (TreeSet<String> everyItem : itemset.itemset) {
                System.out.println(everyItem.toString());
            }
        }
    }

    /*
     * 不可以一边遍历一边删除集合中的数据，这样会让你的集合遍历不完整或者越界！
     */
    public static void delete_blew_support_count(Itemset itemset) {
        /*
         * 在这里可以用迭代器iterator也可以用size()的形式来，
         * 其中用iterator会遍历的时候不可以删除元素
         * 用size可以删除元素，但是最后的n（n是中途被删除的个数）个元素就没有遍历到！！！！
         */
        ArrayList<Integer> deleteSetNum = new ArrayList<Integer>();
        for (int i = 0; i < itemset.itemset.size(); i++) {

            double suppoutCount = 0;
            TreeSet<String> item = itemset.itemset.get(i);
            boolean isPinfan = false;
            for (TreeSet<String> oriItem : originalItem.itemset) {
                if (contain(oriItem, item))
                    suppoutCount++;
                if (suppoutCount >= min_support_count) {
                    isPinfan = true;
                    break;
                }
            }
            if (!isPinfan)
                deleteSetNum.add(i);
        }
        for (int j = deleteSetNum.size() - 1; j >= 0; j--) {
            //System.out.println(deleteSetNum.get(j));
            itemset.itemset.remove((int) deleteSetNum.get(j));
        }
        /*
         * 下面这种做法由于remove
         * 的时候会改变集合的大小，所以不可以从头开始remove只可以从后面remove
         * 这样就保证不会越界
         */
//      for(int  i :deleteSetNum)
//      {
//          System.out.println(i);
//          itemset.itemset.remove(i);
//      }
    }

    //产生1项集
    public static void gen_first_item_set(Itemset firstItemSet) {
        TreeSet<String> itemset = new TreeSet<String>();
        for (TreeSet<String> per_ori_item : originalItem.itemset) {
            for (String item : per_ori_item) {
                itemset.add(item);
            }
        }
        for (String word : itemset) {
            TreeSet<String> everyitemset = new TreeSet<String>();
            everyitemset.add(word);
            firstItemSet.itemset.add(everyitemset);
        }
    }

    /*
     * 根据K-1项频繁产生频繁K项项集
     */
    public static void apriori_gen(Itemset one_item, Itemset second_item) {
        for (int i = 0; i < one_item.itemset.size(); i++) {
            for (int j = i + 1; j < one_item.itemset.size(); j++) {
                TreeSet<String> newItem = new TreeSet<String>();
                for (String peritem : one_item.itemset.get(i)) {
                    newItem.add(peritem);
                }
                for (String peritem : one_item.itemset.get(j)) {
                    newItem.add(peritem);
                }
                //如果没有非频繁K-1项集，加入k项集
                if (!has_infrequent_subset(newItem, one_item)) {
                    if (!find_in_already_set(newItem, second_item))//并且项集没有在之前出现
                        second_item.itemset.add(newItem);
                }
            }
        }
    }

    public static boolean find_in_already_set(TreeSet<String> newItem, Itemset second_item) {
        for (int i = 0; i < second_item.itemset.size(); i++) {
            //记住，TreeSet也可以用equals,这个函数真是好用，不过有时候效率低
            if (newItem.equals(second_item.itemset.get(i)))
                return true;
        }
        return false;
    }

    public static boolean has_infrequent_subset(TreeSet<String> newitem, Itemset one_item1) {//这里写错了！
        for (TreeSet<String> k_1_item : one_item1.itemset) {
            if (!contain(newitem, k_1_item))
                return false;
        }
        return true;
    }

    public static boolean contain(TreeSet<String> big, TreeSet<String> small) {
        for (String smallWord : small) {
            if (!big.contains(smallWord))
                return false;
        }
        return true;
    }

    public static void readItemFromText(String path, Itemset originalItem) {
        File file = new File(path);
        if (file.isDirectory()) {
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(new File(path + filelist[i])));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        String[] lineword = line.split("[^\\S]+");
                        TreeSet<String> itemset = new TreeSet<String>();
                        for (String word : lineword) {
                            itemset.add(word);
                        }
                        originalItem.itemset.add(itemset);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

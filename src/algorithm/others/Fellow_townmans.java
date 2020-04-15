package algorithm.others;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TODO 老乡问题 等价类划分
 */
public class Fellow_townmans {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), m = input.nextInt();
        while (input.hasNextInt()) {
            int[][] arr = new int[m][3];
            for (int i = 0; i < m; i++) {
                arr[i][0] = input.nextInt();
                arr[i][1] = input.nextInt();
                arr[i][2] = input.nextInt();
            }
            res.add(counts(list, arr));
            input.skip("\n");
            try {
                String line = input.nextLine();
                if (line.trim().equals("")) {
                    break;
                } else {
                    n = Integer.parseInt(line.substring(0, 1));
                    m = Integer.parseInt(line.substring(2, 3));
                }
            } catch (Exception e) {
            }
        }
        for (Integer i : res) {
            System.out.println(i);
        }
    }

    public static int counts(ArrayList list, int[][] arr) {
        list.clear();
        list.add(1);
        int cur = 1, count = 0;
        while (true) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i][2] == 0) continue;
                if (arr[i][0] == cur) {
                    list.add(arr[i][1]);
                    arr[i][2] = 0;
                } else if (arr[i][1] == cur) {
                    list.add(arr[i][0]);
                    arr[i][2] = 0;
                }
            }
            if (++count == list.size()) {
                break;
            } else {
                cur = (int) list.get(count);
            }
        }
        return count - 1;
    }
}

/*import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Fellow_townmans {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int N = sc.nextInt();
            int M = sc.nextInt();
            Map<Integer, Integer> mapRelation = new HashMap<>();//确认认识
            Map<Integer, Integer> mapUnRelation = new HashMap<>();//可能认识
            mapRelation.put(1, 1);
            for(int i = 0; i < M; i++){
                int one = sc.nextInt();
                int two = sc.nextInt();
                int three = sc.nextInt();
                //当有关系时
                if(three == 1){
                    if(mapRelation.containsKey(one) && !mapRelation.containsKey(two)){
                        mapRelation.put(two,two);
                        if(mapUnRelation.containsKey(two)){
                            int tmp = mapUnRelation.remove(two);
                            mapUnRelation.remove(tmp);
                            mapRelation.put(tmp, tmp);
                        }
                    }else if(mapRelation.containsKey(two) && !mapRelation.containsKey(one))  {
                        mapRelation.put(one,one);
                        if(mapUnRelation.containsKey(one)){
                            int tmp = mapUnRelation.remove(one);
                            mapUnRelation.remove(tmp);
                            mapRelation.put(tmp, tmp);
                        }
                    }else if(!mapRelation.containsKey(one) && !mapRelation.containsKey(two)) {
                        if(!mapUnRelation.containsKey(one)) mapUnRelation.put(one, two);
                        if(!mapUnRelation.containsKey(two)) mapUnRelation.put(two, one);
                    }
                }

            }

            System.out.println(mapRelation.size() - 1);
        }
    }
}*/
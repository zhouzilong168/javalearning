package test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName TestSome
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/9/11 17:43
 * @Version 1.0
 **/
public class TestSome {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("c",3);
        map.put("a",1);
        map.put("b",2);
        for (Map.Entry<String, Integer> entry:
                map.entrySet()){
            System.out.println(entry.getKey()+","+entry.getValue());
        }
        map = new LinkedHashMap<>();
        map.put("c",3);
        map.put("a",1);
        map.put("b",2);
        for (Map.Entry<String, Integer> entry:
                map.entrySet()){
            System.out.println(entry.getKey()+","+entry.getValue());
        }
        for (String key:
                map.keySet()) {
            System.out.println(key+","+map.get(key));
        }
    }
}

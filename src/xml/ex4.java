package javaEE.midware.RMI;

import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

import java.io.File;
import java.io.FileInputStream;

/**
 * @ClassName ex4
 * @Description TODO VDT-XML 解析指定嵌套路径下的标签数量
 * @Author thinkpad
 * @Date 2020/6/27 12:04
 * @Version 1.0
 **/
public class ex4 {
    // 计数变量/bix/package/command/parlist /bix/package/command/parlist/par
    static int parlist_count = 0, par_count = 0;

    public static void main(String[] args) {
        domain();
        System.out.println("依照题意，考虑四/五层嵌套个数：");
        System.out.println("/bix/package/command/parlist个数为： " + parlist_count);
        System.out.println("/bix/package/command/parlist/par个数为： " + par_count);
    }
    // 主体算法，注意回溯
    public static void domain() {
        try {
            // 解析xml文件，通过流的方式调用VDTjar包进行转换得到VTDNav
            File f = new File("src\\javaEE\\midware\\RMI\\ex4.xml");
            FileInputStream fis = new FileInputStream(f);
            byte[] ba = new byte[(int) f.length()];
            fis.read(ba);
            VTDGen vg = new VTDGen();
            vg.setDoc(ba);
            vg.parse(false);
            VTDNav vn = vg.getNav();
            // 进行匹配
            if (vn.matchElement("bix")) {
                // bix下只有一个节点，直接匹配进去
                if (vn.toElement(VTDNav.FIRST_CHILD, "package")) {
                    // 判断当前是否有子节点
                    if (vn.toElement(VTDNav.FIRST_CHILD)) {
                        do { // 循环遍历当前所有子节点
                            //System.out.println("command: "+vn.toString(vn.getCurrentIndex()));
                            // 若匹配到 command元素，这里考虑鲁棒性，选择 已有字符串调用equals方法
                            if ("command".equals(vn.toString(vn.getCurrentIndex()))) {
                                // 注：克隆保存当前节点，实现简单回溯
                                VTDNav tmp1 = vn.cloneNav();
                                if (vn.toElement(VTDNav.FIRST_CHILD)) {
                                    do {
                                        //System.out.println("parlist: "+vn.toString(vn.getCurrentIndex()));
                                        // 匹配parlist节点
                                        if ("parlist".equals(vn.toString(vn.getCurrentIndex()))) {
                                            parlist_count++;// 计数加一
                                            VTDNav tmp2 = vn.cloneNav();// 同上用于回溯
                                            if (vn.toElement(VTDNav.FIRST_CHILD)) {
                                                do {
                                                    if ("par".equals(vn.toString(vn.getCurrentIndex()))) {
                                                        // 匹配成功计数加一
                                                        par_count++;
                                                    }
                                                } while (vn.toElement(VTDNav.NEXT_SIBLING));
                                            }
                                            vn = tmp2;// 回溯
                                        }
                                    } while (vn.toElement(VTDNav.NEXT_SIBLING));
                                }
                                vn = tmp1;// 回溯
                            }
                        } while (vn.toElement(VTDNav.NEXT_SIBLING));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

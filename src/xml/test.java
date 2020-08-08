package javaEE.midware.RMI;

import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

import java.io.File;
import java.io.FileInputStream;

/**
 * @ClassName test
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/6/27 10:37
 * @Version 1.0
 **/
public class test { ///bix/package/command/parlist /par
    public static void first() {
        try {
            File f = new File("F:\\IDEA\\javalearning\\src\\javaEE\\midware\\RMI\\test.xml");
            FileInputStream fis = new FileInputStream(f);
            byte[] ba = new byte[(int) f.length()];
            fis.read(ba);
            VTDGen vg = new VTDGen();
            vg.setDoc(ba);
            vg.parse(false);
            VTDNav vn = vg.getNav();
            if (vn.matchElement("purchaseOrder")) {
                System.out.println(" orderDate==>"
                        + vn.toString(vn.getAttrVal("orderDate")));
                if (vn.toElement(VTDNav.FIRST_CHILD, "item")) {
                    if (vn.toElement(VTDNav.FIRST_CHILD)) {
                        do {
                            System.out.print(vn.toString(vn.getCurrentIndex()));
                            System.out.print("==>");
                            System.out.println(vn.toString(vn.getText()));
                        } while (vn.toElement(VTDNav.NEXT_SIBLING));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("exception occurred ==>" + e);
        }
    }

    public static void main(String[] args) throws Exception{
        /*domain();
        System.out.println(parlist_count);
        System.out.println(par_count);*/
        System.out.println("/bix/package/command/parlist个数为： 1");
    }
    static int parlist_count = 0,par_count = 0;
    public static void domain() throws Exception {
        try {
            File f = new File("F:\\IDEA\\javalearning\\src\\javaEE\\midware\\RMI\\ex4.xml");
            FileInputStream fis = new FileInputStream(f);
            byte[] ba = new byte[(int) f.length()];
            fis.read(ba);
            VTDGen vg = new VTDGen();
            vg.setDoc(ba);
            vg.parse(false);
            VTDNav vn = vg.getNav();
            if (vn.matchElement("bix")) {
                if (vn.toElement(VTDNav.FIRST_CHILD, "package")){
                    if (vn.toElement(VTDNav.FIRST_CHILD)) {
                        do {
                            //System.out.println("command: "+vn.toString(vn.getCurrentIndex()));
                            if ("command".equals(vn.toString(vn.getCurrentIndex()))) {
                                VTDNav tmp1 = vn.cloneNav(); // 递归回退
                                if (vn.toElement(VTDNav.FIRST_CHILD)) {
                                    do{
                                        //System.out.println("parlist: "+vn.toString(vn.getCurrentIndex()));
                                        if ("parlist".equals(vn.toString(vn.getCurrentIndex()))) {
                                            parlist_count++;
                                            VTDNav tmp2 = vn.cloneNav();
                                            if (vn.toElement(VTDNav.FIRST_CHILD)) {
                                                do{
                                                    if ("par".equals(vn.toString(vn.getCurrentIndex()))) {
                                                        par_count++;
                                                    }
                                                }while(vn.toElement(VTDNav.NEXT_SIBLING));
                                            }
                                            vn = tmp2;
                                        }
                                    }while(vn.toElement(VTDNav.NEXT_SIBLING));
                                }
                                vn = tmp1;
                            }
//                            System.out.print(vn.toString(vn.getCurrentIndex()));
//                            System.out.print("==>");
//                            System.out.println(vn.toString(vn.getText()));
                        } while (vn.toElement(VTDNav.NEXT_SIBLING));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("exception occurred ==>" + e);
        }
    }
}

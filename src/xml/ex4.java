package javaEE.midware.RMI;

import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

import java.io.File;
import java.io.FileInputStream;

/**
 * @ClassName ex4
 * @Description TODO VDT-XML ����ָ��Ƕ��·���µı�ǩ����
 * @Author thinkpad
 * @Date 2020/6/27 12:04
 * @Version 1.0
 **/
public class ex4 {
    // ��������/bix/package/command/parlist /bix/package/command/parlist/par
    static int parlist_count = 0, par_count = 0;

    public static void main(String[] args) {
        domain();
        System.out.println("�������⣬������/���Ƕ�׸�����");
        System.out.println("/bix/package/command/parlist����Ϊ�� " + parlist_count);
        System.out.println("/bix/package/command/parlist/par����Ϊ�� " + par_count);
    }
    // �����㷨��ע�����
    public static void domain() {
        try {
            // ����xml�ļ���ͨ�����ķ�ʽ����VDTjar������ת���õ�VTDNav
            File f = new File("src\\javaEE\\midware\\RMI\\ex4.xml");
            FileInputStream fis = new FileInputStream(f);
            byte[] ba = new byte[(int) f.length()];
            fis.read(ba);
            VTDGen vg = new VTDGen();
            vg.setDoc(ba);
            vg.parse(false);
            VTDNav vn = vg.getNav();
            // ����ƥ��
            if (vn.matchElement("bix")) {
                // bix��ֻ��һ���ڵ㣬ֱ��ƥ���ȥ
                if (vn.toElement(VTDNav.FIRST_CHILD, "package")) {
                    // �жϵ�ǰ�Ƿ����ӽڵ�
                    if (vn.toElement(VTDNav.FIRST_CHILD)) {
                        do { // ѭ��������ǰ�����ӽڵ�
                            //System.out.println("command: "+vn.toString(vn.getCurrentIndex()));
                            // ��ƥ�䵽 commandԪ�أ����￼��³���ԣ�ѡ�� �����ַ�������equals����
                            if ("command".equals(vn.toString(vn.getCurrentIndex()))) {
                                // ע����¡���浱ǰ�ڵ㣬ʵ�ּ򵥻���
                                VTDNav tmp1 = vn.cloneNav();
                                if (vn.toElement(VTDNav.FIRST_CHILD)) {
                                    do {
                                        //System.out.println("parlist: "+vn.toString(vn.getCurrentIndex()));
                                        // ƥ��parlist�ڵ�
                                        if ("parlist".equals(vn.toString(vn.getCurrentIndex()))) {
                                            parlist_count++;// ������һ
                                            VTDNav tmp2 = vn.cloneNav();// ͬ�����ڻ���
                                            if (vn.toElement(VTDNav.FIRST_CHILD)) {
                                                do {
                                                    if ("par".equals(vn.toString(vn.getCurrentIndex()))) {
                                                        // ƥ��ɹ�������һ
                                                        par_count++;
                                                    }
                                                } while (vn.toElement(VTDNav.NEXT_SIBLING));
                                            }
                                            vn = tmp2;// ����
                                        }
                                    } while (vn.toElement(VTDNav.NEXT_SIBLING));
                                }
                                vn = tmp1;// ����
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

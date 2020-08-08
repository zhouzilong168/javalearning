package xml;

import com.ximpleware.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @ClassName ec6
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/6/27 11:49
 * @Version 1.0
 **/
public class ec6 {
    public static void main(String[] args) {
        try {
            File f = new File("src\\javaEE\\midware\\RMI\\ec6.xml");
            FileInputStream fis =  new FileInputStream(f);
            byte[] b = new byte[(int) f.length()];

            fis.read(b);

            File f1 = new File("./out.txt");
            FileOutputStream fos = new FileOutputStream(f1);

            VTDGen vg = new VTDGen();
            vg.setDoc(b);
            vg.parse(true);
            VTDNav vn = vg.getNav();
            // get to the SOAP header
            if (vn.toElementNS(VTDNav.FC,"http://www.w3.org/2003/05/soap-envelope","Header"))
            {
                if (vn.toElement(VTDNav.FC))
                {
                    do {
                        if (vn.hasAttrNS("http://www.w3.org/2003/05/soap-envelope","mustUnderstand")){
                            long l = vn.getElementFragment();
                            int len = (int) (l>>32);
                            int offset = (int) l;
                            fos.write(b, offset, len);
                            fos.write("\n=========\n".getBytes());
                        }
                    }
                    while (vn.toElement(VTDNav.NS));
                }
                else
                    System.out.println("Header has not child elements");
            }
            else
                System.out.println(" Dosesn't have a header");
            fis.close();
            fos.close();

        }
        catch (ParseException e){
            System.out.println(" XML file parsing error \n"+e);
        }
        catch (NavException e){
            System.out.println(" Exception during navigation "+e);
        }
        catch (java.io.IOException e)
        {
            System.out.println(" IO exception condition"+e);
        }
    }
}

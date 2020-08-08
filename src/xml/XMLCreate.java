import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;

/**
 * @ClassName XMLCreate
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/6/7 15:00
 * @Version 1.0
 **/
public class XMLCreate {
    public static void main(String[] args) throws Exception {
        // ���ñ�ǩ��
        String all = "ѧ��������", student = "ѧ��", stuID = "ѧ��", name = "����", hobby = "����";
        Element root = new Element("ѧ��������"); // ��Ԫ��
        Document document = new Document(root);
        // ������һ���ӽڵ�ѧ���ڵ�
        Element child1 = new Element("ѧ��").setAttribute("ѧ��","n102");
        child1.addContent(new Element(name).setText("����"));
        child1.addContent(new Element(hobby).setText("ƹ����"));
        // �ڶ���
        Element child2 = new Element("ѧ��").setAttribute("ѧ��","n101");
        child2.addContent(new Element(name).setText("����"));
        child2.addContent(new Element(hobby).setText("��ë��"));
        // ������
        Element child3 = new Element("ѧ��").setAttribute("ѧ��","n103");
        child3.addContent(new Element(name).setText("�ܱ�"));
        child3.addContent(new Element(hobby).setText("��Χ��"));
        // ��ӵ����ڵ�
        root.addContent(child1);
        root.addContent(child2);
        root.addContent(child3);
        // �����xml�ļ�
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(document,new FileOutputStream("./src/ex2_xml.xml"));
        FileReader fileReader = new FileReader(new File("./src/ex2_xml.xml"));
        char[] chars = new char[128];
        while (fileReader.read(chars) > -1) {
            System.out.println(new String(chars));
        }
    }
}
        /*// ת���õ���ǰdocument����
        Document doc = db.parse(new File("src/ex2.xml"));
        // �õ���ǰ���ڵ�
        Element documentElement = doc.getDocumentElement();*/

        /*// ����xml�ĵ�����
        Document document = db.newDocument();
        document.setXmlVersion("1.0");
        // ������Ԫ��
        Element root = document.createElement("ѧ��������");
        // �����ӽڵ�
        Element child_1 = document.createElement("ѧ��");
        child_1.setAttribute("ѧ��","n101");
        child_1.setIdAttribute("ѧ��",true); // ����id����
        Element child_1_1 = document.createElement("����");
        Text text_1 = document.createTextNode("����");
        child_1_1.appendChild(text_1);
        Element child_1_2 = document.createElement("����");
        Text text_2 = document.createTextNode("ƹ����");
        child_1_2.appendChild(text_2);
        document.appendChild(root);
        root.appendChild(child_1);
        child_1.appendChild(child_1_1);
        child_1.appendChild(child_1_2);

        System.out.println("�����õ���xml�ĵ���ʽ�������");
        xml.XMLPrint.show(document.getDocumentElement());
        xml.XMLPrint.print();
*/
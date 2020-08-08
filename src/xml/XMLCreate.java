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
        // 设置标签名
        String all = "学生花名册", student = "学生", stuID = "学号", name = "名字", hobby = "爱好";
        Element root = new Element("学生花名册"); // 根元素
        Document document = new Document(root);
        // 创建第一个子节点学生节点
        Element child1 = new Element("学生").setAttribute("学号","n102");
        child1.addContent(new Element(name).setText("刘红"));
        child1.addContent(new Element(hobby).setText("乒乓球"));
        // 第二个
        Element child2 = new Element("学生").setAttribute("学号","n101");
        child2.addContent(new Element(name).setText("王江"));
        child2.addContent(new Element(hobby).setText("羽毛球"));
        // 第三个
        Element child3 = new Element("学生").setAttribute("学号","n103");
        child3.addContent(new Element(name).setText("曹斌"));
        child3.addContent(new Element(hobby).setText("下围棋"));
        // 添加到根节点
        root.addContent(child1);
        root.addContent(child2);
        root.addContent(child3);
        // 输出到xml文件
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(document,new FileOutputStream("./src/ex2_xml.xml"));
        FileReader fileReader = new FileReader(new File("./src/ex2_xml.xml"));
        char[] chars = new char[128];
        while (fileReader.read(chars) > -1) {
            System.out.println(new String(chars));
        }
    }
}
        /*// 转化得到当前document对象
        Document doc = db.parse(new File("src/ex2.xml"));
        // 得到当前根节点
        Element documentElement = doc.getDocumentElement();*/

        /*// 创建xml文档对象
        Document document = db.newDocument();
        document.setXmlVersion("1.0");
        // 创建根元素
        Element root = document.createElement("学生花名册");
        // 创建子节点
        Element child_1 = document.createElement("学生");
        child_1.setAttribute("学号","n101");
        child_1.setIdAttribute("学号",true); // 设置id属性
        Element child_1_1 = document.createElement("名字");
        Text text_1 = document.createTextNode("刘红");
        child_1_1.appendChild(text_1);
        Element child_1_2 = document.createElement("爱好");
        Text text_2 = document.createTextNode("乒乓球");
        child_1_2.appendChild(text_2);
        document.appendChild(root);
        root.appendChild(child_1);
        child_1.appendChild(child_1_1);
        child_1.appendChild(child_1_2);

        System.out.println("创建得到的xml文档格式化输出后：");
        xml.XMLPrint.show(document.getDocumentElement());
        xml.XMLPrint.print();
*/
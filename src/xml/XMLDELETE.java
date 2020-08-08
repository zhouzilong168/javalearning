package xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * @ClassName xml.XMLDELETE
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/6/7 16:47
 * @Version 1.0
 **/
public class XMLDELETE {
    public static void main(String[] args) throws Exception {
        // 得到解析工厂
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // 得到文档建造者
        DocumentBuilder db = dbf.newDocumentBuilder();
        // 转化得到当前document对象
        Document doc = db.parse(new File("src/ex2_4.xml"));
        // 得到当前根节点
        Element documentElement = doc.getDocumentElement();

        // 修改对应
        delete(documentElement);
        //modify(documentElement);
        // 通过第（2）题程序打印
        XMLPrint.show(documentElement);
        XMLPrint.print();
    }

    // 递归查找修改
    private static void modify(Node node) {
        NodeList childNodes = node.getChildNodes();
        int size = childNodes.getLength();
        for (int i = 0; i < size; i++) {
            Node item = childNodes.item(i);
            if ("How to become a programmer".equals(item.getTextContent())) {
                item.setTextContent("five thousand years up and down");
                System.out.println("modify success");
            }
            modify(item);
        }
    }
    private static Node parent;// 暂存父节点，用于删除
    // 删除节点
    private static void delete(Node node) {
        NodeList childNodes = node.getChildNodes();
        int size = childNodes.getLength();
        Node tmp = parent; // 暂存父节点，用于后面回溯
        parent = node; // 更新为当前节点
        // 遍历子节点
        for (int i = 0; i < size; i++) {
            Node item = childNodes.item(i);
            // 当前节点名字为price，且内容不空，内容为"49.99$"
            if ("price".equals(item.getNodeName()) && item.getTextContent() != null && "49.99$".equals(item.getTextContent())) {
                parent.removeChild(item);
                System.out.println("delete success");
            }
            delete(item);
        }
        parent = tmp; // 回溯
    }
}

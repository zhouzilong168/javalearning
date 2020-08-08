package xml;

import org.jdom2.output.XMLOutputter;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @ClassName xml.XMLPrint
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/6/7 11:39
 * @Version 1.0
 **/
public class XMLPrint {
    // 用于存储输出
    private static StringBuilder str = new StringBuilder();
    // 用于记录递归层次，以便格式化输出 "\t"制表符个数
    private static int count = 0;

    public static void main(String[] args) throws Exception {
        // 得到解析工厂
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // 得到文档建造者
        DocumentBuilder db = dbf.newDocumentBuilder();
        // 转化得到当前document对象
        Document doc = db.parse(new File("src/ex2.xml"));
        // 得到当前根节点
        Element documentElement = doc.getDocumentElement();

        // 打印DOCTYPE DTD
        append("DOCTYPE node:");
        append(doc.getDoctype().getInternalSubset());
        // 打印节点内容
        append("Document body contents are:");
        // 递归函数实现格式化
        show(documentElement);
        System.out.println(str);
    }

    public static void print() {
        System.out.println(str);
    }
    // 递归格式化元素节点及子节点
    public static void show(Node e) {
        count++; // 层次加一，用于格式化制表符输出
        // 当前节点处理
        append("Node: " + e.getNodeName());
        append("NodeType: " + typeName(e.getNodeType()));
        if (e.getNodeType() == 3) { // 文本节点需要单独处理
            append("Content: "+e.getNodeValue());
            count--;
            return;
        }
        // 属性处理
        NamedNodeMap attributes = e.getAttributes();
        if (attributes != null) {
            int attrSize = attributes.getLength();
            if (attrSize > 0) {
                append("Element Attributes are:");
                for (int i = 0; i < attrSize; i++) {
                    append(attributes.item(i).getNodeName() + "=" + attributes.item(i).getNodeValue());
                }
            }
        }
        // 子元素处理
        NodeList childNodes = e.getChildNodes();
        if (childNodes != null) {
            int childSize = childNodes.getLength();
            append("Child Node of " + e.getNodeName() + " are:");
            for (int i = 0; i < childSize; i++) {
                show(childNodes.item(i));
            }
        }
        count--; // 回溯，方便制表符的缩进
    }
    // 处理缩进量
    private static void append_t(int count) {
        for (int i = 0; i < count; i++) {
            str.append("\t");
        }
    }
    // 优化StringBuilder的append
    private static void append(String s) {
        append_t(count);
        str.append(s + "\n");
    }
    // 节点类型转换成字符串名字
    private static String typeName(short i) {
        String res = null;
        switch (i) {
            case 1:
                res = "element";
                break;
            case 2:
                res = "attribute";
                break;
            case 3:
                res = "text";
                break;
        }
        return res;
    }
}

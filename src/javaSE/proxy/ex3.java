package javaSE.proxy;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName ex3
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/6/27 15:37
 * @Version 1.0
 **/
public class ex3 {
    public static void main(String[] args) throws Exception {
        //1 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2 获得连接
        String url = "jdbc:mysql://localhost:3306/50q";
        Connection conn = DriverManager.getConnection(url, "root", "123456");
        //3 获得执行sql语句的对象
        Statement stmt = conn.createStatement();
        //4 执行SQL语句 查询数据库中的字段
        ResultSet rs = stmt.executeQuery("select * from user");
        //5处理结果集
        User user = null;
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            // 获得一行数据 并封装到user中
            user = new User(rs.getString(1),rs.getString(2),rs.getInt(3));
            System.out.println(user);
            users.add(user);
        }
        doCreate(users); // 通过数据创建xml文件
        List<User> userList = doSave(); // SAX解析XML文件并保存到数据库中
        String sql = "insert into user values('";
        for (User u :
                userList) {
            System.out.println(sql + u.getName() + "','" + u.getPassword() +"',"+ u.getPriviledge() + ");");
            int i = stmt.executeUpdate(sql + u.getName() + "','" + u.getPassword() +"',"+ u.getPriviledge() + ");");
            if (i > 0) {
                System.out.println("成功");
            }
        }
        //6释放资源 注意顺序
        rs.close();
        stmt.close();
        conn.close();
    }
    // 通过DOM创建xml文件
    private static void doCreate(List<User> users) throws Exception{
        // 设置标签名
        String all = "用户册", user = "用户", name = "用户名", pwd = "用户密码", priv = "用户权限";
        Element root = new Element(all); // 根元素
        Document document = new Document(root);
        List<Element> childs = new ArrayList<>();
        for (User u :
                users) { // 遍历用户列表 生成节点
            Element child = new Element(user);
            child.addContent(new Element(name).setText(u.getName()));
            child.addContent(new Element(pwd).setText(u.getPassword()));
            child.addContent(new Element(priv).setText(String.valueOf(u.getPriviledge())));
            root.addContent(child); // 添加到根节点上
        }
        // 输出到xml文件
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(document,new FileOutputStream("src\\javaSE\\proxy\\jianli.xml"));
        FileReader fileReader = new FileReader(new File("src\\javaSE\\proxy\\jianli.xml"));
        char[] chars = new char[128];
        while (fileReader.read(chars) > -1) {
            System.out.println(new String(chars));
        }
    }
    private static List<User> doSave() throws Exception{
        // 使用SAXParserFactory创建SAX解析工厂
        SAXParserFactory spf = SAXParserFactory.newInstance();
        // 通过SAX解析工厂得到解析器对象
        SAXParser sp = spf.newSAXParser();
        // 通过解析器对象得到一个XML的读取器
        XMLReader xmlReader = sp.getXMLReader();
        // 设置读取器的事件处理器
        xmlReader.setContentHandler(new MySaxHanlder());
        // 解析xml文件
        xmlReader.parse("src\\javaSE\\proxy\\jianli.xml");
        return MySaxHanlder.users;
    }
    static class MySaxHanlder extends DefaultHandler{
        //集合用来存放书对象 定义静态变量用于解析返回
        static List<User> users = new ArrayList<>();
        User user;
        //全局变量用来记录每一次查找解析到的标签 方便清空
        String previousTagName;

        /**
         * 每解析到 一个元素（element）的时候都会触发这个函数，
         * 并且将这个element的属性attributes和值value当作参数传进来
         * @param uri
         * @param localName
         * @param qName 限定的名称（带有前缀），如果限定的名称不可用，则为空字符串。
         * @param attributes 元素的属性。如果没有属性，则它将是空的 Attributes 对象
         * @throws SAXException
         */
        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {
            //找到了 用户 开始标签
            if (qName.equals("用户")) {//创建对象 准备接收其属性
                user = new User();
            }
            previousTagName = qName; // 保存当前元素名
        }
        // 当解析到一个元素标签的结束的时候 会调用
        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            //找到了 用户 结束标签
            if (qName.equals("用户")) {
                users.add(user);
                user = null;
                //把用户对象加入集合中 同时并将其清空 用于下一次查找
            }//本标签内的查找 结束 清空tag
            previousTagName = "";
        }
        // 当解析到一个文本节点的时候会调用
        @Override
        public void characters(char[] ch, int start, int length)
                throws SAXException {

            //获取文本节点内容
            String text = new String(ch, start, length);
            // 设置user对象
            switch (previousTagName) {
                case "用户名":
                    user.setName(text);
                    break;
                case "用户密码":
                    user.setPassword(text);
                    break;
                case "用户权限":
                    user.setPriviledge(Integer.valueOf(text));
                    break;
            }
        }
    }
}

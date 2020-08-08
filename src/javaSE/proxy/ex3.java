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
        //1 ע������
        Class.forName("com.mysql.jdbc.Driver");
        //2 �������
        String url = "jdbc:mysql://localhost:3306/50q";
        Connection conn = DriverManager.getConnection(url, "root", "123456");
        //3 ���ִ��sql���Ķ���
        Statement stmt = conn.createStatement();
        //4 ִ��SQL��� ��ѯ���ݿ��е��ֶ�
        ResultSet rs = stmt.executeQuery("select * from user");
        //5��������
        User user = null;
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            // ���һ������ ����װ��user��
            user = new User(rs.getString(1),rs.getString(2),rs.getInt(3));
            System.out.println(user);
            users.add(user);
        }
        doCreate(users); // ͨ�����ݴ���xml�ļ�
        List<User> userList = doSave(); // SAX����XML�ļ������浽���ݿ���
        String sql = "insert into user values('";
        for (User u :
                userList) {
            System.out.println(sql + u.getName() + "','" + u.getPassword() +"',"+ u.getPriviledge() + ");");
            int i = stmt.executeUpdate(sql + u.getName() + "','" + u.getPassword() +"',"+ u.getPriviledge() + ");");
            if (i > 0) {
                System.out.println("�ɹ�");
            }
        }
        //6�ͷ���Դ ע��˳��
        rs.close();
        stmt.close();
        conn.close();
    }
    // ͨ��DOM����xml�ļ�
    private static void doCreate(List<User> users) throws Exception{
        // ���ñ�ǩ��
        String all = "�û���", user = "�û�", name = "�û���", pwd = "�û�����", priv = "�û�Ȩ��";
        Element root = new Element(all); // ��Ԫ��
        Document document = new Document(root);
        List<Element> childs = new ArrayList<>();
        for (User u :
                users) { // �����û��б� ���ɽڵ�
            Element child = new Element(user);
            child.addContent(new Element(name).setText(u.getName()));
            child.addContent(new Element(pwd).setText(u.getPassword()));
            child.addContent(new Element(priv).setText(String.valueOf(u.getPriviledge())));
            root.addContent(child); // ��ӵ����ڵ���
        }
        // �����xml�ļ�
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(document,new FileOutputStream("src\\javaSE\\proxy\\jianli.xml"));
        FileReader fileReader = new FileReader(new File("src\\javaSE\\proxy\\jianli.xml"));
        char[] chars = new char[128];
        while (fileReader.read(chars) > -1) {
            System.out.println(new String(chars));
        }
    }
    private static List<User> doSave() throws Exception{
        // ʹ��SAXParserFactory����SAX��������
        SAXParserFactory spf = SAXParserFactory.newInstance();
        // ͨ��SAX���������õ�����������
        SAXParser sp = spf.newSAXParser();
        // ͨ������������õ�һ��XML�Ķ�ȡ��
        XMLReader xmlReader = sp.getXMLReader();
        // ���ö�ȡ�����¼�������
        xmlReader.setContentHandler(new MySaxHanlder());
        // ����xml�ļ�
        xmlReader.parse("src\\javaSE\\proxy\\jianli.xml");
        return MySaxHanlder.users;
    }
    static class MySaxHanlder extends DefaultHandler{
        //���������������� ���徲̬�������ڽ�������
        static List<User> users = new ArrayList<>();
        User user;
        //ȫ�ֱ���������¼ÿһ�β��ҽ������ı�ǩ �������
        String previousTagName;

        /**
         * ÿ������ һ��Ԫ�أ�element����ʱ�򶼻ᴥ�����������
         * ���ҽ����element������attributes��ֵvalue��������������
         * @param uri
         * @param localName
         * @param qName �޶������ƣ�����ǰ׺��������޶������Ʋ����ã���Ϊ���ַ�����
         * @param attributes Ԫ�ص����ԡ����û�����ԣ��������ǿյ� Attributes ����
         * @throws SAXException
         */
        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {
            //�ҵ��� �û� ��ʼ��ǩ
            if (qName.equals("�û�")) {//�������� ׼������������
                user = new User();
            }
            previousTagName = qName; // ���浱ǰԪ����
        }
        // ��������һ��Ԫ�ر�ǩ�Ľ�����ʱ�� �����
        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            //�ҵ��� �û� ������ǩ
            if (qName.equals("�û�")) {
                users.add(user);
                user = null;
                //���û�������뼯���� ͬʱ��������� ������һ�β���
            }//����ǩ�ڵĲ��� ���� ���tag
            previousTagName = "";
        }
        // ��������һ���ı��ڵ��ʱ������
        @Override
        public void characters(char[] ch, int start, int length)
                throws SAXException {

            //��ȡ�ı��ڵ�����
            String text = new String(ch, start, length);
            // ����user����
            switch (previousTagName) {
                case "�û���":
                    user.setName(text);
                    break;
                case "�û�����":
                    user.setPassword(text);
                    break;
                case "�û�Ȩ��":
                    user.setPriviledge(Integer.valueOf(text));
                    break;
            }
        }
    }
}

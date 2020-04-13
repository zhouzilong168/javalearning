package javaEE.midware.RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

/**
 * @ClassName Client
 * @Description TODO 本地客户端代码
 * @Author thinkpad
 * @Date 2020/4/13 10:42
 * @Version 1.0
 **/
public class Client {
    public static void main(String[] args) {
        try {
            // 设置安全类
            System.setSecurityManager(new RMISecurityManager());
            System.out.println("client Security loaded......");
            // 设置访问路径url
            String url = "rmi://localhost//SAMPLE-SERVER";
            // 通过路径及命名路径注册获得server对象实例
            SampleServer remoteObject = (SampleServer) Naming.lookup(url);
            System.out.println("acquire the remote object" + remoteObject);
            // 使用远程server对象方法
            System.out.println("sum:" + remoteObject.sum(1, 2));
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }
}

package javaEE.midware.RMI;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

/**
 * @ClassName SampleServerImpl
 * @Description TODO 远程对象服务器demo
 * @Author thinkpad
 * @Date 2020/4/13 10:37
 * @Version 1.0
 **/
public class SampleServerImpl extends UnicastRemoteObject implements SampleServer {

    /**
     * 默认端口 1099
     *
     * @throws RemoteException
     */
    public SampleServerImpl() throws RemoteException {
        super();
    }

    /**
     * 自定义端口
     *
     * @param port
     * @throws RemoteException
     */
    public SampleServerImpl(int port) throws RemoteException {
        super(port);
    }

    @Override
    public int sum(int a, int b) throws RemoteException {
        return a + b;
    }

    public static void main(String[] args) {
        try {
            // 设置安全管理
            System.setSecurityManager(new RMISecurityManager());
            // 创建远程对象实例
            SampleServerImpl server = new SampleServerImpl();
            // 加入远程对象实例到命名接口注册
            Naming.rebind("SAMPLE-SERVER", server);
            System.out.println("remote server is waiting......");
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

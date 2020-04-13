package javaEE.midware.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 远程对象服务器接口
 */
public interface SampleServer extends Remote {
    int sum(int a, int b) throws RemoteException;
}

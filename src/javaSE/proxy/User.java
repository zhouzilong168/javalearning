package javaSE.proxy;

/**
 * @ClassName User
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/6/27 16:43
 * @Version 1.0
 **/
public class User {
    String name;
    String password;
    int priviledge;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", priviledge=" + priviledge +
                '}';
    }

    public User(String name, String password, int priviledge) {
        this.name = name;
        this.password = password;
        this.priviledge = priviledge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPriviledge() {
        return priviledge;
    }

    public void setPriviledge(int priviledge) {
        this.priviledge = priviledge;
    }
}

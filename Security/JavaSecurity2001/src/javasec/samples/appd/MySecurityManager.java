package javasec.samples.appd;


public class MySecurityManager extends SecurityManager {
    public void checkRead(String s) {
        Class c[] = getClassContext();
        for (int i = 0; i < c.length; i++) {
            String name = c[i].getName();
            System.out.println(name);
        }
    }
}

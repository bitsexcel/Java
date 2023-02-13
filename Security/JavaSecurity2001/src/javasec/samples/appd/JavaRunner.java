package javasec.samples.appd;

import java.lang.reflect.*;

public class JavaRunner implements Runnable {
    final static int numArgs = 1;
    private JRClassLoader jrl;
    private Object args[];
    private String className;

    JavaRunner(JRClassLoader jrl, String className, Object args[]) {
        this.jrl = jrl;
        this.className = className;
        this.args = args;
    }

    void invokeMain(Class clazz) {
        Class argList[] = new Class[1];
        String strArray[] = new String[1];
        argList[0] = strArray.getClass();
        Method mainMethod = null;
        try {
            mainMethod = clazz.getMethod("main", argList);
        } catch (NoSuchMethodException nsme) {
            System.out.println("No main method in " + clazz.getName());
            System.exit(-1);
        }
    
        try {
            mainMethod.invoke(null, args);
        } catch (Throwable e) {
            if (e instanceof InvocationTargetException)
                e = ((InvocationTargetException) e)
                            .getTargetException();
            System.out.println("Procedure exited with exception " + e);
            e.printStackTrace();
        }
    }

    public void run() {
        Class target = null;
        try {
            target = jrl.loadClass(className);
            invokeMain(target);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Can’t load " + className);
        }
    }

    static Object[] getArgs(String args[]) {
        String passArgs[] = new String[args.length - numArgs];
        for (int i = numArgs; i < args.length; i++)
            passArgs[i - numArgs] = args[i];

        Object wrapArgs[] = new Object[1];
        wrapArgs[0] = passArgs;
        return wrapArgs;
    }

    public static void main(String args[]) {
        System.setSecurityManager(new JavaRunnerManager());
        JRClassLoader jrl = new JRClassLoader(args[0]);
        ThreadGroup tg = jrl.getThreadGroup();
        Thread t = new Thread(tg,
                new JavaRunner(jrl, args[1], getArgs(args)));
        t.start();
        try {
            t.join();
        } catch (InterruptedException ie) {
            System.out.println("Thread was interrupted");
        }
    }
}

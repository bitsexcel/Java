package javasec.samples.ch15;

import java.io.*;
import javax.security.auth.callback.*;

class MyCallbackHandler implements CallbackHandler {
    public void handle(Callback[] cb) throws IOException,
                                      UnsupportedCallbackException {
        for (int i = 0; i < cb.length; i++) {
            if (cb[i] instanceof NameCallback) {
                NameCallback nc = (NameCallback) cb[i];
                System.out.print(nc.getPrompt()+ " ");
                System.out.flush();
                String name = new BufferedReader
                       (new InputStreamReader(System.in)).readLine();
                nc.setName(name);
            }
            else if (cb[i] instanceof PasswordCallback) {
                PasswordCallback pc = (PasswordCallback) cb[i];
                System.out.print(pc.getPrompt()+ " ");
                System.out.flush();
                String pw = new BufferedReader
                        (new InputStreamReader(System.in)).readLine();
                pc.setPassword(pw.toCharArray());
                pw = null;   // Let pw be collected as soon as possible
            } else throw new
                   UnsupportedCallbackException(cb[i], "MyCallbackHandler");
        }
    }
}

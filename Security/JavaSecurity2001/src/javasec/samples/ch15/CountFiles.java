package javasec.samples.ch15;

import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.*;

public class CountFiles {

    static class NullCallbackHandler implements CallbackHandler {
        public void handle(Callback[] cb) {
            throw new IllegalArgumentException("Not implemented yet");
        }
    }

    static LoginContext lc = null;
    public static void main(String[] args) {
        // use the configured LoginModules for the “CountFiles” entry
        try {
           lc = new LoginContext("CountFiles",
                                 new NullCallbackHandler());
        } catch (LoginException le) {
            le.printStackTrace();
            System.exit(-1);
        } 

        try {
            lc.login();
            // if we return with no exception, authentication succeeded
        } catch (Exception e) {
            System.out.println("Login failed: " + e);
            System.exit(-1);
        }

        // now execute the code as the authenticated user
        Subject.doAs(lc.getSubject(), new CountFilesAction());
        System.exit(0);
    }
}

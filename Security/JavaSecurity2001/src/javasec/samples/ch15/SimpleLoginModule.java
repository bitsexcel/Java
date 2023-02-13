package javasec.samples.ch15;

import java.util.*;
import java.io.IOException;
import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.*;
import javax.security.auth.spi.*;

public class SimpleLoginModule implements LoginModule {

    private Subject subject;
    private CallbackHandler callbackHandler;
    private SimplePrincipal principal;
    private boolean debug;

    // State information for the currently-authenticated user
    private String userName = null;
    private boolean succeeded = false;
    private boolean commitSucceeded = false;

    public void initialize(Subject s, CallbackHandler cb,
                           Map sharedMap, Map options) {
 
        subject = s;
        callbackHandler = cb;

        // initialize any configured options
        debug = "true".equalsIgnoreCase((String)options.get("debug"));

        // We don’t use the shared map to cache results between
        // attempts, but otherwise we’d need to save it here.
    }

    public boolean login() throws LoginException {
        if (debug)
            System.err.println("SimpleLoginModule: Login");

        // This is where we’d normally do authentication. If
        // necessary, we could instantiate callback objects
        // and put them in an array and call the callback handler.
        // We could also retrieve information from the sharedMap
        // if we cached a previous login attempt.

        // Normally, we'd set this from the getName() method of
        // the name callback, or from the user environment.
        userName = "defaultUser";

        // We'd set this based on a password match. If we get
        // credentials from the user environment, then it would
        // always be true.
        succeeded = true;
        return true;
    }

    public boolean commit() throws LoginException {
        if (debug)
            System.err.println("SimpleLoginModule: Commit");

        if (!succeeded) {
            // We didn't authenticate the user, but someone else did.
            // Clean up our state, but don't add our principal to
            // the subject
            userName = null;
            return false;
        }

        principal = new SimplePrincipal(userName);
        // defaultUser might already be in the subject if another module
        // authenticated him.
        if (!subject.getPrincipals().contains(principal)) {
            subject.getPrincipals().add(principal);
        }

        // Clean up our internal state
        userName = null;
        commitSucceeded = true;
        return true;
    }

    public boolean abort() throws LoginException {
        if (debug)
            System.err.println("SimpleLoginModule: Abort");

        if (succeeded == false)
            // We failed, and so did someone else, so just clean up
    	    return false;
        else if (succeeded == true && commitSucceeded == true) {
            // Our login succeeded, but another required module failed
            // We must remove our principal and clean up
            logout();
        } else  {
            // Our commit failed, even though login succeeded
            // The rest of our internal state should already
            // have been cleaned up
            succeeded = false;
        }
        return true;
    }

    public boolean logout() throws LoginException {
        if (debug)
            System.err.println("SimpleLoginModule: Logout");

        subject.getPrincipals().remove(principal);
        principal = null;
        userName = null;
        succeeded = commitSucceeded = false;
        return true;
    }
}

ECHO Running Chapter 17 Client
ECHO Requires a Kerberos Server to be running locally
ECHO AND the RichGSSInitiator.jar in the classpath
java -Djava.security.manager -Djava.security.auth.login.config=csLogin.config -Djava.security.policy=RichGSSInitiator.policy com.richware.chap17.RichGSSService Client
ECHO Finished Running Chapter 17 Client
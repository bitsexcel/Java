ECHO Running Chapter 17 Server
ECHO Requires a Kerberos Server to be running locally
java -Djava.security.manager -Djava.security.auth.login.config=bcsLogin.config -Djava.security.policy=RichGSSService.policy com.richware.chap17.RichGSSService Server
ECHO Finished Running Chapter 17 Server
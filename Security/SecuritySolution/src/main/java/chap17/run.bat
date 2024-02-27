cd ..\..\.. 
ECHO Running Chapter 17
java -Djava.security.manager -Djava.security.auth.login.config=bcsLogin.config -Djava.security.policy=RichGSSService.policy com.jasabook.chap17.RichGSSService Server
PAUSE
java -Djava.security.manager -Djava.security.auth.login.config=csLogin.config -Djava.security.policy=RichGSSInitiator.policy com.jasabook.chap17.RichGSSService
PAUSE
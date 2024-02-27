ECHO Running Chapter 19
ECHO ENSURE that richjaas.jar and jaasaction.jar are in the CLASSPATH
ECHO ENSURE that the keystore file is in the root drive of C:\
java -Djava.security.manager -Djava.security.auth.login.config=jaas.config -Djava.security.policy=jaasapp.policy com.richware.chap19.JAASApp
ECHO Finished Running Chapter 19
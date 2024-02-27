cd ..\..\.. 
jar -cvf richjaas.jar -C com/richware/chap19/ JAASApp.class
PAUSE
jar -uvf richjaas.jar -C com/richware/chap19/ RichCallbackHandler.class
PAUSE
jar -cvf jaasaction.jar -C com/richware/chap19/ JAASAction.class

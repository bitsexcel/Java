cd ..\..\.. 
jar -cvf RichGSSService.jar -C com/richware/chap17/ RichGSSService.class
PAUSE
jar -cvf RichGSSInitiator.jar -C com/richware/chap17/ RichGSSService.class

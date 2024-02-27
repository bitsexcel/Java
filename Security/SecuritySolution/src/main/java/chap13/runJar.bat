cd ..\..\.. 
jar -cvf richprovider.jar -C com/richware/chap13/ RichProvider.class
PAUSE
jar -uvf richprovider.jar -C com/richware/chap13/ RichProvider$1.class
PAUSE
jar -uvf richprovider.jar -C com/richware/chap12/ RichRSACipher.class
PAUSE
jar -uvf richprovider.jar -C com/richware/chap13/ RichRC4Cipher.class
PAUSE
jar -uvf richprovider.jar -C com/richware/chap13/ RichRC4KeyGenerator.class
PAUSE
jar -uvf richprovider.jar -C com/richware/chap13/ TestRSACipher.class
PAUSE
jar -uvf richprovider.jar -C com/richware/chap13/ TestRC4Cipher.class
PAUSE

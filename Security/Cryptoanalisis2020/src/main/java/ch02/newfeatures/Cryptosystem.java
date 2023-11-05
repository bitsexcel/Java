package ch02.newfeatures;


public abstract sealed class Cryptosystem
    permits AES_Cryptosystem, RSA_Cryptosystem { 
	
	String encryptMessage() { return null;}
	String decryptMessage() { return null; }
} 
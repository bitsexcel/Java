package javasec.samples.ch03;

public class CorruptString {
    public static void modifyString(String src, String dst) {
    	char[] csrc = src.toCharArray();
    	char[] cdst = dst.toCharArray();
        for (int i = 0; i < src.length(); i++) {
            if (i == dst.length())
                return;
            //src.value[i] = dst.value[i];
            csrc[i] = cdst[i];
            
        }
    }

    public static void main(String[] args) {
        System.out.println("Original string is " + args[0]);
        modifyString(args[0], "SDO was here");
        System.out.println("Modified string is " + args[0]);
    }
}

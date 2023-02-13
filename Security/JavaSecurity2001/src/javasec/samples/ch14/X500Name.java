package javasec.samples.ch14;

// Store an X500 Name and extract its components on demand
public class X500Name {

    private String CN, OU, O, L, ST, C;
    private String name;
    private char nameChar[];

    public X500Name(String s) {
        if (s == null)
            throw new IllegalArgumentException("Name can’t be null");
        name = s;
    }

    public String getCN() {
        if (CN == null)
            CN = parse("CN=");
        return CN;
    }

    public String getOU() {
        if (OU == null)
            OU = parse("OU=");
        return OU;
    }

    public String getO() {
        if (O == null)
            O = parse("O=");
        return O;
    }

    public String getL() {
        if (L == null)
            L = parse("L=");
        return L;
    }

    public String getST() {
        if (ST == null)
            ST = parse("ST=");
        return ST;
    }

    public String getC() {
        if (C == null)
            C = parse("C=");
        return C;
    }

    // Parse the name for the given target
    private String parse(String target) {
        if (nameChar == null)
            nameChar = name.toCharArray();
        char targetChar[] = target.toCharArray();

        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] == targetChar[0]) {
                // Possible match, check further
                boolean found = true;   // At least so far...
                for (int j = 0; j < targetChar.length; j++) {
                    try {
                        if (nameChar[i + j] != targetChar[j]) {
                            // No match, continue on...
                            found = false;
                            break;
                        }
                    } catch (ArrayIndexOutOfBoundsException aioobe) {
                        // No match, and nothing left in nameChar
                        return null;
                    }
                }
                if (found) {
                    int firstPos = i + targetChar.length;
                    int lastPos;
                    int endChar;
                    if (nameChar[firstPos] == ’\"’)
                        endChar = ’\"’;
                    else endChar = ’,’;
                    // The substring will be terminated by a quote if
                    // the substring is quoted (CN="My Name",OU=...)
                    // or by a comma otherwise (L=New York,ST=...)
                    // or by the end of the string
                    // A badly formed substring will throw an
                    // ArrayIndexOutOfBoundsException
                    for (lastPos = firstPos + 1;
                                lastPos < nameChar.length; lastPos++) {
                        if (nameChar[lastPos] == endChar)
                            break;
                    }
                    // If the lastPos is a quote, then we need to
                    // include it in the string; if it’s a comma then
                    // we don’t
                    return new String(nameChar, firstPos,
                            (endChar == ’,’ ?
                                lastPos - firstPos :
                                lastPos - firstPos + 1));
                }
                // else try the next index
            }
        }
        return null;
    }

    public String toString() {
        getCN();
        getOU();
        getO();
        getL();
        getST();
        getC();
        return "CN=" + CN + ", " +
               "OU=" + OU + ", " +
               "O=" + O + ", " +
               "L=" + L + ", " +
               "ST=" + ST + ", " +
               "C=" + C;
    }
}

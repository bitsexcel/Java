package chapter13;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SignatureException;

import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.BCPGOutputStream;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureGenerator;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;

public class PGPClearTextSignatureExample
{
    private static boolean isWhiteSpace(byte b)
    {
        return b == '\r' || b == '\n' || b == '\t' || b == ' ';
    }

    private static int getLengthWithoutWhiteSpace(byte[] line)
    {
        int    end = line.length - 1;

        while (end >= 0 && isWhiteSpace(line[end]))
        {
            end--;
        }

        return end + 1;
    }

    private static void processLine(PGPSignatureGenerator sGen, OutputStream aOut, byte[] line)
        throws IOException
    {
        // note: trailing white space needs to be removed from the end of
        // each line for signature calculation RFC 4880 Section 7.1
        int length = getLengthWithoutWhiteSpace(line);
        if (length > 0)
        {
            sGen.update(line, 0, length);
        }

        aOut.write(line, 0, line.length);
    }

    private static byte[] readInputLine(InputStream fIn)
        throws IOException
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();

        int ch;

        while ((ch = fIn.read()) >= 0)
        {
            bOut.write(ch);
            if (ch == '\r')
            {
                fIn.mark(1);
                if ((ch = fIn.read()) == '\n')
                {
                    bOut.write(ch);
                }
                else
                {
                    fIn.reset();
                }
                return bOut.toByteArray();
            }
            else if (ch == '\n')
            {
                return bOut.toByteArray();
            }
        }

        return null;
    }

    public static void createCleartextSignature(PGPPrivateKey pgpPrivKey, int digest, InputStream msgIn, OutputStream clrOut)
        throws PGPException, IOException
    {
        BufferedInputStream mIn = new BufferedInputStream(msgIn);

        int keyAlgorithm = pgpPrivKey.getPublicKeyPacket().getAlgorithm();
        PGPSignatureGenerator sGen = new PGPSignatureGenerator(
            new JcaPGPContentSignerBuilder(keyAlgorithm, PGPUtil.SHA256).setProvider("BC"));

        sGen.init(PGPSignature.CANONICAL_TEXT_DOCUMENT, pgpPrivKey);

        ArmoredOutputStream aOut = new ArmoredOutputStream(clrOut);

        aOut.beginClearText(digest);

        //
        // note the last \n/\r/\r\n in the file is ignored
        //
        byte[] line = readInputLine(mIn);

        processLine(sGen, aOut, line);

        while ((line = readInputLine(mIn)) != null)
        {
            sGen.update((byte)'\r');
            sGen.update((byte)'\n');

            processLine(sGen, aOut, line);
        }

        aOut.endClearText();

        BCPGOutputStream bOut = new BCPGOutputStream(aOut);

        sGen.generate().encode(bOut);

        aOut.close();
    }
}

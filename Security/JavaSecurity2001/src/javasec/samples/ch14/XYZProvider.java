
import java.security.*;

public class XYZProvider extends Provider {
    public XYZProvider() {
        super("XYZ", 1.0, "XYZ Security Provider v1.0");
        put("KeyManagerFactory.XYZ", "com.xyz.SSLKeyManagerFactory");
    }
}

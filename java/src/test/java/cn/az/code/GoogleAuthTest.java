package cn.az.code;

import cn.az.code.auth.GoogleAuthenticator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @author Liz
 * @version 2019/11/27
 */
@SpringBootTest
public class GoogleAuthTest {

    @Test
    public void genSecretTest() {
        String secret = GoogleAuthenticator.generateSecretKey();
        String url = GoogleAuthenticator.getQrBarcodeUrl("99999999", "dluo", secret);
        System.out.println("Please register " + url);
        System.out.println("Secret key is " + secret);
    }

    // Change this to the saved secret from the running the above test.
    static String savedSecret = "HDHTPUXEAAJVKGVM";

    @Test
    public void authTest() throws IOException {
        // enter the code shown on device. Edit this and run it fast before the code expires!
        long code = 222458;
        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(5); //should give 5 * 30 seconds of grace...
        boolean r = ga.checkCode(savedSecret, code, t);
        System.out.println("Check code = " + r);
    }
}

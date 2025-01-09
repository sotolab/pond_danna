package encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionExample {
    public static void main(String[] args) throws Exception {
        String plainText = "This is plain text to be encrypted.";
        String secretKeyString = "1234567890123456"; // AES는 고정된 키 길이(128, 192, 256bit 사용)
        byte[] secretKey = secretKeyString.getBytes();

        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedText = cipher.doFinal(plainText.getBytes());

        String encodedText = Base64.getEncoder().encodeToString(encryptedText);
        System.out.println("암호문은 \"" + encodedText + "\"");
    }
}

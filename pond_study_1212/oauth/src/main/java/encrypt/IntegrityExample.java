package encrypt;

import java.security.MessageDigest;

public class IntegrityExample {
    public static void main(String[] args) throws Exception {
        String message = "This is the message to be sent.";
        MessageDigest digest = MessageDigest.getInstance("SHA-256"); // SHA-256 해시 알고리즘을 사용하기 위한 MessageDigest 객체 생성
        byte[] hash = digest.digest(message.getBytes());

        StringBuffer hexHash = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) hexHash.append('0');
            hexHash.append(hex);
        }

        System.out.println("The hash of the message is: " + hexHash.toString());
    }
}

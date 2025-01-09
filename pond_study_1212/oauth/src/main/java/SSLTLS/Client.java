package SSLTLS;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.KeyStore;

public class Client {
    public static void main(String[] args) throws Exception {
        String keystorePassword = "981214";

        try {
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(new FileInputStream("clientkeystore.jks"), keystorePassword.toCharArray());

            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ks);

            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);

            SSLSocketFactory ssf = context.getSocketFactory();
            SSLSocket socket = (SSLSocket) ssf.createSocket("localhost", 8888);

            // 지원하는 암호화 스위트 설정
            String[] suites = new String[] {
                    "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256",
                    "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384",
                    "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256",
                    "TLS_DHE_RSA_WITH_AES_256_GCM_SHA384"
            };
            socket.setEnabledCipherSuites(suites);

            // TLS 버전 설정
            socket.setEnabledProtocols(new String[] {"TLSv1.2", "TLSv1.3"});

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("Hello from client!");
            String response = in.readLine();
            System.out.println("Server response: " + response);

            socket.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

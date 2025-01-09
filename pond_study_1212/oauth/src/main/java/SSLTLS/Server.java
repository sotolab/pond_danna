package SSLTLS;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.KeyStore;

public class Server {
    public static void main(String[] args) throws Exception {
        String keystorePassword = "981214";

        try {
            // 키스토어 설정
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(new FileInputStream("serverkeystore.jks"), keystorePassword.toCharArray());

            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, keystorePassword.toCharArray());

            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ks);

            SSLContext context = SSLContext.getInstance("TLS");
            context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            SSLServerSocketFactory ssf = context.getServerSocketFactory();
            SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(8888);

            // 지원하는 암호화 스위트 설정
            String[] suites = new String[] {
                    "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256",
                    "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384",
                    "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256",
                    "TLS_DHE_RSA_WITH_AES_256_GCM_SHA384"
            };
            serverSocket.setEnabledCipherSuites(suites);

            // TLS 버전 설정
            serverSocket.setEnabledProtocols(new String[] {"TLSv1.2", "TLSv1.3"});

            System.out.println("Server is running on port 8888...");

            while (true) {
                SSLSocket socket = (SSLSocket) serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                String message = in.readLine();
                System.out.println("Received from client: " + message);

                out.println("Hello from server!");
                socket.close();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

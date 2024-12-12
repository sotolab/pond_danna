package socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        String host = "localhost"; // 호스트 주소
        int port = 3021; // 연결할 포트 번호

        try {
            System.out.println("Trying to connect to server at " + host + ":" + port);
            Socket clientSocket = new Socket(host, port); // 클라이언트 소켓 생성
            System.out.println("Connected to server");

            OutputStream out = clientSocket.getOutputStream();  // 클라이언트 소켓에서 출력 스트림을 얻어옴, 이는 서버로 데이터를 보내는 통로가 됨
            String message = "Hello, server!";
            out.write(message.getBytes()); // 문자열을 바이트 배열로 전환하여 출력 스트림을 통해 서버에 메시지 전송
            System.out.println("Sent message to server: " + message);

            InputStream in = clientSocket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = in.read(buffer);
            String response = new String(buffer, 0, bytesRead);
            System.out.println("Received response from server: " + response);

            clientSocket.close(); // 소켓 닫기
            System.out.println("Connection closed");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = 3021;
        ServerSocket serverSocket = new ServerSocket(port); // 서버 소켓 생성
        System.out.println("Server is waiting for client on port " + port);
        while (true) { // 서버를 계속 실행하면서 클라이언트 연결을 반복적으로 받음
            try {
                Socket clientSocket = serverSocket.accept(); // 클라이언트의 연결을 대기하고 수락, 연결되면 통신용 소켓 생성
                System.out.println("Client connected from: " + clientSocket.getInetAddress());

                InputStream in = clientSocket.getInputStream(); // 클라이언트로부터 데이터를 받기 위한 스트림
                OutputStream out = clientSocket.getOutputStream(); // 클라이언트에게 데이터를 보내기 위한 스트림

                byte[] buffer = new byte[1024]; // 데이터를 저장할 버퍼
                int bytesRead = in.read(buffer); // 클라이언트로부터 데이터 읽기
                String message = new String(buffer, 0, bytesRead); // 바이트 데이터를 문자열로 변환
                System.out.println("Received message from client: " + message);

                String responseMessage = "Hello, client!"; // 응답 메시지 준비
                out.write(responseMessage.getBytes()); // 클라이언트에게 응답 전송
                System.out.println("Sent response to client: " + responseMessage);

                clientSocket.close(); //클라이언트와 연결 종료
                System.out.println("Client connection closed");
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}

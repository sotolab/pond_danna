package socket;

import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        int port = 3022;

        try {
            // UDP 서버 소켓 생성
            System.out.println("===== UDP Server Start =====");
            DatagramSocket serverSocket = new DatagramSocket(port);
            System.out.println("Server is listening on port " + port);

            while (true) {
                // 클라이언트로부터 데이터를 받을 버퍼와 패킷 준비
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                // 클라이언트로부터 데이터 수신
                System.out.println("Waiting for client message...");
                serverSocket.receive(receivePacket);

                // 수신한 데이터 처리
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received message from client: '" + message + "'");

                // 클라이언트 정보 획득
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                System.out.println("Client address: " + clientAddress + ", port: " + clientPort);

                // 응답 메시지 전송
                String responseMessage = "Hello, Client!";
                byte[] sendBuffer = responseMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(
                        sendBuffer,
                        sendBuffer.length,
                        clientAddress,
                        clientPort
                );
                serverSocket.send(sendPacket);
                System.out.println("Response sent to client: '" + responseMessage + "'");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
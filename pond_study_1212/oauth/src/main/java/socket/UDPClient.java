package socket;

import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 3022;

        try {
            System.out.println("===== UDP Client Start =====");
            System.out.println("Attempting to communicate with server at " + host + ":" + port);

            DatagramSocket clientSocket = new DatagramSocket(); // UDP 통신을 위한 데이터그램 소켓 생성
            System.out.println("UDP socket created successfully");

            String message = "Hello, Server!"; // 전송할 메시지 준비
            byte[] buffer = message.getBytes(); // 문자열 바이트 배열로 변환
            InetAddress address = InetAddress.getByName(host); // 서버 주소 생성
            System.out.println("Preparing to send message: '" + message + "'");

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port); // 전송할 UDP 패킷 생성
            clientSocket.send(packet); // 패킷 전송
            System.out.println("Message sent to server");

            System.out.println("Waiting for server response...");
            buffer = new byte[1024]; // 서버로부터 응답을 받기 위한 새로운 버퍼와 패킷 준비
            packet = new DatagramPacket(buffer, buffer.length);

            clientSocket.receive(packet); // 서버로부터 응답 수신
            message = new String(packet.getData(), 0, packet.getLength()); // 수신한 데이터를 문자열로 변환
            System.out.println("Recieved message from server: " + message);

            clientSocket.close(); // 소켓 종료
            System.out.println("Connection closed");
            System.out.println("===== UDP Client End =====");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

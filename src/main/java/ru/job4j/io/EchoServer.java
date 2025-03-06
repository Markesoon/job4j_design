package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    output.write(("Hello\r\n\r\n".getBytes()));
                    String string = input.readLine();
                        if (string.contains("msg=What")) {
                            output.write("What\r\n\r\n".getBytes());
                            System.out.println(string);
                        }
                    if (string.contains("msg=Exit")) {
                        output.write("Завершить работу сервера\r\n\r\n".getBytes());
                        server.close();
                    }
                    output.flush();
                }
            }
        }
    }

}
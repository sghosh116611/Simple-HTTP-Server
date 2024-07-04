package com.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

	public static void main(String[] args) throws IOException {
		try (ServerSocket server = new ServerSocket(8080)) {
			System.out.println("Listening on port 8080");

			while (true) {
				try (Socket socket = server.accept()) {

					InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
					BufferedReader br = new BufferedReader(streamReader);

					String line = br.readLine();

					while (line != null && !line.isEmpty()) {
						System.out.println(line);
						line = br.readLine();
					}
					Date today = new Date();
					String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
					socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
				}
			}

		}
	}
}

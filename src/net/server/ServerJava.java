package net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerJava implements Runnable {

	private int port;
	private ProtocolJava protocol;
	private ServerSocket serverSocket;
	private ExecutorService executor = Executors.newFixedThreadPool(5);
	public static volatile boolean closedFlag = true;

	public ServerJava(int port, ProtocolJava protocol) {
		super();
		this.port = port;
		this.protocol = protocol;
		try {
			this.serverSocket = new ServerSocket(this.port);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void run() {
		System.out.println("Welcome to the RentCars server");
		try {
			while(closedFlag) {
				try {
					Socket socket = serverSocket.accept();
					ServerClientJava serverClient =	new ServerClientJava(socket, protocol);
					executor.execute(serverClient);
				} catch (SocketTimeoutException e) {}
			}
			executor.shutdown();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}

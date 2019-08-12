package net.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;

import net.RequestJava;

public class ServerClientJava implements Runnable {
 
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Socket socket;
	private ProtocolJava protocol;
	
	public ServerClientJava(Socket socket, ProtocolJava protocol) {
		super();
		this.socket = socket;
		this.protocol = protocol;
		try {
			this.input = new ObjectInputStream(socket.getInputStream());
			this.output = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void run() {
		while(true) {
			try {
				RequestJava request = (RequestJava)	input.readObject();
				output.writeObject(protocol.getResponse(request));
				output.reset();
			}
			catch (SocketTimeoutException e) {}
			catch (EOFException e) {
				System.err.println(LocalDateTime.now() + ": Client closed connection");
				break;
			} catch (Exception e) {
				System.err.println(LocalDateTime.now() + ": ERROR - " + e.getMessage());
				break;
			}
		}
		try {
			socket.close();
		} catch (IOException e) {
			System.err.println(LocalDateTime.now() + ": Socket already closed by client");
		}
	}
}

package net;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import client.config.Config_cl;

public class TCPClientJava extends Socket implements Closeable {

	protected ObjectOutputStream output;
	protected ObjectInputStream input;
	private Socket socket;
	
	public TCPClientJava(String hostName, int port) {
		super();
		try {
				this.socket = new Socket(Config_cl.CLIENT_HOST, Config_cl.CLIENT_PORT);
				this.output = new ObjectOutputStream(socket.getOutputStream());
				this.input = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	protected <T> T sendRequest(String requestType, Serializable requestData) {
		try {
			RequestJava request = new RequestJava(requestType, requestData);
			output.writeObject(request);
			ResponseJava response = (ResponseJava) input.readObject();
			if(response.code != TcpResponseCode.OK)
				throw new Exception(response.code.toString());
			return (T) response.responseData;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	@Override
	public void close() throws IOException {
		socket.close();
	}

}

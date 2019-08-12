package net;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ResponseJava implements Serializable {

	public TcpResponseCode code;
	public Serializable responseData;
	
	public ResponseJava(TcpResponseCode code, Serializable responseData) {
		super();
		this.code = code;
		this.responseData = responseData;
	}
}

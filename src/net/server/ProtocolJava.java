package net.server;

import net.RequestJava;
import net.ResponseJava;

public interface ProtocolJava {

	ResponseJava getResponse(RequestJava request);
	
}

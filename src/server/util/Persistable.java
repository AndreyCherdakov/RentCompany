package server.util;

import java.io.Serializable;

public interface Persistable extends Serializable {

	public void save(String fileName);
	
}

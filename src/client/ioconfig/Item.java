package client.ioconfig;

public interface Item {
	
	public String displayedName();
	public void perform();
	default boolean isExit() {
		return false;
	}

}

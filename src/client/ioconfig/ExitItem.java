package client.ioconfig;

public class ExitItem implements Item {

	@Override
	public String displayedName() {
		return "\tExit";
	}

	@Override
	public void perform() {

	}
	@Override
	public boolean isExit() {
		return true;
	}

}

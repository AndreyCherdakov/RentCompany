package client.items;

import client.ioconfig.InputOutput;
import client.requests.IRentCompany;
import client.requests.Persistable;

public class SaveAndExitItem extends RentCompanyItem {

	String fileName;

	public SaveAndExitItem(InputOutput inputOutput, IRentCompany company, String fileName) {
		super(inputOutput, company);
		this.fileName = fileName;
	}

	@Override
	public String displayedName() {
		return "\tSave and Exit";
	}

	@Override
	public void perform() {
		((Persistable)company).save(fileName);

	}
	@Override
	public boolean isExit() {
		return true;
	}

}

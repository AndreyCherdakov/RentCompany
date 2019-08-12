package client.items.manager;

import client.config.Config_cl;
import client.ioconfig.InputOutput;
import client.items.RentCompanyItem;
import client.requests.IRentCompany;

public class RemoveCarItem extends RentCompanyItem {

	public RemoveCarItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "\tRemove car";
	}

	@Override
	public void perform() {
		String regNumber = getRegNumberExisted();
		if(regNumber == null) {
			inputOutput.outputErrorLine(Config_cl.CANCEL);
			return;
		}
		inputOutput.outputLine(company.removeCar(regNumber));

	}

}

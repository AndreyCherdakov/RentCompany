package client.items.driver;

import client.config.Config_cl;
import client.ioconfig.InputOutput;
import client.items.RentCompanyItem;
import client.requests.IRentCompany;

public class GetCarItem extends RentCompanyItem {

	public GetCarItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "\tDisplay car data";
	}

	@Override
	public void perform() {
		String regNumber = getRegNumberExisted();
		if(regNumber == null) {
			inputOutput.outputErrorLine(Config_cl.CANCEL);
			return;
		}
		inputOutput.outputLine(company.getCar(regNumber));

	}
}

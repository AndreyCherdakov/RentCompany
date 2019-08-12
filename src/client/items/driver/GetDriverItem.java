package client.items.driver;

import client.config.Config_cl;
import client.ioconfig.InputOutput;
import client.items.RentCompanyItem;
import client.requests.IRentCompany;

public class GetDriverItem extends RentCompanyItem {

	public GetDriverItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "\tDisplay drivers data";
	}

	@Override
	public void perform() {
		Long licenseId = getLicenseIdExisted();
		if(licenseId == null) {
			inputOutput.outputErrorLine(Config_cl.CANCEL);
			return;
		}
		inputOutput.outputLine(company.getDriver(licenseId));

	}

	

}

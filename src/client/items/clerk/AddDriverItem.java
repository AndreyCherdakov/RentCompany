package client.items.clerk;

import client.config.Config_cl;
import client.ioconfig.InputOutput;
import client.items.RentCompanyItem;
import dto.Driver;
import client.requests.IRentCompany;

public class AddDriverItem extends RentCompanyItem {

	public AddDriverItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "\tAdd new driver";
	}

	@Override
	public void perform() {
			Long licenseId = getLicenseIdNotExisted();
			if(licenseId == null) {
				errorOutput();
				return;
			}
			String name = inputOutput.inputString("Enter driver name");
			if(name == null) {
				errorOutput();
				return;
			}
			Integer birthYear=inputOutput.inputInteger("Enter driver birth year", 1940, 2001);
			if(birthYear == null) {
				errorOutput();
				return;
			}
			String phone = inputOutput.inputStringByPattrn("Enter driver phone number (Phone number format '05x-xxxxxxx')", Config_cl.PHONE_PATTERN);
			if (phone == null) {
				errorOutput();
				return;
			}
			inputOutput.outputLine(company.addDriver(new Driver(licenseId, name, birthYear, phone)));

	}

	protected void errorOutput() {
		inputOutput.outputErrorLine(Config_cl.CANCEL);
	}

}

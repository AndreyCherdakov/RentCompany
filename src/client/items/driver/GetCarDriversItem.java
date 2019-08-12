package client.items.driver;

import client.config.Config_cl;
import client.ioconfig.InputOutput;
import client.items.RentCompanyItem;
import client.requests.IRentCompany;

public class GetCarDriversItem extends RentCompanyItem {

	public GetCarDriversItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "\tDisplay drivers by car";
	}

	@Override
	public void perform() {
		String regNumber = getRegNumberExisted();
		if(regNumber == null) {
			inputOutput.outputErrorLine(Config_cl.CANCEL);
			return;
		}
		if(company.getDriversCar(regNumber).isEmpty()) {
			inputOutput.outputErrorLine("No drivers of car with registration number " + regNumber);
			return;
		}
		company.getDriversCar(regNumber).forEach(inputOutput::outputLine);

	}

}

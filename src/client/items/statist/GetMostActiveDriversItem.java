package client.items.statist;

import client.ioconfig.InputOutput;
import client.items.RentCompanyItem;
import client.requests.IRentCompany;

public class GetMostActiveDriversItem extends RentCompanyItem {

	public GetMostActiveDriversItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "\tDisplay most active drivers";
	}

	@Override
	public void perform() {
		if(company.getMostActiveDrivers().isEmpty()) {
			inputOutput.outputErrorLine("Drivers not found");
			return;
		}
		company.getMostActiveDrivers().forEach(inputOutput::outputLine);
	}

}

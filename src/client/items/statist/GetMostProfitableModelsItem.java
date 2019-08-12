package client.items.statist;

import client.ioconfig.InputOutput;
import client.items.RentCompanyItem;
import client.requests.IRentCompany;

public class GetMostProfitableModelsItem extends RentCompanyItem {

	public GetMostProfitableModelsItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "\tMost profitable model names";
	}

	@Override
	public void perform() {
		fillFromToDates();
		company.getMostProfitableCarModels(fromDate, toDate).forEach(inputOutput::outputLine);
	}

}

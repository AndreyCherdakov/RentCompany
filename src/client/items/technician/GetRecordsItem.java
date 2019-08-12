package client.items.technician;

import client.config.Config_cl;
import client.ioconfig.InputOutput;
import client.items.RentCompanyItem;
import client.requests.IRentCompany;

public class GetRecordsItem extends RentCompanyItem {

	public GetRecordsItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "\tDisplay records";
	}

	@Override
	public void perform() {
		fillFromToDates();
		if(fromDate == null || toDate == null) {
			inputOutput.outputErrorLine(Config_cl.CANCEL);
			return;
		}
		if(company.getRentRecordsAtDates(fromDate, toDate).isEmpty()) {
			inputOutput.outputErrorLine("Records not found");
			return;
		}
		company.getRentRecordsAtDates(fromDate, toDate).forEach(inputOutput::outputLine);

	}

}

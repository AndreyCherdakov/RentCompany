package client.items.statist;

import java.util.List;

import client.config.Config_cl;
import client.ioconfig.InputOutput;
import client.items.RentCompanyItem;
import client.requests.IRentCompany;

public class GetMostPopularModelsItem extends RentCompanyItem {

	public GetMostPopularModelsItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "\tDisplay most popular model names";
	}

	@Override
	public void perform() {
		fillFromToDates();
		if(fromDate == null || toDate == null) {
			errorOutput();
			return;
		}
		Integer fromAge = inputOutput.inputInteger(String.format("Enter age FROM [%d - %d]", Config_cl.AGE_MIN, Config_cl.AGE_MAX), Config_cl.AGE_MIN, Config_cl.AGE_MAX);
		if(fromAge == null) {
			errorOutput();
			return;
		}
		Integer toAge = inputOutput.inputInteger(String.format("Enter age TO [%d - %d]", Config_cl.AGE_MIN, Config_cl.AGE_MAX), Config_cl.AGE_MIN, Config_cl.AGE_MAX);
		if(toAge == null) {
			errorOutput();
			return;
		}
		if(fromAge > toAge) {
			inputOutput.outputErrorLine("FromAge can not be greater than ToAge" );
			return;
		}
		List<String> models = company.getMostPopularCarModels(fromDate, toDate, fromAge, toAge);
		if(models.isEmpty()) {
			inputOutput.outputErrorLine("Models not found");
			return;
		}
		models.forEach(inputOutput::outputLine);
	}
	
	protected void errorOutput() {
		inputOutput.outputErrorLine(Config_cl.CANCEL);
	}

}

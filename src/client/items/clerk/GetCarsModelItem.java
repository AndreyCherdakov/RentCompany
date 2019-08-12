package client.items.clerk;

import java.time.LocalDateTime;
import java.util.List;

import client.config.Config_cl;
import client.ioconfig.InputOutput;
import client.items.RentCompanyItem;
import client.requests.IRentCompany;

public class GetCarsModelItem extends RentCompanyItem {
	
	public GetCarsModelItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "\tDisplay cars by model";
	}

	@Override
	public void perform() {
		List<String> modelNames = company.getModelNames();
		String modelName = inputOutput.inputString("Enter model name" + modelNames,	modelNames);
		if(modelName == null) {
			errorOutput();
			return;
		}
		
		if(company.getCarsModel(modelName).isEmpty()) {
			inputOutput.outputErrorLine(LocalDateTime.now() + ": ERROR - No cars of " + modelName);
			return;
		}
		company.getCarsModel(modelName).forEach(inputOutput::outputLine);

	}
	
	protected void errorOutput() {
		inputOutput.outputErrorLine(Config_cl.CANCEL);
	}

}

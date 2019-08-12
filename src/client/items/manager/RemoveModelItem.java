package client.items.manager;

import java.util.List;

import client.config.Config_cl;
import client.ioconfig.InputOutput;
import client.items.RentCompanyItem;
import client.requests.IRentCompany;

public class RemoveModelItem extends RentCompanyItem {

	public RemoveModelItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "\tRemove model";
	}

	@Override
	public void perform() {
		
		List<String> models = company.getModelNames();
		String modelName = inputOutput.inputString("Enter model name" + models, models);
		if(modelName == null) {
			inputOutput.outputErrorLine(Config_cl.CANCEL);
			return;
		}
		company
			.removeModel(modelName)
			.forEach(inputOutput::outputLine);
	}

}

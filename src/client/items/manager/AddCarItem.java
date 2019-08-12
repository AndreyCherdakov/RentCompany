package client.items.manager;

import java.util.List;

import client.config.Config_cl;
import client.ioconfig.InputOutput;
import client.items.RentCompanyItem;
import dto.Car;
import client.requests.IRentCompany;

public class AddCarItem extends RentCompanyItem {
	
	public AddCarItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "\tAdd new car";
	}

	@Override
	public void perform() {
		String regNumber = getRegNumberNotExisted();
		if(regNumber == null) {
			errorOutput();
			return;
		}
		String color = inputOutput.inputString("Enter color");
		List<String> models = company.getModelNames();
		String modelName = inputOutput.inputString("Enter model name from list " + models, models);
		if(modelName == null) {
			errorOutput();
			return;
		}
		
		Car car = new Car(regNumber, color, modelName);
		inputOutput.outputLine(company.addCar(car));

	}

	protected void errorOutput() {
		inputOutput.outputErrorLine(Config_cl.CANCEL);
	}

}

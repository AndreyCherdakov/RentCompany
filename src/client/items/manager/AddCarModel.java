package client.items.manager;

import client.config.Config_cl;
import client.ioconfig.InputOutput;
import client.items.RentCompanyItem;
import dto.Model;
import client.requests.IRentCompany;

public class AddCarModel extends RentCompanyItem {

	public AddCarModel(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "\tAdd car model";
	}

	@Override
	public void perform() {
		String modelName = inputOutput.inputString("Enter model name");
		if(modelName == null) {
			errorOutput();
			return;
		}
		
		Integer gasTank=inputOutput.inputInteger(String.format("Enter gas tank volume [interval %d - %d]", Config_cl.MIN_TANK, Config_cl.MAX_TANK), Config_cl.MIN_TANK, Config_cl.MAX_TANK);
		if(gasTank == null) {
			errorOutput();
			return;
		}
		String companyName = inputOutput.inputString("Enter company name");
		if(companyName == null) {
			errorOutput();
			return;
		}
		String country=inputOutput.inputString("Enter country");
		if(country == null) {
			errorOutput();
			return;
		}
		Integer priceDay = inputOutput.inputInteger(String.format("Enter price day [interval %d - %d]", Config_cl.PRICE_MIN, Config_cl.PRICE_MAX), Config_cl.PRICE_MIN, Config_cl.PRICE_MAX);
		if(priceDay == null) {
			errorOutput();
			return;
		}
		Model model = new Model(modelName, gasTank, companyName, country, priceDay);
		inputOutput.outputLine(company.addModel(model));

	}

	protected void errorOutput() {
		inputOutput.outputErrorLine(Config_cl.CANCEL);
	}
}

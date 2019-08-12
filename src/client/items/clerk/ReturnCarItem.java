package client.items.clerk;

import java.time.LocalDate;
import client.config.Config_cl;
import client.ioconfig.InputOutput;
import client.items.RentCompanyItem;
import client.requests.IRentCompany;

public class ReturnCarItem extends RentCompanyItem {
	
	public ReturnCarItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "\tReturn of the rented car";
	}

	@Override
	public void perform() {
		String regNumber = getRegNumberExisted();
		if(regNumber == null) {
			errorOutput();
			return;
		}
		Long licenseId = getLicenseIdExisted();
		if(licenseId == null) {
			errorOutput();
			return;
		}
		LocalDate returnDate = inputOutput.inputDate("Enter return date " + Config_cl.DATE_FORMAT, Config_cl.DATE_FORMAT);
		if(returnDate == null) {
			errorOutput();
			return;
		}
		Integer damages = inputOutput.inputInteger(String.format("Enter percent of damages [%d - %d]", Config_cl.DAMAGE_MIN, Config_cl.DAMAGE_MAX), Config_cl.DAMAGE_MIN, Config_cl.DAMAGE_MAX);
		if(damages == null) {
			errorOutput();
			return;
		}
		Integer tankPercent = inputOutput.inputInteger(String.format("Enter tank percent [%d - %d]", Config_cl.TANK_PERCENT_MIN, Config_cl.TANK_PERCENT_MAX), Config_cl.TANK_PERCENT_MIN, Config_cl.TANK_PERCENT_MAX);
		if(tankPercent == null) {
			errorOutput();
			return;
		}
		inputOutput.outputLine(company.returnCar(
						regNumber,
						licenseId,
						returnDate,
						damages, 
						tankPercent));

	}
	
	protected void errorOutput() {
		inputOutput.outputErrorLine(Config_cl.CANCEL);
	}

}

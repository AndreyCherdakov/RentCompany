package client.items.clerk;

import java.time.LocalDate;
import java.time.LocalDateTime;

import client.config.Config_cl;
import client.ioconfig.InputOutput;
import client.items.RentCompanyItem;
import client.requests.IRentCompany;

public class RentCarItem extends RentCompanyItem {

	public RentCarItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "\tRent a car";
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
		LocalDate rentDate = inputOutput.inputDate("Enter rent date [Format " + Config_cl.DATE_FORMAT + "]", Config_cl.DATE_FORMAT);
		if(rentDate == null) {
			errorOutput();
			return;
		}
		Integer rentDays = inputOutput.inputInteger(String.format("Enter rent days [%d - %d]", Config_cl.RENT_DAYS_MIN, Config_cl.RENT_DAYS_MAX), Config_cl.RENT_DAYS_MIN, Config_cl.RENT_DAYS_MAX);
		
		inputOutput.outputLine(company.rentCar(regNumber, licenseId, rentDate, rentDays));

	}
	
	protected void errorOutput() {
		inputOutput.outputErrorLine(Config_cl.CANCEL);
	}
	
	protected LocalDate fromDate;
	protected LocalDate toDate;
	protected void fillFromToDates() {
		
		fromDate = inputOutput.inputDate("Enter pick date from", Config_cl.DATE_FORMAT);
		if(fromDate == null) {
			errorOutput();
			return;
		}
		toDate = inputOutput.inputDate("Enter pick date to", Config_cl.DATE_FORMAT);
		if(toDate == null) {
			errorOutput();
			return;
		}
		if(fromDate.isAfter(toDate)) {
			inputOutput.outputErrorLine(LocalDateTime.now() + ": ERROR - Date From can't be after date To");
			fromDate = toDate = null;
		}
		
	}

}

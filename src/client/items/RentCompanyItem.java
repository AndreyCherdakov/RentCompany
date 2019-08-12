package client.items;

import client.requests.IRentCompany;
import java.time.LocalDate;
import client.config.Config_cl;
import client.ioconfig.*;

public abstract class RentCompanyItem implements Item{

	protected InputOutput inputOutput;
	protected IRentCompany company;

	public RentCompanyItem(InputOutput inputOutput, IRentCompany company) {
		super();
		this.inputOutput = inputOutput;
		this.company = company;
	}
	
	protected String getRegNumberNotExisted() {
		String regNumber = inputOutput.inputString("Enter new car registration number");
		if(regNumber == null) {
			errorCanceled();
			return null;
		}
		if(company.getCar(regNumber) != null) {
			inputOutput.outputErrorLine("Car already exists");
			return null;
		}
		return regNumber;
	}
	
	protected Long getLicenseIdNotExisted() {
		Long licenseId = inputOutput.inputLongByPattern("Enter new license id (13 digits)", Config_cl.LICENSEID_FORMAT);
		if(licenseId == null) {
			errorCanceled();
			return null;
		}
		if(company.getDriver(licenseId) != null) {
			inputOutput.outputErrorLine("Driver already exists");
			return null;
		}
		return licenseId;
	}

	protected Long getLicenseIdExisted() {
		Long licenseId = inputOutput.inputLongByPattern("Enter license id", Config_cl.LICENSEID_FORMAT);
		if(licenseId == null) {
			errorCanceled();
			return null;
		}
		if(company.getDriver(licenseId) == null) {
			inputOutput.outputErrorLine("Driver not found");
			return null;
		}
		return licenseId;
	}

	protected String getRegNumberExisted() {
		String regNumber = inputOutput.inputString("Enter registration number");
		if(regNumber == null) {
			errorCanceled();
			return null;
		}
		if(company.getCar(regNumber) == null) {
			inputOutput.outputErrorLine("Car not found");
			return null;
		}
		return regNumber;
	}

	protected LocalDate fromDate;
	protected LocalDate toDate;
	
	protected void fillFromToDates() {
		fromDate = inputOutput.inputDate("Enter rent date FROM", Config_cl.DATE_FORMAT);
		if(fromDate == null) {
			errorCanceled();
			return;
		}
		toDate = inputOutput.inputDate("Enter rent date TO", Config_cl.DATE_FORMAT);
		if(toDate == null) {
			errorCanceled();
			return;
		}
		if(fromDate.isAfter(toDate)) {
			inputOutput.outputErrorLine("Date FROM can not be after date TO");
			fromDate = toDate = null;
		}
	}
	
	protected void errorCanceled() {
		inputOutput.outputErrorLine(Config_cl.CANCEL);
	}
	
}

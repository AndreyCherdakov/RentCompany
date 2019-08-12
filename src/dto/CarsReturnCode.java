package dto;

import java.io.Serializable;

public enum CarsReturnCode implements Serializable {

	OK ("Ok"),
	MODEL_EXISTS ("Model exists"),
	NO_MODEL ("No model"),
	MODEL_REMOVED ("Model removed"),
	CAR_EXISTS ("Car exists"),
	NO_CAR ("No car"),
	DRIVER_EXISTS ("Driver exists"),
	NO_DRIVER ("No driver"),
	CAR_REMOVED ("Car removed"),
	CAR_IN_USE ("Car in use"),
	RENT_DAYS_INVALID ("Rent days is invalid"),
	RENT_DATE_INVALID ("Rent date is invalid"),
	RETURN_DATE_INVALID ("Return date is invalid");
	
	private String value;
	
	private CarsReturnCode(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}

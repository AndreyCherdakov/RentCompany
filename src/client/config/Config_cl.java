package client.config;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class Config_cl {

	public static final int LICENSE_ID_NUMBER_OF_DIGITS = 13;
	public static final Pattern PHONE_PATTERN = Pattern.compile("05\\d-\\d{7}");
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final Pattern LICENSEID_FORMAT = Pattern.compile("\\d{13}");
	public static final int MIN_TANK = 35;
	public static final int MAX_TANK = 60;
	public static final String CANCEL = LocalDateTime.now() + ": canceled";
	public static final String CLIENT_HOST = "localhost";
	public static final int CLIENT_PORT = 3000;
	public static final String NOT_FOUND = LocalDateTime.now() + ": not found in DataBase";
	public static final int PRICE_MIN = 50;
	public static final int PRICE_MAX = 1000;
	public static final int AGE_MIN = 18;
	public static final int AGE_MAX = 120;
	public static final int RENT_DAYS_MIN = 1;
	public static final int RENT_DAYS_MAX = 30;
	public static final int DAMAGE_MIN = 0;
	public static final int DAMAGE_MAX = 100;
	public static final int TANK_PERCENT_MIN = 0;
	public static final int TANK_PERCENT_MAX = 100;
	
}

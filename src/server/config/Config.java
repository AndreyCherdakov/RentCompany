package server.config;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class Config implements Serializable {

	public static final String ALPHABET_WORDS = "ABCEHKMOPTXY";
	public static final String ALPHABET_DIGITS = "0123456789";
	public static final int REGNUMBER_LENGTH = 8;
	public static final String[] COLORS = {
			"Red",
			"Blue",
			"Green",
			"White",
			"Black",
			"Purple",
			"Pink",
			"Yellow",
			"Braun",
			"Light Red",
			"Light Blue",
			"Light Green"
	};
	public static final String[] MODELS = {
			"Toyota",
			"Kia",
			"Mazda",
			"Mitsubishi",
			"Opel",
			"Nissan",
			"Honda",
			"Hundai",
			"Mercedes",
			"Buik",
			"Mini",
			"Subaru",
			"Lada",
			"Chevrolet",
			"Citroen",
			"Renault",
			"Audi",
			"BMW",
			"Land Rover",
			"Volvo",
			"Cadilak"
	};
	public static final double TRUE_PROBABILITY = 0.5;
	public static final int[] GAS_TANKS = {
			40,
			45,
			50,
			55,
			60
	};
	public static final String[] COMPANIES = {
		"General Mototrs",
		"Volkswagen Group",
		"AB SKF",
		"Groupe PSA",
		"Daimler AG",
		"Dacia"
	};
	public static final String[] COUNTRIES = {
			"France",
			"Germany",
			"Russia",
			"USA",
			"Poland",
			"Israel",
			"Egypt",
			"Portugal",
			"Austria",
			"Canada"
	};
	public static final int PRICE_PER_DAY_MIN = 10;
	public static final int PRICE_PER_DAY_MAX = 30;
	public static final int LICENSE_ID_NUMBER_OF_DIGITS = 13;
	public static final String[] DRIVER_NAMES = {
			"Andrey",
			"Oleg",
			"Valera",
			"Alexander",
			"Semen",
			"Mikhail",
			"Pavel",
			"Alexey",
			"Dima",
			"Nikolay",
			"Denis",
			"Vladimir",
			"Boris",
			"Uriy",
			"Vladislav",
			"Victor",
			"Evgeniy"
	};
	public static final int PHONE_NUMBER_OF_DIGITS = 7;
	public static final LocalDate DATE_MIN = LocalDate.now();
	public static final LocalDate DATE_MAX = LocalDate.of(DATE_MIN.getYear() + 1, DATE_MIN.getMonth(), DATE_MIN.getDayOfMonth());
	public static final int RENT_DAYS_MAX = 10;
	public static final int CAR_DAMAGES = 50;
	public static final String DATABASE_PATH = "../2019-07-31_RentCarsProject/src/server/database/RentCompany.dta";
	public static final int SERVER_PORT = 3000;
	public static final String SERVER_HOST = "localhost";
	public static final int NUMBER_OF_THREADS = 10;
	public static final long DELAY = 5;
}

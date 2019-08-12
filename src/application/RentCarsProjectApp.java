package application;

import server.config.Config;
import server.config.Randoms;
import server.model.RentCompanyEmbedded;

import java.time.LocalDate;

import dto.Car;
import dto.Driver;
import dto.Model;
import dto.RentRecord;
import dto.State;

public class RentCarsProjectApp {

	private static final int NUM_CARS = 100;
	private static final int NUM_MODELS = 5;
	private static final int NUM_DRIVERS = 5;
	private static final int NUM_CONTRACTS = 100;

	public static void main(String[] args) {

		RentCompanyEmbedded company = new RentCompanyEmbedded();
		
		System.out.println("=========== Add Random Models ===========");
		for (int i = 0; i < NUM_MODELS; i++) {
			System.out.println(company.addModel(randomModel()));
		}
		System.out.println("=========== Add Random Drivers ===========");
		for (int i = 0; i < NUM_DRIVERS; i++) {
			System.out.println(company.addDriver(randomDriver()));
		}
		System.out.println("=========== Add Random Cars ===========");
		for (int i = 0; i < NUM_CARS; i++) {
			System.out.println(company.addCar(randomCar()));
		}
		
		System.out.println("===============================");
		System.out.println("addModel: " + company.addModel(new Model(Config.MODELS[0], Config.GAS_TANKS[2], Config.COMPANIES[2], Config.COUNTRIES[2], 20)));
		System.out.println("addCar: " + company.addCar(new Car("T104XA77", Config.COLORS[0], Config.MODELS[0])));
		System.out.println("addCar: " + company.addCar(new Car("M111MM01", Config.COLORS[1], State.GOOD, Config.MODELS[0], false, true)));
		System.out.println("addCar: " + company.addCar(new Car("A111AA01", Config.COLORS[0], State.GOOD, Config.MODELS[0], true, false)));
		System.out.println("addCar: " + company.addCar(new Car("B111BB01", Config.COLORS[2], State.GOOD, Config.MODELS[0], true, true)));
		System.out.println("addDriver: " + company.addDriver(new Driver(1111111111111L, Config.DRIVER_NAMES[1], 1984, "053-5991568")));
		System.out.println("===============Repeat Add ================");
		System.out.println("addModel: " + company.addModel(new Model(Config.MODELS[0], Config.GAS_TANKS[2], Config.COMPANIES[2], Config.COUNTRIES[2], 20)));
		System.out.println("addCar: " + company.addCar(new Car("T104XA77", Config.COLORS[0], Config.MODELS[0])));
		System.out.println("addDriver: " + company.addDriver(new Driver(1111111111111L, Config.DRIVER_NAMES[1], 1984, "053-5991568")));
		
		System.out.println("========= All Cars ===========");
		for (Car c : company.getAllCars()) {
			System.out.println(c);
		}
		
		System.out.println("========= All Drivers ===========");
		for (Driver d : company.getAllDrivers()) {
			System.out.println(d);
		}
		
		System.out.println("========= All Models ===========");
		for (Model m : company.getAllModels()) {
			System.out.println(m);
		}
		
		System.out.println("================================");
		System.out.println("getModel: " + company.getModel("Toyota"));
		System.out.println("getCar: " + company.getCar("T104XA77"));
		System.out.println("getDriver: " + company.getDriver(1111111111111L));
		System.out.println("============== Get nulls ===============");
		System.out.println("getModel: " + company.getModel(null));
		System.out.println("getCar: " + company.getCar(null));
		System.out.println("getDriver: " + company.getDriver(-1));
		
		System.out.println("================= Sprint2 ====================");
		
		System.out.println("============== Rent Cars ====================");
		for (int i = 0; i < NUM_CONTRACTS; i++) {
		//	System.out.println("rentCar: " + 
					company.rentCar(
				company.getAllCars().get(Randoms.nextIntegerRange(0, company.getAllCars().size() - 1)).getRegNumber(),
				company.getAllDrivers().get(Randoms.nextIntegerRange(0, company.getAllDrivers().size() - 1)).getLicenseId(),
				Randoms.randomDate(Config.DATE_MIN, Config.DATE_MAX),
				Randoms.nextIntegerRange(0, Config.RENT_DAYS_MAX));
		//);
		}
		for (RentRecord	rr : company.getAllRents()) {
			System.out.println(rr);
		}
		
		System.out.println("==================================================");
		System.out.println("getDriversCar: " + company.getDriversCar("T104XA77"));
		System.out.println("==================================================");
		System.out.println("getCarsDriver: " + company.getCarsDriver(1111111111111L));
		System.out.println("==================================================");
		System.out.println("getCarsModel: " + company.getCarsModel("Toyota"));
		System.out.println("==================================================");
		System.out.println("getRentRecordsAtDates: " + company.getRentRecordsAtDates(LocalDate.now(), LocalDate.of(2019, 10, 30)));
		
		System.out.println("================= Sprint3 ====================");
		System.out.println("======================= removeCar ===========================");
		System.out.println("getCar: " + company.getCar("T104XA77"));
		System.out.println("removeCar: " + company.removeCarReturnCode("T104XA77"));
		System.out.println("getCar: " + company.getCar("M111MM01"));
		System.out.println("removeCar: " + company.removeCarReturnCode("M111MM01"));
		System.out.println("========================================");
		System.out.println("getAllCarsByModel: " + company.getCarsModel("Toyota"));
		System.out.println("removeModel: " + company.removeModel("Toyota"));
		System.out.println("========================================");
		System.out.println("addCar: " + company.addCar(new Car("C111CC01", Config.COLORS[0], State.GOOD, Config.MODELS[0], false, false)));
		System.out.println("addCar: " + company.addCar(new Car("E111EE01", Config.COLORS[0], State.EXCELLENT, Config.MODELS[1], false, false)));
		System.out.println("addCar: " + company.addCar(new Car("H111HH01", Config.COLORS[0], State.BAD, Config.MODELS[0], true, false)));
		System.out.println("addCar: " + company.addCar(new Car("K111KK01", Config.COLORS[0], State.BAD, Config.MODELS[0], false, true)));
		System.out.println("AddDriver: " + company.addDriver(new Driver(2222222222222L, Config.DRIVER_NAMES[0], 1984, "053-5991568")));
		System.out.println("rentCar: " + company.rentCar("C111CC01", 2222222222222L, LocalDate.now(), 5));
		System.out.println("rentCar: " + company.rentCar("E111EE01", 2222222222222L, LocalDate.now(), 5));
		System.out.println("rentCar: " + company.rentCar("H111HH01", 2222222222222L, LocalDate.now(), 5));
		System.out.println("rentCar: " + company.rentCar("K111KK01", 2222222222222L, LocalDate.now(), 5));
		System.out.println("returnCar: " + company.returnCar("C111CC01", 2222222222222L, LocalDate.of(2019, 8, 5), 11, 80));
		System.out.println("returnCar: " + company.returnCar("E111EE01", 2222222222222L, LocalDate.of(2019, 8, 15), 1, 50));
		System.out.println("returnCar: " + company.returnCar("H111HH01", 2222222222222L, LocalDate.of(2019, 8, 4), 5, 50));
		System.out.println("returnCar: " + company.returnCar("K111KK01", 2222222222222L, LocalDate.of(2019, 8, 4), 5, 50));
		
		System.out.println("================= Sprint3 ====================");
		System.out.println(company.getMostPopularCarModels(LocalDate.of(2019, 1, 1), LocalDate.of(2020, 1, 1), 30, 40));
		System.out.println("=====================");
		System.out.println(company.getMostProfitableCarModels(LocalDate.of(2019, 1, 1), LocalDate.of(2020, 1, 1)));
		System.out.println("=====================");
		System.out.println(company.getMostActiveDrivers());
		System.out.println("=====================");
		System.out.println(company.getModelNames());
		System.out.println("================");
		company.save(Config.DATABASE_PATH);
		System.out.println(company.getCar("C111CC01"));
	}
	
	public static Car randomCar() {

		return new Car(
				Randoms.nextRegNumber(),
				Randoms.nextStringFromSet(Config.COLORS),
				Randoms.nextState(),
				Randoms.nextStringFromSet(Config.MODELS),
				Randoms.nextBoolean(),
				Randoms.nextBoolean());
	}
	
	public static Model randomModel() {
		
		return new Model(
				Randoms.nextStringFromSet(Config.MODELS),
				Randoms.nextIntegerFromSet(Config.GAS_TANKS),
				Randoms.nextStringFromSet(Config.COMPANIES),
				Randoms.nextStringFromSet(Config.COUNTRIES),
				Randoms.nextIntegerRange(Config.PRICE_PER_DAY_MIN, Config.PRICE_PER_DAY_MAX));
	}
	
	public static Driver randomDriver() {
		
		return new Driver(
				Randoms.nextLongNumberOfDigits(Config.LICENSE_ID_NUMBER_OF_DIGITS),
				Randoms.nextStringFromSet(Config.DRIVER_NAMES),
				Randoms.nextIntegerRange(1970, 1990),
				Randoms.randomPhone());
	}
	
}

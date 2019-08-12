package server.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import dto.Car;
import dto.CarsReturnCode;
import dto.Driver;
import dto.Model;
import dto.RemovedCarData;
import dto.RentRecord;
import dto.State;
import server.config.Config;
import server.util.Persistable;

@SuppressWarnings("serial")
public class RentCompanyEmbedded extends AbstractRentCompany implements Persistable, Serializable {

	private HashMap<String, Car> cars = new HashMap<String, Car>();
	private HashMap<Long, Driver> drivers = new HashMap<Long, Driver>();
	private HashMap<String, Model> models = new HashMap<String, Model>();
	private HashMap<String, List<RentRecord>> carRecords = new HashMap<String, List<RentRecord>>();
	private HashMap<Long, List<RentRecord>> driverRecords = new HashMap<Long, List<RentRecord>>();
	private HashMap<String, List<Car>> modelRecords = new HashMap<String, List<Car>>();
	private TreeMap<LocalDate, List<RentRecord>> records = new TreeMap<LocalDate, List<RentRecord>>();
	
	public static IRentCompany restoreFromFile(String fileName) {
		try (
				ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName))
			){
				IRentCompany restoreDB = (IRentCompany) input.readObject();
				System.err.println(LocalDateTime.now() + ": DataBase restored succesful");
				return restoreDB;
		}catch(Exception e) {
			System.err.println(LocalDateTime.now() + ": ERROR - DataBase can not be restored\n" + e.getMessage());
			return new RentCompanyEmbedded();
		}
	}

	@Override
	public CarsReturnCode addModel(Model model) {
		if (models.containsKey(model.getModelName())) {
			return CarsReturnCode.MODEL_EXISTS;
		}
		models.put(model.getModelName(), model);
		return CarsReturnCode.OK;
	}

	@Override
	public CarsReturnCode addCar(Car car) {
		if (cars.containsKey(car.getRegNumber())) {
			return CarsReturnCode.CAR_EXISTS;
		}
		if (models.containsKey(car.getModelName())) {
			cars.put(car.getRegNumber(), car);
			List<Car> lc = new ArrayList<Car>();
			lc.add(car);
			if (modelRecords.containsKey(car.getModelName())) {
				modelRecords.get(car.getModelName()).addAll(lc);
			}
			else {
				modelRecords.put(car.getModelName(), lc);
			}
			return CarsReturnCode.OK;
		}
		return CarsReturnCode.NO_MODEL;
	}
	
	@Override
	public CarsReturnCode addDriver(Driver driver) {
		if (drivers.containsKey(driver.getLicenseId())) {
			return CarsReturnCode.DRIVER_EXISTS;
		}
		drivers.put(driver.getLicenseId(), driver);
		return CarsReturnCode.OK;
	}

	@Override
	public Model getModel(String modelName) {
		try {
			return models.get(modelName);
		} catch (Exception e) {
			return new Model();
		}
	}

	@Override
	public Car getCar(String regNumber) {
		try {
			return cars.get(regNumber);
		} catch (Exception e) {
			return new Car();
		}
	}

	@Override
	public Driver getDriver(long licenseId) {
		try {
			return drivers.get(licenseId);
		} catch (Exception e) {
			return new Driver();
		}
	}

//	Sprint2	
	
	//
	@Override
	public CarsReturnCode rentCar(String regNumber, long licenseId, LocalDate rentDate, int rentDays) {
		
		if (rentDays <= 0) {
			return CarsReturnCode.RENT_DAYS_INVALID;
		}
		if (!cars.containsKey(regNumber)) {
			return CarsReturnCode.NO_CAR;
		}
		if (!drivers.containsKey(licenseId)) {
			return CarsReturnCode.NO_DRIVER;
		}
		
		if (carRecords.containsKey(regNumber)) {
			long counter = carRecords
								.get(regNumber)
								.stream()
								.filter(rr -> (rentDate.toEpochDay() <= LocalDate.now().toEpochDay()) ||
										(rr.getReturnDate() != null) ||
										(rentDate.toEpochDay() >= rr.getRentDate().toEpochDay() && 
										rentDate.toEpochDay() < rr.getReturnDate().toEpochDay()))
								.count();
			if (counter > 0) {
				return CarsReturnCode.RENT_DATE_INVALID;
			}
		}
		
		RentRecord rr = new RentRecord(regNumber, licenseId, rentDate, rentDays);
		Car car = getCar(regNumber);
		
		if (car.isFlRemoved()) {
			return CarsReturnCode.CAR_REMOVED;
		}
		if (car.isInUse()) {
			return CarsReturnCode.CAR_IN_USE;
		}

		addElementToRecords(rr);
		addElementToCarRecords(rr);
		addElementToDriverRecords(rr);
		car.setInUse(true);
		return CarsReturnCode.OK;
	}
	
	private void addElementToRecords(RentRecord rr) {
		
		List<RentRecord> listRecords = new ArrayList<RentRecord>();
		
		listRecords.add(rr);
		if (records.containsKey(rr.getRentDate())) {
			records.get(rr.getRentDate()).addAll(listRecords);
		} else {
			records.put(rr.getRentDate(), listRecords);
		}
	}
	
	private void addElementToCarRecords(RentRecord rr) {
		
		List<RentRecord> listRecords = new ArrayList<RentRecord>();
		
		listRecords.add(rr);
		if (carRecords.containsKey(rr.getRegNumber())) {
			carRecords.get(rr.getRegNumber()).addAll(listRecords);
		} else {
			carRecords.put(rr.getRegNumber(), listRecords);
		}
	}
	
	private void addElementToDriverRecords(RentRecord rr) {
		
		List<RentRecord> listRecords = new ArrayList<RentRecord>();
		
		listRecords.add(rr);
		if (driverRecords.containsKey(rr.getLicenseId())) {
			driverRecords.get(rr.getLicenseId()).addAll(listRecords);
		} else {
			driverRecords.put(rr.getLicenseId(), listRecords);
		}
	}

	@Override
	public List<Car> getCarsDriver(long licenseId) {
		try {
			return driverRecords
					.get(licenseId)
					.stream()
					.map((RentRecord rr) -> cars.get(rr.getRegNumber()))
					.distinct()
					.collect(Collectors.toList());
		} catch (Exception e) {
			return new ArrayList<Car>();
		}
	}

	@Override
	public List<Driver> getDriversCar(String regNumber) {
		try {
			return carRecords
					.get(regNumber)
					.stream()
					.peek(System.out :: println)
					.map((RentRecord rr) -> drivers.get(rr.getLicenseId()))
					.distinct()
					.collect(Collectors.toList());
		} catch (Exception e) {
			return new ArrayList<Driver>();
		}
	}

	@Override
	public List<Car> getCarsModel(String modelName) {
		try {
			return modelRecords
					.get(modelName);
		} catch (Exception e) {
			return new ArrayList<Car>();
		}
	}

	@Override
	public List<RentRecord> getRentRecordsAtDates(LocalDate from, LocalDate to) {
		try {
			return records
					.entrySet()
					.stream()
					.filter((ss) -> ss.getKey().toEpochDay() >= from.toEpochDay() && ss.getKey().toEpochDay() < to.toEpochDay())
					.flatMap((ss) -> ss.getValue().stream())
					.collect(Collectors.toList());
		} catch (Exception e) {
			return new ArrayList<RentRecord>();
		}
	}

// Sprint3	
	
	@Override
	public RemovedCarData removeCar(String regNumber) {
		
		Car removedCar = new Car();
		
		try {
			removedCar = getCar(regNumber);
		} catch (Exception e) {
			return new RemovedCarData();
		}
		
		if (removedCar.isFlRemoved()) {
			return new RemovedCarData();
		}
		removedCar.setFlRemoved(true);
		
		return new RemovedCarData(removedCar, carRecords.get(regNumber));
	}
	
	public CarsReturnCode removeCarReturnCode(String regNumber) {
		if (!cars.containsKey(regNumber)) {
			return CarsReturnCode.NO_CAR;
		}
		return removeCar(regNumber) == null ? CarsReturnCode.CAR_REMOVED : CarsReturnCode.OK;
	}

	@Override
	public List<RemovedCarData> removeModel(String modelName) {
		
		List<Car> carsByModel = getCarsModel(modelName);
		
		if (carsByModel.isEmpty()) {
			return new ArrayList<RemovedCarData>();
		}
		
		List<RemovedCarData> removedCars = new ArrayList<RemovedCarData>();
		
		for (Car c : carsByModel) {
			c.setFlRemoved(true);
			removedCars.add(new RemovedCarData(c, carRecords.get(c.getRegNumber())));
		}
		
		return removedCars;
	}
	
	public CarsReturnCode removeModelReturnCode(String modelName) {
		if (!models.containsKey(modelName)) {
			return CarsReturnCode.NO_MODEL;
		}
		return removeModel(modelName).isEmpty() ? CarsReturnCode.CAR_REMOVED : CarsReturnCode.OK;
	}

	@Override
	public RemovedCarData returnCar(String regNumber, long licenseId, LocalDate returnDate, int damages, int tankPersent) {
		
		List<RentRecord> lrr = carRecords.get(regNumber);
		if (lrr == null) {
			return new RemovedCarData();
		}
		
		Car car = getCar(regNumber);
		List<RentRecord> newRR = new ArrayList<RentRecord>();
		LocalDate rentDate = null;
		
		for (RentRecord rr : lrr) {
			if (rr.getLicenseId() == licenseId) {
				if (checkReturnDate(rr, returnDate)) {
					return new RemovedCarData();
				}
				changeDamages(rr, damages);
				updateRentRecord(rr, returnDate, damages, tankPersent);
				rentDate = rr.getRentDate();
			}
			newRR.add(rr);
		}
		
		car.setInUse(false);
		carRecords.replace(regNumber, newRR);
		driverRecords.replace(licenseId, newRR);
		records.replace(rentDate, newRR);
		
		return new RemovedCarData(car, newRR);
	}
	
	public CarsReturnCode returnCarReturnCode(String regNumber, long licenseId, LocalDate returnDate, int damages, int tankPersent) {
		if (!cars.containsKey(regNumber)) {
			return CarsReturnCode.NO_CAR;
		}
		if (!drivers.containsKey(licenseId)) {
			return CarsReturnCode.NO_DRIVER;
		}
		return returnCar(regNumber, licenseId, returnDate, damages, tankPersent) == null ? CarsReturnCode.NO_CAR : CarsReturnCode.OK;
	}
	
	private boolean checkReturnDate(RentRecord rr, LocalDate returnDate) {
		return rr.getReturnDate() != null || returnDate.toEpochDay() <= rr.getRentDate().toEpochDay();
	}
	
	private void updateRentRecord(RentRecord rr, LocalDate returnDate, int damages, int tankPersent) {
		
			rr.setDamages(damages);
			rr.setReturnDate(returnDate);
			rr.setTankPersent(tankPersent);
			rr.setCost(sumRent(rr, returnDate) + sumFinesForDelay(rr, returnDate) + sumFinesForGas(rr, tankPersent));
	}
	
	private double sumRent(RentRecord rr, LocalDate returnDate) {
		return (returnDate.toEpochDay() - rr.getRentDate().toEpochDay()) * getModel(getCar(rr.getRegNumber()).getModelName()).getPricePerDay();
	}
	
	private double sumFinesForDelay(RentRecord rr, LocalDate returnDate) {
		long finesDays = returnDate.toEpochDay() - (rr.getRentDate().toEpochDay() + rr.getRentDays());
		return finesDays > 0 ? finesDays * getFinePercent() : 0;
	}
	
	private double sumFinesForGas (RentRecord rr, int tankPersent) {
		int gasTank = getModel(getCar(rr.getRegNumber()).getModelName()).getGasTank();
		return (gasTank - gasTank / 100 * tankPersent) * getGasPrice();
	}
	
	private void changeDamages(RentRecord rr, int damages) {
		Car car = getCar(rr.getRegNumber());
		if (rr.getDamages() < damages) {
			if (damages >= Config.CAR_DAMAGES) {
				car.setState(State.BAD);
			}
			else {
				car.setState(State.GOOD);
			}
		}
	}

//	Sprint4	
	
	@Override
	public List<String> getMostPopularCarModels(LocalDate dataFrom, LocalDate dataTo, int ageFrom, int ageTo) {
		List<RentRecord> lrr = getRentRecordsAtDates(dataFrom, dataTo);
		if (lrr.isEmpty()) {
			return new ArrayList<String>();
		}
		
		Map<String, Long> mostPopular = lrr
				.stream()
				.filter(rr -> driverAge(rr, ageFrom, ageTo))
				.collect(Collectors.groupingBy(
						rr -> getCar(rr.getRegNumber()).getModelName(),
						() -> new TreeMap<String, Long>(),
						Collectors.counting())
						);
		if (mostPopular.isEmpty()) {
			return new ArrayList<String>();
		}
		
		return mostPopular
				.entrySet()
				.stream()
				.sorted((entry1, entry2) -> 
					Long.compare(entry2.getValue(), entry1.getValue()) == 0 ?
							entry1.getKey().compareTo(entry2.getKey()) :
								Long.compare(entry2.getValue(), entry1.getValue()))
				.map(entry -> entry.getKey())
				.collect(Collectors.toList());
	}
	
	private boolean driverAge(RentRecord rr, int ageFrom, int ageTo) {
		int driverAge = LocalDate.now().getYear() - getDriver(rr.getLicenseId()).getBirthYear();
		return driverAge >= ageFrom && driverAge < ageTo;
	}

	@Override
	public List<String> getMostProfitableCarModels(LocalDate dataFrom, LocalDate dataTo) {
		
		List<RentRecord> lrr = getRentRecordsAtDates(dataFrom, dataTo);
		if (lrr.isEmpty()) {
			return new ArrayList<String>();
		}
		
		Map<String, Double> mostPopular = lrr
				.stream()
				.peek((rr) -> System.out.println(getCar(rr.getRegNumber()) + " - " + rr))
				.collect(Collectors.groupingBy(
						rr -> getCar(rr.getRegNumber()).getModelName(),
					//	Collectors.summarizingDouble(record -> record.getCost());
						Collectors.reducing(
								new Double(0.),
								record -> record.getCost(),
								(x,y)-> x+y)));
		if (mostPopular.isEmpty()) {
			return new ArrayList<String>();
		}
		
		return mostPopular
				.entrySet()
				.stream()
				.sorted((entry1, entry2) -> 
					Double.compare(entry2.getValue(), entry1.getValue()) == 0 ?
							entry1.getKey().compareTo(entry2.getKey()) :
								Double.compare(entry2.getValue(), entry1.getValue()))
				.map(entry -> entry.getKey())
				.collect(Collectors.toList());
	}

	@Override
	public List<Driver> getMostActiveDrivers() {
		Map<Driver, Integer> mostPopular = driverRecords
				.entrySet()
				.stream()
				.collect(Collectors.toMap(entry -> getDriver(entry.getKey()), entry -> entry.getValue().size()));
		if (mostPopular.isEmpty()) {
			return new ArrayList<Driver>();
		}
		
		return mostPopular
				.entrySet()
				.stream()
				.sorted((entry1 , entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()) == 0 ? Long.compare(entry2.getKey().getLicenseId(), entry1.getKey().getLicenseId()) : Integer.compare(entry2.getValue(), entry1.getValue()))
				.map(entry -> entry.getKey())
				.collect(Collectors.toList());
	}

	@Override
	public List<String> getModelNames() {
		return models.keySet().stream().sorted((entry1, entry2) -> entry2.compareTo(entry1)).collect(Collectors.toList());
	}
	
	@Override
	public void save(String fileName) {
		try (
				ObjectOutputStream output =	new ObjectOutputStream(new FileOutputStream(fileName))
			){
				output.writeObject(this);
				System.err.println(LocalDateTime.now() + ": <Data saved>");
		}catch(IOException e) {
			System.err.println(LocalDateTime.now() + ": ERROR - Can not save to the file: " + fileName + "\n");
			throw new RuntimeException(e);
		}
	}
	
// ======================================================================
	
	public List<Car> getAllCars(){
		return cars.values().stream().collect(Collectors.toList());
	}
	
	public List<Model> getAllModels(){
		return models.values().stream().collect(Collectors.toList());
	}
	
	public List<Driver> getAllDrivers(){
		return drivers.values().stream().collect(Collectors.toList());
	}
	
	public List<RentRecord> getAllRents(){
		return records
				.entrySet()
				.stream()
				.flatMap((ss) -> ss.getValue().stream())
				.collect(Collectors.toList());
	}

}

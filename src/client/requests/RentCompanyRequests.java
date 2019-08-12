package client.requests;

import java.time.LocalDate;
import java.util.List;

import dto.Car;
import dto.Driver;
import dto.Model;
import dto.CarsReturnCode;
import dto.RemovedCarData;
import dto.RentRecord;
import net.TCPClientJava;

@SuppressWarnings("serial")
public class RentCompanyRequests extends TCPClientJava implements IRentCompany, Persistable {
	
	public RentCompanyRequests(String hostname, int port) {
		super(hostname, port);
	}
	
	@Override
	public int getGasPrice() {
		return 0;
	}

	@Override
	public void setGasPrice(int price) {
		
	}

	@Override
	public int getFinePercent() {
		return 0;
	}

	@Override
	public void setFinePercent(int finePercent) {
	}

	@Override
	public CarsReturnCode addModel(Model model) {
		String methodName = new Object() {}
		.getClass().getEnclosingMethod().getName();
		return sendRequest(methodName, model);
	}
	
	@Override
	public Model getModel(String modelName) {
		String methodName = new Object() {}
		.getClass().getEnclosingMethod().getName();
		return sendRequest(methodName, modelName);
	}

	@Override
	public CarsReturnCode addCar(Car car) {
		String methodName = new Object() {}
		.getClass().getEnclosingMethod().getName();
		return sendRequest(methodName, car);
	}

	@Override
	public Car getCar(String regNumber) {
		String methodName = new Object() {}
		.getClass().getEnclosingMethod().getName();
		return sendRequest(methodName, regNumber);
	}

	@Override
	public CarsReturnCode addDriver(Driver driver) {
		String methodName = new Object() {}
		.getClass().getEnclosingMethod().getName();
		return sendRequest(methodName, driver);
	}
	
	@Override
	public Driver getDriver(long licenseId) {
		String methodName = new Object() {}
		.getClass().getEnclosingMethod().getName();
		return sendRequest(methodName, licenseId);
	}

	@Override
	public CarsReturnCode rentCar(String regNumber, long licenseId, LocalDate rentDate, int rentDays) {
		String methodName = new Object() {}
		.getClass().getEnclosingMethod().getName();
		return sendRequest(methodName, regNumber + ";" + licenseId + ";" + rentDate + ";" + rentDays);
	}

	@Override
	public List<Car> getCarsDriver(long licenseId) {
		String methodName = new Object() {}
		.getClass().getEnclosingMethod().getName();
		return sendRequest(methodName, licenseId);
	}

	@Override
	public List<Driver> getDriversCar(String regNumber) {
		String methodName = new Object() {}
		.getClass().getEnclosingMethod().getName();
		return sendRequest(methodName, regNumber);
	}

	@Override
	public List<Car> getCarsModel(String modelName) {
		String methodName = new Object() {}
		.getClass().getEnclosingMethod().getName();
		return sendRequest(methodName, modelName);
	}

	@Override
	public List<RentRecord> getRentRecordsAtDates(LocalDate from, LocalDate to) {
		String methodName = new Object() {}
		.getClass().getEnclosingMethod().getName();
		return sendRequest(methodName, from + ";" + to);
	}

	@Override
	public RemovedCarData removeCar(String regNumber) {
		String methodName = new Object() {}
		.getClass().getEnclosingMethod().getName();
		return sendRequest(methodName, regNumber);
	}

	@Override
	public List<RemovedCarData> removeModel(String modelName) {
		String methodName = new Object() {}
		.getClass().getEnclosingMethod().getName();
		return sendRequest(methodName, modelName);
	}

	@Override
	public RemovedCarData returnCar(String regNumber, long licenseId, LocalDate returnDate, int damages, int tankPercent) {
		String methodName = new Object() {}
		.getClass().getEnclosingMethod().getName();
		return sendRequest(methodName, regNumber + ";" + licenseId + ";" + returnDate + ";" + damages + ";" + tankPercent);
	}

	@Override
	public List<String> getMostPopularCarModels(LocalDate fromDate, LocalDate toDate, int fromAge, int toAge) {
		String methodName = new Object() {}
		.getClass().getEnclosingMethod().getName();
		return sendRequest(methodName, fromDate + ";" + toDate + ";" + fromAge + ";" + toAge);
	}

	@Override
	public List<String> getMostProfitableCarModels(LocalDate fromDate, LocalDate toDate) {
		String methodName = new Object() {}
		.getClass().getEnclosingMethod().getName();
		return sendRequest(methodName, fromDate + ";" + toDate);
	}

	@Override
	public List<Driver> getMostActiveDrivers() {
		String methodName = new Object() {}
		.getClass().getEnclosingMethod().getName();
		return sendRequest(methodName, null);
	}

	@Override
	public List<String> getModelNames() {
		String methodName = new Object() {}
		.getClass().getEnclosingMethod().getName();
		return sendRequest(methodName, null);
	}

	@Override
	public void save(String fileName) {
	}

}

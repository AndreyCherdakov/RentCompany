package client.requests;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import dto.Car;
import dto.Driver;
import dto.Model;
import dto.CarsReturnCode;
import dto.RemovedCarData;
import dto.RentRecord;

public interface IRentCompany extends Serializable {

	public int getGasPrice();
	public void setGasPrice(int price);
	public int getFinePercent();
	public void setFinePercent(int finePercent);
	public CarsReturnCode addModel(Model model);
	public Model getModel(String modelName);
	public CarsReturnCode addCar(Car car);
	public Car getCar(String regNumber);
	public CarsReturnCode addDriver(Driver driver);
	public Driver getDriver(long licenseId);
	public CarsReturnCode rentCar(String regNumber, long licenseId, LocalDate rentDate, int rentDays);
	public List<Car> getCarsDriver(long licenseId);
	public List<Driver>getDriversCar(String regNumber);
	public List<Car> getCarsModel(String modelName);//returns cars (not removed and not in_use) of given model
	public List<RentRecord>getRentRecordsAtDates(LocalDate from,LocalDate to);
	public RemovedCarData removeCar(String regNumber);
	public List<RemovedCarData>removeModel(String modelName);
	public RemovedCarData returnCar(String regNumber, long licenseId,LocalDate returnDate,int damages, int tankPercent);
	public List<String> getMostPopularCarModels(LocalDate fromDate,	LocalDate toDate,int fromAge, int toAge);
	public List<String> getMostProfitableCarModels(LocalDate fromDate, LocalDate toDate);
	public List<Driver> getMostActiveDrivers();
	public List<String> getModelNames();
}

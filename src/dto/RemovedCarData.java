package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class RemovedCarData implements Serializable {

	private Car car = new Car();
	private List<RentRecord> removedRecords = new ArrayList<RentRecord>();
	
	public RemovedCarData() {
		super();
	}

	public RemovedCarData(Car car, List<RentRecord> removedRecords) {
		super();
		if (car == null) {
			this.car = new Car();
		} else {
			this.car = car;
		}
		if (removedRecords == null) {
			this.removedRecords = new ArrayList<RentRecord>();
		} else {
			this.removedRecords = removedRecords;
		}
	}

	public Car getCar() {
		return car;
	}

	public List<RentRecord> getRemovedRecords() {
		return removedRecords;
	}

	@Override
	public String toString() {
		return "[" + car + ", " + removedRecords + "]";
	}
	
	
}

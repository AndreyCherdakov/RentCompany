package dto;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class RentRecord implements Serializable {

	private String regNumber;
	private long licenseId;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private int rentDays;
	private int damages;
	private int tankPersent;
	private double cost;
	
	public RentRecord(String regNumber, long licenseId, LocalDate rentDate, LocalDate returnDate, int rentDays,
			int damages, int tankPersent, double cost) {
		super();
		this.regNumber = regNumber;
		this.licenseId = licenseId;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
		this.rentDays = rentDays;
		this.damages = damages;
		this.tankPersent = tankPersent;
		this.cost = cost;
	}

	public RentRecord(String regNumber, long licenseId, LocalDate rentDate, int rentDays) {
		super();
		this.regNumber = regNumber;
		this.licenseId = licenseId;
		this.rentDate = rentDate;
		this.rentDays = rentDays;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public long getLicenseId() {
		return licenseId;
	}

	public LocalDate getRentDate() {
		return rentDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public int getRentDays() {
		return rentDays;
	}

	public int getDamages() {
		return damages;
	}

	public int getTankPersent() {
		return tankPersent;
	}

	public double getCost() {
		return cost;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public void setLicenseId(long licenseId) {
		this.licenseId = licenseId;
	}

	public void setRentDate(LocalDate rentDate) {
		this.rentDate = rentDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public void setRentDays(int rentDays) {
		this.rentDays = rentDays;
	}

	public void setDamages(int damages) {
		this.damages = damages;
	}

	public void setTankPersent(int tankPersent) {
		this.tankPersent = tankPersent;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "[" + regNumber + ", " + licenseId + ", " + rentDate
				+ ", " + returnDate + ", " + rentDays + ", " + damages + ", "
				+ tankPersent + ", " + cost + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + damages;
		result = prime * result + (int) (licenseId ^ (licenseId >>> 32));
		result = prime * result + ((regNumber == null) ? 0 : regNumber.hashCode());
		result = prime * result + ((rentDate == null) ? 0 : rentDate.hashCode());
		result = prime * result + rentDays;
		result = prime * result + ((returnDate == null) ? 0 : returnDate.hashCode());
		result = prime * result + tankPersent;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RentRecord other = (RentRecord) obj;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (damages != other.damages)
			return false;
		if (licenseId != other.licenseId)
			return false;
		if (regNumber == null) {
			if (other.regNumber != null)
				return false;
		} else if (!regNumber.equals(other.regNumber))
			return false;
		if (rentDate == null) {
			if (other.rentDate != null)
				return false;
		} else if (!rentDate.equals(other.rentDate))
			return false;
		if (rentDays != other.rentDays)
			return false;
		if (returnDate == null) {
			if (other.returnDate != null)
				return false;
		} else if (!returnDate.equals(other.returnDate))
			return false;
		if (tankPersent != other.tankPersent)
			return false;
		return true;
	}
	
}

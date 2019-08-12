package dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Model implements Serializable {

	private String modelName;
	private int gasTank;
	private String company;
	private String country;
	private int pricePerDay;
	
	public Model() {
		super();
	}

	public Model(String modelName, int gasTank, String company, String country, int pricePerDay) {
		super();
		this.modelName = modelName;
		this.gasTank = gasTank;
		this.company = company;
		this.country = country;
		this.pricePerDay = pricePerDay;
	}

	public String getModelName() {
		return modelName;
	}

	public int getGasTank() {
		return gasTank;
	}

	public String getCompany() {
		return company;
	}

	public String getCountry() {
		return country;
	}

	public int getPricePerDay() {
		return pricePerDay;
	}

	@Override
	public String toString() {
		return "[" + modelName + ", " + gasTank + ", " + company + ", "
				+ country + ", " + pricePerDay + "]";
	}

	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + gasTank;
		result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
		result = prime * result + pricePerDay;
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
		Model other = (Model) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (gasTank != other.gasTank)
			return false;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		if (pricePerDay != other.pricePerDay)
			return false;
		return true;
	}
	
}

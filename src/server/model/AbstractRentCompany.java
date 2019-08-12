package server.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class AbstractRentCompany implements IRentCompany, Serializable {

	protected int finePercent;
	protected int gasPrice;
	
	public AbstractRentCompany() {
		super();
		this.finePercent = 15;
		this.gasPrice = 10;
	}

	public int getFinePercent() {
		return finePercent;
	}

	public int getGasPrice() {
		return gasPrice;
	}

	public void setFinePercent(int finePercent) {
		this.finePercent = finePercent;
	}

	public void setGasPrice(int gasPrice) {
		this.gasPrice = gasPrice;
	}

	@Override
	public String toString() {
		return "[" + finePercent + ", " + gasPrice + "]";
	}
	
}

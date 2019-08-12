package server.util;

import server.config.Config;
import server.model.IRentCompany;

public class Scheduler implements Runnable {

	private IRentCompany company;

	public Scheduler(IRentCompany company) {
		this.company = company;
	}
	
	@Override
	public void run() {
		
		((Persistable)company).save(Config.DATABASE_PATH);
		
	}
}

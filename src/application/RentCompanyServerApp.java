package application;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.RentCompanyProtocol;
import net.server.ProtocolJava;
import net.server.ServerJava;
import server.config.Config;
import server.model.IRentCompany;
import server.model.RentCompanyEmbedded;
import server.util.Persistable;
import server.util.Scheduler;

public class RentCompanyServerApp {

	public static void main(String[] args) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(Config.NUMBER_OF_THREADS);
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		IRentCompany company = RentCompanyEmbedded.restoreFromFile(Config.DATABASE_PATH);
		ProtocolJava protocol = new RentCompanyProtocol(company);
		ServerJava server = new ServerJava(Config.SERVER_PORT, protocol);
		Scheduler scheduleSaved = new Scheduler(company);
		executor.execute(server);
		scheduler.scheduleAtFixedRate(scheduleSaved,
									Config.DELAY,
									Config.DELAY,
									TimeUnit.MINUTES);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter 'quit' for shutdown");
		while(true) {
			String line = scanner.nextLine();
			if(line.equals("quit"))
				break;
		}
		((Persistable)company).save(Config.DATABASE_PATH);
		executor.shutdown();
		scheduler.shutdown();
		scanner.close();
		ServerJava.closedFlag = false;

	}

}

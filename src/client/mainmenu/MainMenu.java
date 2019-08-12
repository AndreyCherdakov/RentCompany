package client.mainmenu;

import client.config.Config_cl;
import client.ioconfig.ConsoleInputOutput;
import client.ioconfig.ExitItem;
import client.ioconfig.InputOutput;
import client.ioconfig.Item;
import client.ioconfig.SubmenuItem;
import client.items.clerk.AddDriverItem;
import client.items.clerk.GetCarsModelItem;
import client.items.clerk.RentCarItem;
import client.items.clerk.ReturnCarItem;
import client.items.driver.GetCarDriversItem;
import client.items.driver.GetCarItem;
import client.items.driver.GetDriverItem;
import client.items.manager.AddCarItem;
import client.items.manager.AddCarModel;
import client.items.manager.RemoveCarItem;
import client.items.manager.RemoveModelItem;
import client.items.statist.GetMostActiveDriversItem;
import client.items.statist.GetMostPopularModelsItem;
import client.items.statist.GetMostProfitableModelsItem;
import client.items.technician.GetRecordsItem;
import client.requests.IRentCompany;
import client.requests.RentCompanyRequests;

public class MainMenu {

	private static IRentCompany company = new RentCompanyRequests(Config_cl.CLIENT_HOST, Config_cl.CLIENT_PORT);
	private static InputOutput inputOutput = new ConsoleInputOutput();

	public static Item[] getMainMenuItems() {
		Item[]items= {
				new SubmenuItem("\tManager", inputOutput,getManagerItems()),
				new SubmenuItem("\tClerk", inputOutput, getClerkItems()),
				new SubmenuItem("\tDriver", inputOutput, getDriverItems()),
				new SubmenuItem("\tStatist", inputOutput, getStatistItems()),
				new SubmenuItem("\tTechnician", inputOutput, getTechnicianItems()),
				new ExitItem()
		};
		return items;
	}
	public static Item[] getTechnicianItems() {
		Item[] items= {
				new GetRecordsItem(inputOutput, company),
				new ExitItem()};
		return items;
	}
	public static Item[] getStatistItems() {
		Item[] items= {
				new GetMostActiveDriversItem(inputOutput, company),
				new GetMostPopularModelsItem(inputOutput, company),
				new GetMostProfitableModelsItem(inputOutput, company),
				new ExitItem()};
		return items;
	}
	public static Item[] getDriverItems() {
		Item[]items= {
				new GetCarItem(inputOutput, company),
				new GetDriverItem(inputOutput, company),
				new GetCarDriversItem(inputOutput, company),
				new ExitItem()
		};
		return items;
	}
	public static Item[] getClerkItems() {
		Item[]items= {
				new AddDriverItem(inputOutput, company),
				new GetCarsModelItem(inputOutput, company),
				new RentCarItem(inputOutput, company),
				new ReturnCarItem(inputOutput, company),
				new ExitItem()
		};
		return items;
	}
	public static Item[] getManagerItems() {
		Item[]items= {
			new AddCarModel(inputOutput, company),
			new AddCarItem(inputOutput, company),
			new RemoveCarItem(inputOutput, company),
			new RemoveModelItem(inputOutput, company),
			new ExitItem()
		};
		return items;
	}
	public static IRentCompany getCompany() {
		return company;
	}
	public static InputOutput getInputOutput() {
		return inputOutput;
	}
	public static void setCompany(IRentCompany company) {
		MainMenu.company = company;
	}
	public static void setInputOutput(InputOutput inputOutput) {
		MainMenu.inputOutput = inputOutput;
	}
	

}

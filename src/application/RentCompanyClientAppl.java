package application;

import client.ioconfig.Menu;
import client.mainmenu.MainMenu;

public class RentCompanyClientAppl {

	public static void main(String[] args) throws Exception {
		
		Menu menu = new Menu(MainMenu.getMainMenuItems(), MainMenu.getInputOutput());
		menu.runMenu();
	}

}
package client.ioconfig;

public class Menu {
	
	Item[]items;
	InputOutput inputOutput;

public Menu(Item[] items, InputOutput inputOutput) {
	super();
	this.items = items;
	this.inputOutput = inputOutput;
}

public void runMenu() {
	while (true) {
		for (int i = 0; i < items.length; i++) {
			inputOutput.outputLine((i + 1) + ". " + items[i].displayedName());
		}
		Integer selected = inputOutput.inputInteger("\nSelect item number", 1, items.length);
		try {
			items[selected - 1].perform();
		} catch (Exception e) {
			inputOutput.outputLine(e.getMessage());
		}
		if (items[selected - 1].isExit())
			return;
	}
	
}
}

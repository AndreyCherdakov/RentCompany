package client.ioconfig;

import java.util.Scanner;

public class ConsoleInputOutput implements InputOutput {
	
	Scanner scanner = new Scanner(System.in);
	
	@Override
	public String inputString(String prompt) {
		outputLine(prompt + " or cancel");
		String line = scanner.nextLine();
		return line.equals("cancel") ? null : line;
	}

	@Override
	public void output(Object obj) {
		System.out.print(obj);
	}

	@Override
	public void outputErrors(Object obj) {
		System.err.println(obj);
		
	}

}

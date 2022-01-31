package by.epam.jonline_introduction.part06.task01.controller.impl;

import by.epam.jonline_introduction.part06.task01.controller.Command;
import by.epam.jonline_introduction.part06.task01.controller.CommandProvider;
import by.epam.jonline_introduction.part06.task01.controller.LibraryController;

public class LibraryControllerImpl implements LibraryController {

	private final CommandProvider provider = new CommandProvider();

	@Override
	public String doAction(String request) {

		String[] params = new String[2];
		String[] tmpArray;
		String commandName;

		request = request.trim();
		tmpArray = request.split(",", 2);
		for (int i = 0; i < tmpArray.length; i++) {
			params[i] = tmpArray[i];
		}

		commandName = params[0];

		Command currentCommand = provider.getCommand(commandName);
		String response;

		if (currentCommand == null) {
			response = "2";
			return response;
		}

		response = currentCommand.execute(params[1]);

		return response;
	}

}

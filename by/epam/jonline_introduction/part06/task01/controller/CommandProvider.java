package by.epam.jonline_introduction.part06.task01.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.jonline_introduction.part06.task01.controller.impl.AddBookCommand;
import by.epam.jonline_introduction.part06.task01.controller.impl.AddDescriptionCommand;
import by.epam.jonline_introduction.part06.task01.controller.impl.CreateAccountCommand;
import by.epam.jonline_introduction.part06.task01.controller.impl.ExitCommand;
import by.epam.jonline_introduction.part06.task01.controller.impl.LogInCommand;
import by.epam.jonline_introduction.part06.task01.controller.impl.LogOutCommand;
import by.epam.jonline_introduction.part06.task01.controller.impl.RemoveAccoundCommand;
import by.epam.jonline_introduction.part06.task01.controller.impl.RemoveBookCommand;
import by.epam.jonline_introduction.part06.task01.controller.impl.SearchBookByAuthorCommand;
import by.epam.jonline_introduction.part06.task01.controller.impl.SearchBookByTitleCommand;
import by.epam.jonline_introduction.part06.task01.controller.impl.ShowLibraryCommand;
import by.epam.jonline_introduction.part06.task01.controller.impl.SuggestToAddBookCommand;

public class CommandProvider {

	private Map<String, Command> commandMap = new HashMap<String, Command>();

	public CommandProvider() {
		commandMap.put("1", new CreateAccountCommand());
		commandMap.put("2", new LogInCommand());
		commandMap.put("3", new LogOutCommand());
		commandMap.put("4", new RemoveAccoundCommand());
		commandMap.put("5", new ShowLibraryCommand());
		commandMap.put("6", new SearchBookByAuthorCommand());
		commandMap.put("7", new SearchBookByTitleCommand());
		commandMap.put("8", new SuggestToAddBookCommand());
		commandMap.put("9", new AddBookCommand());
		commandMap.put("10", new RemoveBookCommand());
		commandMap.put("11", new AddDescriptionCommand());
		commandMap.put("12", new ExitCommand());

	}

	public Command getCommand(String commandName) {
		Command command;
		command = commandMap.get(commandName);
		return command;
	}

}

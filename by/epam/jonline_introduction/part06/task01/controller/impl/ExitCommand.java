package by.epam.jonline_introduction.part06.task01.controller.impl;

import by.epam.jonline_introduction.part06.task01.controller.Command;
import by.epam.jonline_introduction.part06.task01.service.LibraryService;
import by.epam.jonline_introduction.part06.task01.service.ServiceProvider;
import by.epam.jonline_introduction.part06.task01.service.UserService;

public class ExitCommand implements Command {

	@Override
	public String execute(String params) {

		ServiceProvider provider = ServiceProvider.getInstance();
		LibraryService libraryService = provider.getLibraryService();
		UserService userService = provider.getUserService();

		String response;

		userService.saveUserRepository();
		libraryService.saveLibrary();
		response = "12";

		return response;
	}

}

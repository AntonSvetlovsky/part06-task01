package by.epam.jonline_introduction.part06.task01.controller.impl;

import by.epam.jonline_introduction.part06.task01.bean.UserRole;
import by.epam.jonline_introduction.part06.task01.controller.Command;
import by.epam.jonline_introduction.part06.task01.service.LibraryService;
import by.epam.jonline_introduction.part06.task01.service.ServiceProvider;
import by.epam.jonline_introduction.part06.task01.service.UserService;

public class ShowLibraryCommand implements Command {

	@Override
	public String execute(String params) {

		ServiceProvider provider = ServiceProvider.getInstance();
		LibraryService libraryService = provider.getLibraryService();
		UserService userService = provider.getUserService();
		String response = "4";

		if (userService.checkAccess().equals(UserRole.ADMIN) || userService.checkAccess().equals(UserRole.USER)) {
			response = libraryService.showLibrary(params);
		}

		return response;
	}

}

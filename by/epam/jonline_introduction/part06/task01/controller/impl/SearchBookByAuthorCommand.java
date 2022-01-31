package by.epam.jonline_introduction.part06.task01.controller.impl;

import by.epam.jonline_introduction.part06.task01.bean.UserRole;
import by.epam.jonline_introduction.part06.task01.controller.Command;
import by.epam.jonline_introduction.part06.task01.service.LibraryService;
import by.epam.jonline_introduction.part06.task01.service.ServiceProvider;
import by.epam.jonline_introduction.part06.task01.service.UserService;

public class SearchBookByAuthorCommand implements Command {

	@Override
	public String execute(String params) {

		ServiceProvider provider = ServiceProvider.getInstance();
		LibraryService libraryService = provider.getLibraryService();
		UserService userService = provider.getUserService();
		String response = "4";

		if (userService.checkAccess().equals(UserRole.ADMIN) || userService.checkAccess().equals(UserRole.USER)) {

			String[] paramsArray = new String[2];

			if (params != null) {
				String[] tmpArray = params.split(",", 2);
				for (int i = 0; i < tmpArray.length; i++) {
					paramsArray[i] = tmpArray[i].trim();
				}
			}

			response = libraryService.searchBookByAuthor(paramsArray[0], paramsArray[1]);
		}

		return response;
	}

}

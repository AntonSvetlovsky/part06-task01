package by.epam.jonline_introduction.part06.task01.controller.impl;

import by.epam.jonline_introduction.part06.task01.bean.UserRole;
import by.epam.jonline_introduction.part06.task01.controller.Command;
import by.epam.jonline_introduction.part06.task01.service.ServiceProvider;
import by.epam.jonline_introduction.part06.task01.service.UserService;

public class RemoveAccoundCommand implements Command {

	@Override
	public String execute(String params) {

		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();
		String response = "4";

		if (userService.checkAccess().equals(UserRole.ADMIN) || userService.checkAccess().equals(UserRole.USER)) {
			response = userService.removeAccount();
		}

		return response;
	}

}

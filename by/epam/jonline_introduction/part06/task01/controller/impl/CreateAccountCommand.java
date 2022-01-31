package by.epam.jonline_introduction.part06.task01.controller.impl;

import by.epam.jonline_introduction.part06.task01.bean.UserRole;
import by.epam.jonline_introduction.part06.task01.controller.Command;
import by.epam.jonline_introduction.part06.task01.service.ServiceProvider;
import by.epam.jonline_introduction.part06.task01.service.UserService;

public class CreateAccountCommand implements Command {

	@Override
	public String execute(String params) {

		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();
		String response = "4";

		if (userService.checkAccess().equals(UserRole.GUEST)) {

			String[] paramsArray = new String[4];

			if (params != null) {
				String[] tmpArray = params.split(",", 4);

				for (int i = 0; i < tmpArray.length; i++) {
					paramsArray[i] = tmpArray[i].trim();
				}
			}

			response = userService.createAccount(paramsArray[0], paramsArray[1], paramsArray[2], paramsArray[3]);
		}

		return response;
	}

}

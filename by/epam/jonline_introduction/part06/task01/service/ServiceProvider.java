package by.epam.jonline_introduction.part06.task01.service;

import by.epam.jonline_introduction.part06.task01.service.impl.LibraryServiceImpl;
import by.epam.jonline_introduction.part06.task01.service.impl.UserServiceImpl;

public final class ServiceProvider {

	private static final ServiceProvider instance = new ServiceProvider();
	private final LibraryService libraryService = new LibraryServiceImpl();
	private final UserService userService = new UserServiceImpl();

	private ServiceProvider() {
	}

	public static ServiceProvider getInstance() {
		return instance;
	}

	public LibraryService getLibraryService() {
		return libraryService;
	}

	public UserService getUserService() {
		return userService;
	}
}

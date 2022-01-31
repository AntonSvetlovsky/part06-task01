package by.epam.jonline_introduction.part06.task01.controller;

import by.epam.jonline_introduction.part06.task01.controller.impl.LibraryControllerImpl;

public final class ControllerProvider {

	private static final ControllerProvider instance = new ControllerProvider();
	private final LibraryController controller = new LibraryControllerImpl();

	private ControllerProvider() {
	}

	public static ControllerProvider getInstance() {
		return instance;
	}

	public LibraryController getController() {
		return controller;
	}

}

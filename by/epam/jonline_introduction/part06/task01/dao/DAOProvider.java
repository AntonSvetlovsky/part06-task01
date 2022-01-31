package by.epam.jonline_introduction.part06.task01.dao;

import by.epam.jonline_introduction.part06.task01.dao.impl.LibraryDAOImpl;
import by.epam.jonline_introduction.part06.task01.dao.impl.UserDAOImpl;

public final class DAOProvider {

	private static final DAOProvider instance = new DAOProvider();
	private final LibraryDAO libraryDAO = new LibraryDAOImpl();
	private final UserDAO userDAO = new UserDAOImpl();

	private DAOProvider() {
	}

	public static DAOProvider getInstance() {
		return instance;
	}

	public LibraryDAO getLibraryDAO() {
		return libraryDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

}

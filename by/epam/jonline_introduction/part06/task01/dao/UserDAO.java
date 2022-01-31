package by.epam.jonline_introduction.part06.task01.dao;

import by.epam.jonline_introduction.part06.task01.bean.User;
import by.epam.jonline_introduction.part06.task01.bean.UserRepository;

public interface UserDAO {

	UserRepository loadUserRepository();

	void saveUserRepository();

	User getActiveUser();

	void setActiveUser(User user);

	void setGuestAccess();
}

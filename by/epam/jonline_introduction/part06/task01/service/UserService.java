package by.epam.jonline_introduction.part06.task01.service;

import by.epam.jonline_introduction.part06.task01.bean.UserRole;

public interface UserService {

	String createAccount(String userName, String password, String email, String role);

	String removeAccount();

	String logIn(String userName, String password);

	String logOut();

	String saveUserRepository();

	UserRole checkAccess();

}

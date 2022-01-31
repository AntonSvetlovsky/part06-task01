package by.epam.jonline_introduction.part06.task01.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import by.epam.jonline_introduction.part06.task01.bean.User;
import by.epam.jonline_introduction.part06.task01.bean.UserRepository;
import by.epam.jonline_introduction.part06.task01.bean.UserRole;
import by.epam.jonline_introduction.part06.task01.dao.DAOProvider;
import by.epam.jonline_introduction.part06.task01.dao.UserDAO;
import by.epam.jonline_introduction.part06.task01.service.UserService;

public class UserServiceImpl implements UserService {

	private final DAOProvider provider = DAOProvider.getInstance();
	private final UserDAO userDAO = provider.getUserDAO();

	@Override
	public String createAccount(String userName, String password, String email, String role) {
		String response = "1";
		if (userName == null || password == null || email == null || !UserRole.checkValue(role)) {
			response = "3";
			return response;
		}
		UserRepository repository;
		User newUser;
		List<User> userList;
		repository = userDAO.loadUserRepository();
		if (repository.getUserMap().size() != 0) {
			userList = new ArrayList<User>(repository.getUserMap().values());
			for (User user : userList) {
				if (userName.equals(user.getUserName())) {
					return response;
				}
			}
		}
		String encryptedPassword = encryptor(password);
		UserRole userRole = UserRole.valueOf(role.toUpperCase());
		newUser = new User(userName, encryptedPassword, email, userRole);
		repository.addUser(newUser);
		userDAO.setActiveUser(newUser);
		response = "0";
		return response;
	}

	@Override
	public String removeAccount() {
		String response = "1";
		Integer currentTd = userDAO.getActiveUser().getId();
		if (currentTd != null) {
			UserRepository repository;
			repository = userDAO.loadUserRepository();
			repository.removeUser(currentTd);
			userDAO.setGuestAccess();
			response = "0";
		}
		return response;
	}

	@Override
	public String logIn(String userName, String password) {
		String response = "1";
		if (userName == null || password == null) {
			response = "3";
			return response;
		}
		UserRepository repository;
		List<User> userList;
		repository = userDAO.loadUserRepository();
		userList = new ArrayList<User>(repository.getUserMap().values());
		String encryptedPassword = encryptor(password);
		for (User user : userList) {
			if (user.getUserName().equals(userName)) {
				if (user.getPassword().equals(encryptedPassword)) {
					userDAO.setActiveUser(user);
					response = "0";
					return response;
				}
			}
		}
		return response;
	}

	@Override
	public String logOut() {
		String response = "0";
		userDAO.setGuestAccess();
		return response;
	}

	@Override
	public String saveUserRepository() {
		String response = "1";
		UserRepository repositoty = userDAO.loadUserRepository();
		if (repositoty.getUserMap().size() > 0) {
			userDAO.saveUserRepository();
			response = "0";
		}
		return response;
	}

	@Override
	public UserRole checkAccess() {

		return userDAO.getActiveUser().getRole();
	}

	private String encryptor(String str) {
		StringBuilder encryptedStr = new StringBuilder();
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(str.getBytes());
			for (byte b : bytes) {
				encryptedStr.append(String.format("%02x", b));
			}
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return encryptedStr.toString();
	}

}

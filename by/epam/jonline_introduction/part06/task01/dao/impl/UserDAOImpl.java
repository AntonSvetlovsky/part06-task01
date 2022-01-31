package by.epam.jonline_introduction.part06.task01.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import by.epam.jonline_introduction.part06.task01.bean.User;
import by.epam.jonline_introduction.part06.task01.bean.UserRepository;
import by.epam.jonline_introduction.part06.task01.bean.UserRole;
import by.epam.jonline_introduction.part06.task01.dao.UserDAO;

public class UserDAOImpl implements UserDAO {

	private UserRepository userRepository;
	private User activeUser = new User();

	{
		activeUser.setRole(UserRole.GUEST);
	}

	@Override
	public UserRepository loadUserRepository() {
		if (userRepository == null) {
			userRepository = new UserRepository(loadUserMap());
		}
		return userRepository;
	}

	@Override
	public void saveUserRepository() {

		Map<Integer, User> userMap = userRepository.getUserMap();
		List<User> userList = new ArrayList<User>(userMap.values());

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources\\userRepository.txt"))) {

			writer.write(UserRepository.getCounter().toString());
			writer.newLine();
			for (User user : userList) {
				writer.write(user.getId() + "," + user.getUserName() + "," + user.getPassword() + "," + user.getEmail()
						+ "," + user.getRole());
				writer.newLine();
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getActiveUser() {

		return activeUser;
	}

	@Override
	public void setActiveUser(User user) {

		activeUser = user;

	}

	@Override
	public void setGuestAccess() {
		User guestUser = new User();
		guestUser.setRole(UserRole.GUEST);
		activeUser = guestUser;
	}

	private Map<Integer, User> loadUserMap() {
		Map<Integer, User> userMap = new HashMap<Integer, User>();

		String[] tmpArray;

		try (BufferedReader reader = new BufferedReader(new FileReader("resources\\userRepository.txt"))) {

			String tmp;
			tmp = reader.readLine();
			if (tmp != null && tmp.matches("\\d+")) {
				UserRepository.setCounter(new AtomicInteger(Integer.parseInt(tmp)));
			}
			while ((tmp = reader.readLine()) != null) {
				tmpArray = tmp.split(",", 5);
				userMap.put(Integer.parseInt(tmpArray[0]), new User(Integer.parseInt(tmpArray[0]), tmpArray[1],
						tmpArray[2], tmpArray[3], UserRole.valueOf(tmpArray[4])));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return userMap;
	}

}

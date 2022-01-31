package by.epam.jonline_introduction.part06.task01.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRepository {

	private static AtomicInteger counter;
	private Map<Integer, User> userMap;

	static {
		counter = new AtomicInteger(0);
	}

	{
		userMap = new HashMap<Integer, User>();
	}

	public UserRepository() {
	}

	public UserRepository(Map<Integer, User> userMap) {
		this.userMap = userMap;
	}

	public static AtomicInteger getCounter() {
		return counter;
	}

	public static void setCounter(AtomicInteger counter) {
		UserRepository.counter = counter;
	}

	public Map<Integer, User> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<Integer, User> userMap) {
		this.userMap = userMap;
	}

	public void addUser(User user) {
		Integer id = counter.incrementAndGet();
		user.setId(id);
		userMap.put(id, user);
	}

	public void removeUser(Integer id) {
		userMap.remove(id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userMap == null) ? 0 : userMap.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		UserRepository other = (UserRepository) obj;
		if (userMap == null) {
			if (other.userMap != null) {
				return false;
			}
		} else if (!userMap.equals(other.userMap)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "UserRepository [userMap=" + userMap + "]";
	}

}

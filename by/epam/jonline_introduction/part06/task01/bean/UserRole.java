package by.epam.jonline_introduction.part06.task01.bean;

public enum UserRole {

	GUEST, USER, ADMIN;

	public static boolean checkValue(String value) {

		UserRole[] userRoles = UserRole.values();

		for (UserRole userRole : userRoles) {
			if (userRole.toString().equalsIgnoreCase(value)) {
				return true;
			}
		}
		return false;
	}
}

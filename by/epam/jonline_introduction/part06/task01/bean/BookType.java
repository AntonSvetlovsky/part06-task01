package by.epam.jonline_introduction.part06.task01.bean;

public enum BookType {

	PAPER, DIGITAL;

	public static boolean checkValue(String value) {

		BookType[] bookTypes = BookType.values();

		for (BookType bookType : bookTypes) {
			if (bookType.toString().equalsIgnoreCase(value)) {
				return true;
			}
		}
		return false;
	}
}

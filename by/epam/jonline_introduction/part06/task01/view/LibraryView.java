package by.epam.jonline_introduction.part06.task01.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import by.epam.jonline_introduction.part06.task01.controller.ControllerProvider;
import by.epam.jonline_introduction.part06.task01.controller.LibraryController;

public class LibraryView {

	private static final ControllerProvider instance = ControllerProvider.getInstance();
	private final LibraryController controller = instance.getController();
	private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public void show() {
		try (reader) {

			printMessage("Welcome to Home Library!!!" + "\n");
			boolean flag = true;
			while (flag) {
				printMessage("-".repeat(48) + "\n"
						+ " ".repeat(15) + "Available Options" + " ".repeat(16) + "\n" + "-".repeat(48) + "\n"
						+ "1.  Create Account." + "\n"
						+ "    Enter your user name, password, email address, type of account (admin/user)" + "\n"
						+ "    after the number of command dividing by comma." + "\n"
						+ "    Input example : 1,userName,password,emailAddress,user ." + "\n"
						+ "2.  Log In."+ "\n"
						+ "    Enter your user name, password" + "\n"
						+ "    after the number of command dividing by comma." + "\n"
						+ "    Input example : 2,userName,password ." + "\n"
						+ "3.  Log Out." + "\n"
						+ "4.  Remove Account." + "\n"
						+ "5.  Show Library." + "\n"
						+ "    You can enter requested page after number of command dividing by comma." + "\n"
						+ "    Input example 1 : 5 .    Input example 2 : 5,2 ." + "\n"
						+ "6.  Search Books By Author." + "\n"
						+ "    Enter book author after number of command dividing by comma, " + "\n"
						+ "    you can enter requested page." + "\n"
						+ "    Input example 1 : 6,bookAuthor .    Input example 2 : 6,bookAuthor,2 ." + "\n"
						+ "7.  Search Books By Title." + "\n"
						+ "    Enter book title after the number of command dividing by comma." + "\n"
						+ "    Input example : 7,bookTitle ." + "\n"
						+ "8.  Suggest To Add A Book." + "\n"
						+ "    Enter book title, author, type (paper/digital)" + "\n"
						+ "    after the number of command dividing by comma." + "\n"
						+ "    Input example : 8,bookTitle,bookAuthor,paper ." + "\n"
						+ "9.  Add Book To The Library." + "\n"
						+ "    Enter book title, author, type (paper/digital)" + "\n"
						+ "    after the number of command dividing by comma." + "\n"
						+ "    Input example : 9,bookTitle,bookAuthor,paper ." + "\n"
						+ "10. Remove Book." + "\n"
						+ "    Enter book id after the number of command dividing by comma." + "\n"
						+ "    Input example : 10,id ." + "\n"
						+ "11. Add Description To The Book." + "\n"
						+ "    Enter book id, bookDescription after the number of command dividing by comma." + "\n"
						+ "    Input example : 11,id,description ." + "\n"
						+ "12. Exit." + "\n"
						+ "-".repeat(48) + "\n"
						+ "Enter Your Choice:" + "\n");

				printMessage(">>");
				String request = reader.readLine();

				String response = controller.doAction(request);
				String[] result = response.split("\n", 2);
				String key = result[0];

				switch (key) {
				case "0":
					printMessage("Successful.");
					if (result.length > 1) {
						printBook(result[1]);
					}
					break;
				case "1":
					printMessage("Error1.");
					break;
				case "2":
					printMessage("Invalid Command.");
					break;
				case "3":
					printMessage("Invalid Input.");
					break;
				case "4":
					printMessage("Access Denied.");
					break;
				case "12":
					printMessage("Exit.");
					flag = false;
					break;
				default:
					printMessage("Error.");
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void printMessage(String message) {
		System.out.println(message);
	}

	private void printBook(String result) {

		String[] resultArray = result.split("\n", 2);
		String[] pageInfo = resultArray[0].split("\\|");
		String[] bookArray = resultArray[1].split("\n");
		System.out.println("Current Page: " + pageInfo[0]);
		System.out.print("Available Pages: ");
		int lastPage = Integer.parseInt(pageInfo[1]) - 1;
		for (int i = 0; i <= lastPage; i++) {
			System.out.print(i + "  ");
		}
		System.out.println();

		System.out.printf("%4s%16s%16s%8s%32s", "ID", "TITLE", "AUTHOR", "TYPE", "DESCRIPTION");
		System.out.println();

		for (String book : bookArray) {
			String[] bookInfo = book.split("\\|");
			System.out.printf("%4s%16s%16s%8s%32s", bookInfo[0], bookInfo[1], bookInfo[2], bookInfo[3], bookInfo[4]);
			System.out.println();
		}
	}
}

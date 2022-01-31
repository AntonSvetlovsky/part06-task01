package by.epam.jonline_introduction.part06.task01.service;

public interface LibraryService {

	String showLibrary(String pageNumber);

	String searchBookByTitle(String title, String pageNumber);

	String searchBookByAuthor(String author, String pageNumber);

	String suggestToAddBook(String title, String author, String type);

	String addBook(String title, String author, String type);

	String removeBook(String strId);

	String addDescription(String strId, String description);

	String saveLibrary();
}

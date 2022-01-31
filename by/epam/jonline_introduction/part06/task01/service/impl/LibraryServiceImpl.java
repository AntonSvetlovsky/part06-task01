package by.epam.jonline_introduction.part06.task01.service.impl;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import by.epam.jonline_introduction.part06.task01.bean.Book;
import by.epam.jonline_introduction.part06.task01.bean.BookType;
import by.epam.jonline_introduction.part06.task01.bean.Library;
import by.epam.jonline_introduction.part06.task01.bean.User;
import by.epam.jonline_introduction.part06.task01.bean.UserRepository;
import by.epam.jonline_introduction.part06.task01.bean.UserRole;
import by.epam.jonline_introduction.part06.task01.dao.DAOProvider;
import by.epam.jonline_introduction.part06.task01.dao.LibraryDAO;
import by.epam.jonline_introduction.part06.task01.dao.UserDAO;
import by.epam.jonline_introduction.part06.task01.service.LibraryService;
import by.epam.jonline_introduction.part06.task01.service.Paginator;

public class LibraryServiceImpl implements LibraryService {

	private final DAOProvider provider = DAOProvider.getInstance();
	private final LibraryDAO libraryDAO = provider.getLibraryDAO();
	private final UserDAO userDAO = provider.getUserDAO();

	@Override
	public String showLibrary(String pageNumber) {
		String response = "1";
		Paginator paginator = Paginator.getInstance();
		Library library = libraryDAO.loadLibrary();
		paginator.setBookList(new ArrayList<Book>(library.getbookMap().values()));
		List<Book> bookListPage;
		int pageNum;
		if (pageNumber != null && pageNumber.matches("\\d+")) {
			pageNum = Integer.parseInt(pageNumber);
		} else {
			pageNum = 0;
		}
		bookListPage = paginator.getPageWithNumber(pageNum);

		if (bookListPage.size() == 0) {
			return response;
		}
		response = "0" + "\n" + pageNum + "|" + paginator.getNumberOfPages() + "\n";
		for (Book book : bookListPage) {
			response += book.getId() + "|" + book.getTitle() + "|" + book.getAuthor() + "|" + book.getType() + "|"
					+ book.getDescription() + "\n";
		}
		return response;
	}

	@Override
	public String searchBookByTitle(String title, String pageNumber) {
		String response = "1";
		if (title == null) {
			response = "3";
			return response;
		}
		Library library = libraryDAO.loadLibrary();
		List<Book> bookList;
		List<Book> requestedList;
		List<Book> bookListPage;
		Paginator paginator = Paginator.getInstance();
		int pageNum;
		bookList = new ArrayList<Book>(library.getbookMap().values());
		if (bookList.size() == 0) {
			return response;
		}
		requestedList = new ArrayList<Book>();

		for (Book book : bookList) {
			if (book.getTitle().equalsIgnoreCase(title)) {
				requestedList.add(book);
			}
		}
		paginator.setBookList(requestedList);

		if (pageNumber != null && pageNumber.matches("\\d+")) {
			pageNum = Integer.parseInt(pageNumber);

		} else {
			pageNum = 0;
		}
		bookListPage = paginator.getPageWithNumber(pageNum);

		if (bookListPage.size() == 0) {
			return response;
		}
		response = "0" + "\n" + pageNum + "|" + paginator.getNumberOfPages() + "\n";
		for (Book book : bookListPage) {
			response += book.getId() + "|" + book.getTitle() + "|" + book.getAuthor() + "|" + book.getType() + "|"
					+ book.getDescription() + "\n";
		}
		return response;
	}

	@Override
	public String searchBookByAuthor(String author, String pageNumber) {
		String response = "1";

		if (author == null) {
			response = "3";
			return response;
		}
		Library library = libraryDAO.loadLibrary();
		Paginator paginator = Paginator.getInstance();
		List<Book> bookList;
		List<Book> requestedList;
		List<Book> bookListPage;
		int pageNum;
		bookList = new ArrayList<Book>(library.getbookMap().values());
		if (bookList.size() == 0) {
			return response;
		}
		requestedList = new ArrayList<Book>();
		for (Book book : bookList) {
			if (book.getAuthor().equalsIgnoreCase(author)) {
				requestedList.add(book);
			}
		}
		paginator.setBookList(requestedList);
		if (pageNumber != null && pageNumber.matches("\\d+")) {
			pageNum = Integer.parseInt(pageNumber);

		} else {
			pageNum = 0;
		}
		bookListPage = paginator.getPageWithNumber(pageNum);

		if (bookListPage.size() == 0) {
			return response;
		}

		response = "0" + "\n" + pageNum + "|" + paginator.getNumberOfPages() + "\n";
		for (Book book : bookListPage) {
			response += book.getId() + "|" + book.getTitle() + "|" + book.getAuthor() + "|" + book.getType() + "|"
					+ book.getDescription() + "\n";
		}
		return response;
	}

	@Override
	public String addBook(String title, String author, String type) {
		String response = "1";

		if (title == null || author == null || !BookType.checkValue(type)) {
			response = "3";
			return response;
		}

		BookType bookType = BookType.valueOf(type.toUpperCase());
		Book book = new Book(title, author, bookType);
		Library library = libraryDAO.loadLibrary();
		library.addBook(book);
		response = "0";

		return response;
	}

	@Override
	public String removeBook(String strId) {
		String response = "1";
		int id;

		if (strId == null || !strId.matches("\\d+")) {
			response = "3";
			return response;
		}

		id = Integer.parseInt(strId);
		Library library = libraryDAO.loadLibrary();
		Integer key = Integer.valueOf(id);
		if (library.getbookMap().containsKey(key)) {
			library.removeBook(key);
			response = "0";
		}

		return response;
	}

	@Override
	public String addDescription(String strId, String description) {
		String response = "1";
		int id;

		if (strId == null || !strId.matches("\\d+") || description == null) {
			response = "3";
			return response;
		}

		id = Integer.parseInt(strId);
		Library library = libraryDAO.loadLibrary();
		Integer key = Integer.valueOf(id);
		if (library.getbookMap().containsKey(key)) {
			library.getbookMap().get(key).setDescription(description);
			String subject = "Description of the book was added.";
			String text = library.getbookMap().get(key).getTitle() + "\n" + "Description:" + "\n" + description;
			response = sendMail(subject, text, UserRole.USER);

		}

		return response;

	}

	@Override
	public String saveLibrary() {
		String response = "1";
		Library library = libraryDAO.loadLibrary();
		if (library.getbookMap().size() > 0) {
			libraryDAO.saveLibrary();
			response = "0";
		}
		return response;
	}

	@Override
	public String suggestToAddBook(String title, String author, String type) {
		String response = "1";
		if (title == null || author == null || type == null) {
			response = "3";
			return response;
		}
		String subject = "New Book.";
		String text = title + " by " + author + ", type: " + type;
		response = sendMail(subject, text, UserRole.ADMIN);
		return response;
	}

	private String sendMail(String subject, String text, UserRole role) {
		String response = "1";

		String serviceEmail = ""; // TODO For sending messages you have to enter service email address
		String serviceEmailPassword = ""; // TODO and password and configure mail.properties
		UserRepository repository;
		List<User> userList;
		List<User> recipientList = new ArrayList<User>();
		repository = userDAO.loadUserRepository();
		userList = new ArrayList<User>(repository.getUserMap().values());
		for (User user : userList) {
			if (user.getRole() == role) {
				recipientList.add(user);
			}
		}

		Properties mailProperties = new Properties();
		try (FileReader reader = new FileReader("properties\\mail.properties")) {

			mailProperties.load(reader);
			Session mailSession = Session.getDefaultInstance(mailProperties);
			MimeMessage message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress("antonsvetlovsky@gmail.com"));

			for (User user : recipientList) {
				message.addRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
			}
			// message.addRecipient(RecipientType.TO, new
			// InternetAddress("antonsvetlovsky@gmail.com"));
			message.setSubject(subject);
			message.setText(text);

			Transport tr = mailSession.getTransport();
			tr.connect(serviceEmail, serviceEmailPassword);
			tr.sendMessage(message, message.getAllRecipients());
			response = "0";

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

}

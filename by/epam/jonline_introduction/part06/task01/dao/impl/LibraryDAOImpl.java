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

import by.epam.jonline_introduction.part06.task01.bean.Book;
import by.epam.jonline_introduction.part06.task01.bean.BookType;
import by.epam.jonline_introduction.part06.task01.bean.Library;
import by.epam.jonline_introduction.part06.task01.dao.LibraryDAO;

public class LibraryDAOImpl implements LibraryDAO {

	private Library library;

	@Override
	public Library loadLibrary() {
		if (library == null) {
			library = new Library(loadBookMap());
		}
		return library;
	}

	@Override
	public void saveLibrary() {

		Map<Integer, Book> bookMap = library.getbookMap();
		List<Book> bookList = new ArrayList<Book>(bookMap.values());

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources\\library.txt"))) {

			writer.write(Library.getCounter().toString());
			writer.newLine();
			for (Book book : bookList) {
				writer.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getType() + ","
						+ book.getDescription());
				writer.newLine();
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Map<Integer, Book> loadBookMap() {
		Map<Integer, Book> bookMap = new HashMap<Integer, Book>();
		String[] tmpArray;

		try (BufferedReader reader = new BufferedReader(new FileReader("resources\\library.txt"))) {

			String tmp;
			tmp = reader.readLine();
			if (tmp != null && tmp.matches("\\d+")) {
				Library.setCounter(new AtomicInteger(Integer.parseInt(tmp)));
			}
			while ((tmp = reader.readLine()) != null) {
				tmpArray = tmp.split(",", 5);
				bookMap.put(Integer.parseInt(tmpArray[0]), new Book(Integer.parseInt(tmpArray[0]), tmpArray[1],
						tmpArray[2], BookType.valueOf(tmpArray[3]), tmpArray[4]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bookMap;
	}

}

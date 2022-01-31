package by.epam.jonline_introduction.part06.task01.service;

import java.util.ArrayList;
import java.util.List;

import by.epam.jonline_introduction.part06.task01.bean.Book;

public class Paginator {

	private static Paginator instance;
	private int pageSize;
	private List<Book> bookList;

	{
		pageSize = 5;
	}

	private Paginator() {
	}

	public static Paginator getInstance() {
		if (instance == null) {
			instance = new Paginator();
		}
		return instance;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public int getNumberOfPages() {
		if ((bookList.size() % pageSize) == 0) {
			return bookList.size() / pageSize;
		} else {
			return bookList.size() / pageSize + 1;
		}
	}

	public List<Book> getPageWithNumber(int number) {
		List<Book> requestedList = null;

		if (number > -1 && number <= getNumberOfPages()) {
			requestedList = new ArrayList<Book>();
			for (int i = number * pageSize; i < number * pageSize + pageSize; i++) {
				if (i < bookList.size()) {
					requestedList.add(bookList.get(i));
				}
			}
		}

		return requestedList;
	}

}

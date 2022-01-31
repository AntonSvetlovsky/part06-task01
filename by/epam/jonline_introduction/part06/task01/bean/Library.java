package by.epam.jonline_introduction.part06.task01.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Library {

	private static AtomicInteger counter;
	private Map<Integer, Book> bookMap;

	static {
		counter = new AtomicInteger(0);
	}

	{
		bookMap = new HashMap<Integer, Book>();
	}

	public Library() {
	}

	public Library(Map<Integer, Book> bookMap) {
		this.bookMap = bookMap;
	}

	public static AtomicInteger getCounter() {
		return counter;
	}

	public static void setCounter(AtomicInteger counter) {
		Library.counter = counter;
	}

	public Map<Integer, Book> getbookMap() {
		return bookMap;
	}

	public void setBookMap(Map<Integer, Book> bookMap) {
		this.bookMap = bookMap;
	}

	public void addBook(Book book) {
		int id = counter.incrementAndGet();
		book.setId(id);
		bookMap.put(id, book);
	}

	public void removeBook(int id) {
		bookMap.remove(id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookMap == null) ? 0 : bookMap.hashCode());
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
		Library other = (Library) obj;
		if (bookMap == null) {
			if (other.bookMap != null) {
				return false;
			}
		} else if (!bookMap.equals(other.bookMap)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Library [libraryMap=" + bookMap + "]";
	}

}

package by.epam.jonline_introduction.part06.task01.dao;

import by.epam.jonline_introduction.part06.task01.bean.Library;

public interface LibraryDAO {

	Library loadLibrary();

	void saveLibrary();
}

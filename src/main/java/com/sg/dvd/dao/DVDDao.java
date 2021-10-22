package com.sg.dvd.dao;
import com.sg.dvd.dto.DVDLib;
import java.util.List;

/* Data Access Object (DAO) part of Model
   -DAO-> encapsulate the logic for retrieving, saving and updating data in data storage
   -Interface-> Is an interface, allowing for inheritance and cohesion among reuse of methods
 */

public interface DVDDao {
    //add dvd
    DVDLib addDVD(String title, DVDLib dvd) throws DVDDaoException;

    //list all dvds
    List<DVDLib> getAllDVDs() throws DVDDaoException;

    //get dvd by title
    DVDLib getDVD(String title) throws DVDDaoException;

    //remove dvd from library
    DVDLib removeDVD(String dvdTitle) throws DVDDaoException;

    //edit a dvd
    DVDLib editDVD(String dvdTitle, String partToEdit, String replacement) throws DVDDaoException;

    //put all dvds edited/added/removed in library
    void writeDVDLibrary(String file) throws DVDDaoException;

    //load dvdLibrary
    void loadDVDLibrary(String file) throws DVDDaoException;
}

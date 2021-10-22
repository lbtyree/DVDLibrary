package com.sg.dvd.controller;
import com.sg.dvd.dao.DVDDao;
import com.sg.dvd.ui.DVDView;
import com.sg.dvd.dao.DVDDaoException;
import com.sg.dvd.dto.DVDLib;
import java.util.List;

/*Controller
  -Orchestrates actions using the methods of model & view
  -Composition-> DVDController has members DVDDao and DVDView.
  -Composition-> DVDController object must be initialized with Dao and View Objects
  -Dependency Injection-> Cannot Allow DVDController to choose correct UserIO implementation
  -Dependency Injection-> loosely couple view to an implementation of the interface
  -Dependency Injection->UserIO interface implementation is chosen in App class
  -Dependency Injection->Specific implementation (or dependency) will be injected into the object when created in App
 */

public class DVDController {
    //members DVDDao and DVDView (Composition)
    // do not initialize, just declare to
    private DVDView view;
    private DVDDao dao;

    //run all
    public void run() throws DVDDaoException {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        createDVD();
                        break;
                    case 2:
                        removeDVD();
                        break;
                    case 3:
                        editDVD();
                        break;
                    case 4:
                        listDVDs();
                        break;
                    case 5:
                        // search by title / display particular dvd
                        viewDVD();
                        break;
                    case 6:
                        //load dvd library from file
                        loadDVDLibrary();
                        break;
                    case 7:
                        //save dvd library to file
                        saveDVDLibrary();
                        keepGoing=false;
                        break;

                    default:
                        //save dvd library to file on default method
                        saveDVDLibraryDefault();
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (DVDDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    // Constructor Injection: passing uninitialized DVDDao dao & DVDView view
    //don't make DVDController responsible for injecting the right
    // UserIO implementation into ClassRosterView.
    // That is the job of the App class.
    public DVDController(DVDDao dao, DVDView view) {
        this.dao = dao;
        this.view = view;
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createDVD() throws DVDDaoException {
        view.displayCreateDVDBanner();
        DVDLib newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }

    private void listDVDs() throws DVDDaoException {
        view.displayDisplayAllBanner();
        List<DVDLib> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }

    private void viewDVD() throws DVDDaoException {
        view.displayDisplayDVDBanner();
        String dvdTitle = view.getDVDTitle();
        DVDLib dvd = dao.getDVD(dvdTitle);
        view.displayDVD(dvd);
    }

    private void removeDVD() throws DVDDaoException {
        view.displayRemoveDVDBanner();
        String dvdTitle = view.getDVDTitle();
        DVDLib removedDVD = dao.removeDVD(dvdTitle);
        view.displayRemoveResult(removedDVD);
    }

    private void editDVD() throws DVDDaoException {
        view.displayEditDVDBanner();
        String dvdTitle = view.getDVDTitle();
        String partToEdit= view.getItemToChange();
        String replacement= view.getDVDReplacement(partToEdit);
        DVDLib editDVD = dao.editDVD(dvdTitle, partToEdit, replacement);
        view.displayEditSuccessBanner();
    }

    private void loadDVDLibrary() throws DVDDaoException {
        view.displayLoadDVDBanner();
        String file = view.getInputFileName();
        view.displayLoading();
        dao.loadDVDLibrary(file);
        view.displayLoadSuccessBanner(file);
    }

    private void saveDVDLibrary() throws DVDDaoException {
        view.displaySaveDVDBanner();
        String file = view.getOutputFileName();
        dao.writeDVDLibrary(file);
        view.displaySaveSuccessBanner(file);
    }

    private void saveDVDLibraryDefault() throws DVDDaoException {
        view.displaySaveDVDDefaultBanner();
        String file = view.getOutputFileName();
        dao.writeDVDLibrary(file);
        view.displaySaveSuccessBanner(file);
    }
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}


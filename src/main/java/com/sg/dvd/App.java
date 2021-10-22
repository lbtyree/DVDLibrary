package com.sg.dvd;
import com.sg.dvd.dao.DVDDaoException;
import com.sg.dvd.ui.UserIOConsoleImpl;
import com.sg.dvd.ui.UserIO;
import com.sg.dvd.ui.DVDView;
import com.sg.dvd.dao.DVDDao;
import com.sg.dvd.dao.DVDDaoFileImpl;
import com.sg.dvd.controller.DVDController;

/*
   -configures, instantiates, and assembles the classes of the application
   -wiring the application:  instantiating dependency instances
   -& passing that into the correct constructor
 */

public class App {
    public static void main(String[] args) throws DVDDaoException {
        // instantiate instances (dependencies) & pass them
        UserIO myIo = (UserIO) new UserIOConsoleImpl(); //instantiate implementation (dependency)
        //Composition: DVDView object composed of UserIO object
        DVDView myView = new DVDView((com.sg.dvd.ui.UserIO) myIo); //pass instance of dependency
        DVDDao myDao = new DVDDaoFileImpl(); //instantiate implementation (dependency)
        //Composition: DVDController object composed of Dao and View Objects
        DVDController controllerDVD = new DVDController(myDao, myView);//pass instance of dependency
        //run application
        controllerDVD.run();
    }

}

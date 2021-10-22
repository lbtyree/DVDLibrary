package com.sg.dvd.ui;
import com.sg.dvd.dto.DVDLib;
import java.util.List;

/* Part of View
   -Interacts with user
   -Composition-> has member UserIO. DVDView object must be initialized with UserIO object
   -Dependency Injection->loosely couple view to an implementation of the interface
   - UserIO interface implementation is chosen in App class
 */

public class DVDView {
    //member UserIO (Composition)
    // Do not initialize io member field here, do it in the constructor

    private static com.sg.dvd.ui.UserIO io;

    //constructor
    // Dependency injection-> allow app class to choose io implementation
    public DVDView(UserIO io) {
        this.io = io;
    }

    //Menu for user to select action
    public static int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add DVD");
        io.print("2. Delete DVD");
        io.print("3. Edit DVD");
        io.print("4. List All DVDs");
        io.print("5. Search for DVD by title & Display Info for Particular DVD");
        io.print("6. Load DVD Library From File");
        io.print("7. Save changes to library file and exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }

    //collect info on a new dvd & create the new DVD
    public DVDLib getNewDVDInfo() {
        //String AddressId = io.readString("Please enter Address ID");
        String title = io.readString("Please enter title");
        String releaseDate = io.readString("Please enter Release Date ");
        String ratingMPAA = io.readString("Please enter Rating MPAA");
        String directorName = io.readString("Please enter Director Name");
        String studio = io.readString("Please enter the Studio");
        String ratingUser = io.readString("Please enter User Rating & Notes");

        //Create new DVDLib
        DVDLib currentDVD = new DVDLib(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setRatingMPAA(ratingMPAA);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setRatingUser(ratingUser);

        return currentDVD;
    }

    //Banner for Creating DVD
    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }

    //Banner for Successfully Creating DVD
    public void displayCreateSuccessBanner() {
        io.readString(
                "DVD successfully created.  Please hit enter to continue");
    }

    //Display all dvds in current list
    public void displayDVDList(List<DVDLib> dvdList) {
        if(dvdList.size()==0){ // if list empty
            io.print("There are currently no DVDs in the library. Please add a dvd.");
        }
        for (DVDLib currentDVD : dvdList) {
            String studentInfo = String.format("Title: %s , Release Date: %s , MPAA Rating: %s , Director Name: %s , Studio: %s , User Rating: %s",
                    currentDVD.getTitle(),
                    currentDVD.getReleaseDate(),
                    currentDVD.getRatingMPAA(),
                    currentDVD.getDirectorName(),
                    currentDVD.getStudio(),
                    currentDVD.getRatingUser());
            io.print(studentInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    //Banner for displaying all dvds
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }

    //Banner for displaying a single DVD
    public void displayDisplayDVDBanner () {
        io.print("=== Display DVD ===");
    }

    //Get title of DVD
    public String getDVDTitle() {
        return io.readString("Please enter the DVD Title.");
    }

    //Display for singular dvd
    public void displayDVD(DVDLib dvd) {
        if (dvd != null) {
            io.print("Title: " + dvd.getTitle());
            io.print("Release Date: " + dvd.getReleaseDate());
            io.print("MPAA Rating: " + dvd.getRatingMPAA());
            io.print("Director Name: " + dvd.getDirectorName());
            io.print("Studio: " + dvd.getStudio());
            io.print("User Rating: " + dvd.getRatingUser());
            io.print("");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveDVDBanner () {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveResult(DVDLib recordDVD) {
        if(recordDVD != null){
            io.print("DVD successfully removed.");
        }else{
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayEditDVDBanner () {
        io.print("=== Edit DVD ===");
    }

    public String getItemToChange() {
        io.print("\"Please enter one of the following that you'd like to change:\"");
        return io.readString("Title, Release Date, Rating MPAA, Director Name, Studio, User Rating");
    }


    public String getDVDReplacement(String item) {
        return io.readString("Please type what you would like to change " + item +  " to.");
    }

    public void displayEditSuccessBanner() {
        io.readString(
                "DVD successfully edited.  Please hit enter to continue");
    }

    public void displayLoadDVDBanner () {
        io.print("=== Load DVD Library ===");
    }

    public String getInputFileName() {
        return io.readString("Please enter the name of the file containing the DVD library.");
    }

    public void displayLoading(){io.print("Loading... ");}

    public void displayLoadSuccessBanner(String fileName) {
        io.readString(
                "DVD library successfully loaded from " + fileName + ".  Please hit enter to continue");
    }

    public void displaySaveDVDBanner () {
        io.print("=== Save DVD Library Back to File===");
    }

    public void displaySaveDVDDefaultBanner () {
        io.print("=== Unknown Command, Save DVD Library Back to File to Save Previous Changes===");
    }

    public String getOutputFileName() {
        return io.readString("Please enter the name of the file you would like to contain the DVD library.");
    }

    public void displaySaveSuccessBanner(String fileName) {
        io.readString(
                "DVD library successfully saved to " + fileName +".  Please hit enter to continue");
    }


    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}

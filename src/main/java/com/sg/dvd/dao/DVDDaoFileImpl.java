package com.sg.dvd.dao;
import com.sg.dvd.dto.DVDLib;
import java.io.*;
import java.util.*;

/* Data Access Object (DAO) Part of Model
   -DAO-> encapsulate the logic for retrieving, saving and updating data in data storage
   -Impl naming convention-> Named so because we are implementing the DVDDao interface
 */

public class DVDDaoFileImpl implements DVDDao{
    private Map<String, DVDLib> dvds = new HashMap<>();
    public static final String DVD_FILE = "dvd_library.txt";
    public static final String DELIMITER = "::";


    @Override
    public DVDLib addDVD(String title, DVDLib dvd) throws DVDDaoException {
        DVDLib newDVD= dvds.put(title, dvd);
        return null;
    }

    @Override
    public List<DVDLib> getAllDVDs() throws DVDDaoException {
        return new ArrayList(dvds.values());
    }

    @Override
    public DVDLib getDVD(String title) throws DVDDaoException {
        return dvds.get(title);
    }

    @Override
    public DVDLib removeDVD(String dvdTitle) throws DVDDaoException {
        DVDLib removedDVD= dvds.remove(dvdTitle);
        return removedDVD;
    }

    @Override
    public DVDLib editDVD(String dvdTitle, String edit, String replacement) throws DVDDaoException {
        DVDLib toEditDVD= dvds.get(dvdTitle);
        if (edit.equals("Title")){
            //must change key and set title
            //remove old entry, create new
            String oldKey= toEditDVD.getTitle(); // collect old key
            toEditDVD.setTitle(replacement); // edit title in DVDLib object
            DVDLib removed= dvds.remove(oldKey); //remove edited object
            dvds.put(replacement, removed); // replace removed object with new key
        }else if(edit.equals("Release Date")){
            toEditDVD.setReleaseDate(replacement);
        } else if(edit.equals("Rating MPAA")){
            toEditDVD.setRatingMPAA(replacement);
        }else if(edit.equals("Director Name")){
            toEditDVD.setDirectorName(replacement);
        }else if(edit.equals("Studio")){
            toEditDVD.setStudio(replacement);
        }else if(edit.equals("User Rating")){
            toEditDVD.setRatingUser(replacement);
        }
        return toEditDVD;
    }

    //unmarshall formatted text file to DVDLib object
    private DVDLib unmarshallDVD(String dvdAsText){
        // format {Title::Release Date::Rating MPAA::DirectorName::Studio::User Rating}

        String[] dvdTokens = dvdAsText.split(DELIMITER);

        // Given the pattern above, the title is in index 0 of the array.
        String title = dvdTokens[0];

        //Create new DVDLib
        DVDLib dvdFromFile = new DVDLib(title);

        // Index 1 - Release Date
        dvdFromFile.setReleaseDate(dvdTokens[1]);


        // Index 2 - Rating MPAA
        dvdFromFile.setRatingMPAA(dvdTokens[2]);


        // Index 3 - Director Name
        dvdFromFile.setDirectorName(dvdTokens[3]);

        // Index 4 - Studio
        dvdFromFile.setStudio(dvdTokens[4]);

        // Index 5 - User Rating
        dvdFromFile.setRatingUser(dvdTokens[5]);

       //return dvd
        return dvdFromFile;
    }

    //Marshall DVDLib object to formatted string for file
    private String marshallDVD(DVDLib aDVD){
        // format {Title::Release Date::Rating MPAA::DirectorName::Studio::User Rating}

        //title at index 0
        String dvdAsText = aDVD.getTitle() + DELIMITER;

        // add the rest of the properties in the correct order:

        // Index 1 - Release Date
        dvdAsText  += aDVD.getReleaseDate() + DELIMITER;

        // Index 2 - Rating MPAA
        dvdAsText  += aDVD.getRatingMPAA() + DELIMITER;

        // Index 3 - Director Name
        dvdAsText  += aDVD.getDirectorName() + DELIMITER;

        // Index 4 - Studio
        dvdAsText  += aDVD.getStudio() + DELIMITER;

        // Index 5 - User Rating , No delimiter on final part
        dvdAsText  += aDVD.getRatingUser();

        // DVDLib object has been returned to formatted text
        return dvdAsText;
    }

    //was private, made public for user purposes
    //load the DVDLib from a given file
    public void loadDVDLibrary(String dvdFile) throws DVDDaoException {
        //Using scanner to scan/read file
        Scanner scanner;

        //try for file, catch if file not found
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(dvdFile)));
        } catch (FileNotFoundException e) {
            throw new DVDDaoException(
                    "-_- Could not load DVD Library data into memory.", e);
        }

        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent DVDLib unmarshalled
        DVDLib currentDVD;

        // Go through file line by line, decoding each line into a
        // DVDLib object by calling the unmarshallDVD method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a DVDLib Object
            currentDVD = unmarshallDVD(currentLine);

            // use the dvd Title as the map key
            // put currentDVD into the map with currrentDVD object as value
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        // close scanner
        scanner.close();
    }

    //was private, made public for user purposes
    //write library list to given file
    public void writeDVDLibrary(String dvdFile) throws DVDDaoException {
        // Not handling the IOException - but instead
        // translating it to an application specific exception and
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to
        // handle any errors that occur.

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(dvdFile));
        } catch (IOException e) {
            throw new DVDDaoException(
                    "Could not save student data.", e);
        }

        // Write out the DVDLib objects to the library file.
        // reuse list all method
        String dvdAsText;
        List<DVDLib> dvdList = this.getAllDVDs();
        for (DVDLib currentDVD : dvdList ) {
            // turn DVDLib into a String by marshalling
            dvdAsText = marshallDVD(currentDVD);
            // write the DVDLib object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            //flush makes buffer empty for further data to store
            out.flush();
        }
        // close stream
        out.close();
    }
}

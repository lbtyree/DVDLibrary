package com.sg.dvd.dto;

/* Data Transfer Object (DTO), Part of Model
   -used to transfer the data between classes and modules of application
   -should only contain private fields for your data, getters, setters, and constructors.
 */

public class DVDLib {
    private String title;
    private String releaseDate;
    private String ratingMPAA;
    private String directorName;
    private String studio;
    private String ratingUser;

    //constructor
    public DVDLib(String title) {
        this.title = title;
    }

    // Add getters and setters for all above private data fields

    public String getTitle() {
        return title;
    }

    public void setTitle(String title ) {this.title = title; }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate ) {this.releaseDate = releaseDate; }

    public String getRatingMPAA() {
        return ratingMPAA;
    }

    public void setRatingMPAA(String ratingMPAA ) {this.ratingMPAA = ratingMPAA; }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName ) {this.directorName = directorName; }

    public String getStudio() {return studio;}

    public void setStudio(String studio ) {this.studio = studio; }

    public String getRatingUser() {
        return ratingUser;
    }

    public void setRatingUser(String ratingUser ) {this.ratingUser= ratingUser; }

}


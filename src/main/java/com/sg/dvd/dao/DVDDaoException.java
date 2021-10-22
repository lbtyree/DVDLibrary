package com.sg.dvd.dao;

/*  DAO exceptions Part of Model
   -Inheritance Relationship-> DVDDaoException extends Exception
   -Exception-> Exception extends throwable, can use param throwable cause
   -Purpose of DVDDaoException-> hide the underlying implementation exceptions so that we don't leak implementation details from our DAO
   -Why Extend Exception-> extending Exception allows us to translate and/or wrap any implementation-specific
   -exception that can get thrown. By extending exception,  our new exception will be a checked exception.
 */

public class DVDDaoException extends Exception{ //inheritance of exception which extends throwable

    //something is wrong in our application, but it isn't caused by another exception.
    public DVDDaoException(String message) {
        super(message);
    }

    //wrap the original exception with an application-specific exception
    // cases where something is wrong in our application that is caused by
    // another exception in the underlying implementation
    // must catch the implementation-specific exception ie FileNotFoundException
    public DVDDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}


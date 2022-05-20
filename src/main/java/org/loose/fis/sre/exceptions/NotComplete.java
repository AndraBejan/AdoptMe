package org.loose.fis.sre.exceptions;

public class NotComplete extends Exception {

    public NotComplete() {
        super("You didn't complete all the fields!");
    }

}

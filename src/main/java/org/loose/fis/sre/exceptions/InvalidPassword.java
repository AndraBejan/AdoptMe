package org.loose.fis.sre.exceptions;

public class InvalidPassword extends Exception {

    public InvalidPassword() {
        super("You didn't enter the same password!");
    }

}

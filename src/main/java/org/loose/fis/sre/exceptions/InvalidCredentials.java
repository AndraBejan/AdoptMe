package org.loose.fis.sre.exceptions;

public class InvalidCredentials extends Exception {

    public InvalidCredentials() {
        super("Your username or password is incorrect!");
    }

}

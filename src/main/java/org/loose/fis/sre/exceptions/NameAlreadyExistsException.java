package org.loose.fis.sre.exceptions;

public class NameAlreadyExistsException extends Exception {

    private String Name;

    public NameAlreadyExistsException(String Name) {
        super(String.format("An account with this name %s already exists!", Name));
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }
}

package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.InvalidCredentials;
import org.loose.fis.sre.exceptions.InvalidPassword;
import org.loose.fis.sre.exceptions.NotComplete;
import org.loose.fis.sre.exceptions.NameAlreadyExistsException;
import org.loose.fis.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class UserService {

    private static ObjectRepository<User> userRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String name, String password,String password2, String phone, String email, String role) throws NameAlreadyExistsException, InvalidPassword, NotComplete {
        checkUserDoesNotAlreadyExist(name);
        checkSamePassword(password,password2);
        checkComplete(name, password, password2, phone, email, role);
        userRepository.insert(new User(name, encodePassword(name, password), encodePassword(name, password2), phone, email, role));
    }

    public static void validateUser(String name, String password) throws InvalidCredentials {
        checkInvalidCredentials(name, password);

    }

    private static void checkUserDoesNotAlreadyExist(String name) throws NameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(name, user.getName()))
                throw new NameAlreadyExistsException(name);
        }
    }

    private static void checkInvalidCredentials(String name, String password) throws InvalidCredentials {
        boolean userExists = false;
        for (User user : userRepository.find()) {
            if (Objects.equals(name, user.getName())) {
                userExists = true;
                if(!Objects.equals(encodePassword(name, password), user.getPassword()))
                    throw new InvalidCredentials();
            }
        }
        if(!userExists)
            throw new InvalidCredentials();
    }

    public static int checkAccountInformation(String name, String password) {
        for (User user : userRepository.find()) {
            if (Objects.equals(name, user.getName()) && Objects.equals(encodePassword(name, password), user.getPassword())) {
                if(Objects.equals(user.getRole(),"Client"))
                    return 1;
                else if(Objects.equals(user.getRole(),"Owner"))
                    return 2;
            }
        }
        return 0;
    }

    private static void checkComplete(String name, String password, String password2, String phone, String email, String role) throws NotComplete {
        if (name.isEmpty() || password.isEmpty() || password2.isEmpty() || phone.isEmpty() || email.isEmpty() || role.isEmpty())
            throw new NotComplete();
    }

    private static void checkSamePassword(String password, String password2) throws InvalidPassword {
        if (password.equals(password2)) return;
        else throw new InvalidPassword();
    }

    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }


}

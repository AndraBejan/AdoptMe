package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.InvalidPassword;
import org.loose.fis.sre.exceptions.NameAlreadyExistsException;
import org.loose.fis.sre.exceptions.NotComplete;
import org.loose.fis.sre.model.Announcement;
import org.loose.fis.sre.model.Pet;
import org.loose.fis.sre.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class AnnouncementService {
    private static ObjectRepository<Announcement> announcementRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("ann.db").toFile())
                .openOrCreate("test", "test");

        announcementRepository = database.getRepository(Announcement.class);
    }
    public static void addAnnouncement(Pet petName, User user, String category) {
        announcementRepository.insert(new Announcement(petName, user, category));
    }
    public static Announcement getIdAnnouncement(String id){
        System.out.println("\nSearching for announcement ID " + id);
        for(Announcement crt : announcementRepository.find()){
            if(crt.getID().equals(id)){
                System.out.println("Announcement was found");
                return crt;
            }
        }
        System.out.println("Announcement with ID " + id + " was not found");
        return null;
    }

    public static ArrayList<Announcement> getUserAnnouncements(String username){
        System.out.println("\nRetrieving announcement arrayList from database for user " + username);
        ArrayList<Announcement> userAds= new ArrayList<>();
        for(Announcement crt : announcementRepository.find()){
            if(Objects.equals(crt.getUser().getName(),username)){
                userAds.add(crt);
            }
        }
        System.out.println("Operation was completed successfully");
        return userAds;
    }

    public static ArrayList<Announcement> getAnnouncement() {
        ArrayList<Announcement> userAds= new ArrayList<>();
        return userAds;
    }

    public static ArrayList<Announcement> getCategoryAnnouncements(String value) {
        ArrayList<Announcement> userAds= new ArrayList<>();
        return userAds;
    }

    public static ArrayList<Announcement> getPetTypeAnnouncements(String value) {
        ArrayList<Announcement> userAds= new ArrayList<>();
        return userAds;
    }

    public static ArrayList<Announcement> getCategoryPetTypeAnnouncements(String value, String value1) {
        ArrayList<Announcement> userAds= new ArrayList<>();
        return userAds;
    }
}

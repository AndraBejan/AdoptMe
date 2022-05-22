package org.loose.fis.sre.model;

import javafx.scene.control.PasswordField;
import org.dizitart.no2.objects.Id;

import javax.validation.OverridesAttribute;
import java.util.Objects;

public class User {
    @Id
    private String name;
    private String password;
    private String password2;
    private String phone;
    private String email;
    private String role;
    private String imagePath = "C/Users/bejan/OneDrive/Desktop/CTI/FIS/AdoptME/docs/user.png";


    public User(String name, String password, String password2, String phone, String email, String role) {
        this.name = name;
        this.password = password;
        this.password2 = password2;
        this.phone = phone;
        this.email = email;
        this.role = role;
    }

    public User(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {return password;}

    public String getRole() {return role;}

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!Objects.equals(name, user.name)) return false;
        if (!Objects.equals(password, user.password)) return false;
        if (!Objects.equals(password2, user.password2)) return false;
        if (!Objects.equals(phone, user.phone)) return false;
        if (!Objects.equals(email, user.email)) return false;
        return Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (password2 != null ? password2.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}

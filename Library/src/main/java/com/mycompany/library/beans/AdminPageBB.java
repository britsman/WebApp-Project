package com.mycompany.library.beans;

import com.mycompany.library.core.User;
import com.mycompany.library.core.UserRegistry;
import com.mycompany.library.core.WebbLib;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Backing bean for the adminPage.xhtml page.
 * @author estelius
 */
@Named("admin")
@SessionScoped
public class AdminPageBB implements Serializable {
    
    private static final WebbLib library = WebbLib.INSTANCE;
    
    private String username;
    private String email;
    private String password;
    private double feesOwed;
    
    // Defaul constructor.
    public AdminPageBB() {}
    
    public String action() {
        return "adminPages?faces-redirect=true";
    }
    
    public List<User> getAllUsers() {
        return library.getUsers().getAll();
    }
    
    // Create and add a new user.
    public void createUser() {
        library.getUsers().add(new User(username, password, email, feesOwed));
    }
    
    // Remove an existing user.
    public void removeUser(Long id) {
        library.getUsers().remove(id);
    }
    
    // Edit an existing user.
    public void editUser(Long id, String username, String email, String password, double feesOwed) {
        User user = library.getUsers().find(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setFeesOwed(feesOwed);
        System.out.println("Username: " + user.getUsername() + "xaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        library.getUsers().add(new User(username, password, email, feesOwed));
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return username;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public double getFeesOwed() {
        return feesOwed;
    }
    
    public void setFeesOwed(double feesOwed) {
        this.feesOwed = feesOwed;
    }
}

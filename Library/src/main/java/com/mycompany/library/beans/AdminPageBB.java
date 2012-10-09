package com.mycompany.library.beans;

import com.mycompany.library.core.User;
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
    
    private Long editId;
    private String editUsername;
    private String editEmail;
    private String editPassword;
    private double editFeesOwed;
    
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
    
    public void prepareEdit(Long id, String username, String email, String password, double feesOwed) {
        editId = id;
        editUsername = username;
        editEmail = email;
        editPassword = password;
        editFeesOwed = feesOwed;
    }
    
    // Edit an existing user.
    public void editUser() {
        User user = library.getUsers().find(editId);
        user.setUsername(editUsername);
        user.setEmail(editEmail);
        user.setPassword(editPassword);
        user.setFeesOwed(editFeesOwed);
        library.getUsers().update(user);
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
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

    public Long getEditId() {
        return editId;
    }
    
    public void setEditId(Long editId) {
        this.editId = editId;
    }
    
    public String getEditUsername() {
        return editUsername;
    }

    public void setEditUsername(String editUsername) {
        this.editUsername = editUsername;
    }

    public String getEditEmail() {
        return editEmail;
    }

    public void setEditEmail(String editEmail) {
        this.editEmail = editEmail;
    }

    public String getEditPassword() {
        return editPassword;
    }

    public void setEditPassword(String editPassword) {
        this.editPassword = editPassword;
    }

    public double getEditFeesOwed() {
        return editFeesOwed;
    }

    public void setEditFeesOwed(double editFeesOwed) {
        this.editFeesOwed = editFeesOwed;
    }
}

package com.mycompany.library.beans;

import com.mycompany.library.core.User;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Backing bean for the adminPage.xhtml page.
 * @author estelius
 */
@Named("admin")
@SessionScoped
public class AdminPageBB implements Serializable {
    
    private UserRegistryBean urb;
    
    @NotNull
    @Size(min = 3, max = 8)
    private String username;
    @NotNull
    @Pattern(regexp = "^[_a-zA-Z0-9]+(\\.[_a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
             message = "Provided email is not valid.")
    private String email;
    @NotNull
    @Size(min = 5, max = 20)
    private String password;
    @NotNull
    @DecimalMin(value = "0")
    private double feesOwed;
    @NotNull
    private boolean isLib;
    
    @NotNull
    private Long editId;
    @NotNull
    @Size(min = 3, max = 8)
    private String editUsername;
    @NotNull
    @Pattern(regexp = "^[_a-zA-Z0-9]+(\\.[_a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
             message = "Provided email is not valid.")
    private String editEmail;
    @NotNull
    @Size(min = 5, max = 20)
    private String editPassword;
    @NotNull
    @DecimalMin(value = "0")
    private double editFeesOwed;
    
    // Defaul constructor.
    public AdminPageBB() {}
    
    @Inject
    public AdminPageBB(UserRegistryBean urb) {
        this.urb = urb;
    }
    
    public String action() {
        return "adminPages?faces-redirect=true";
    }
    
    public List<User> getAllUsers() {
        return urb.getAll();
    }
    
    // Create and add a new user.
    public void createUser() {
        urb.add(new User(username, password, email, feesOwed));
    }
    
    // Remove an existing user.
    public void removeUser(Long id) {
        urb.remove(id);
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
        User user = urb.find(editId);
        user.setUsername(editUsername);
        user.setEmail(editEmail);
        user.setPassword(editPassword);
        user.setFeesOwed(editFeesOwed);
        urb.update(user);
    }
    
    public void userPlus(Long editId, boolean value){
        User user = urb.find(editId);
        user.setIsLibrarian(value);
        urb.update(user);
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

    public boolean isIsLib() {
        return isLib;
    }

    public void setIsLib(boolean isLib) {
        this.isLib = isLib;
    }
}

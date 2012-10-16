package com.mycompany.library.beans;

import com.mycompany.library.core.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Backing bean for the adminPage.xhtml page.
 * @author estelius
 */
@Named("admin")
@SessionScoped
public class AdminPageBB implements Serializable {
    
    private UserRegistryBean urb;
    @Resource(name = "mail/gusbriter@student.gu.se")
    private Session mailSession;
    
    private String username;
    private String email;
    private String password;
    private double feesOwed;
    private boolean isLib;
    
    private Long editId;
    private String editUsername;
    private String editEmail;
    private String editPassword;
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
    @Asynchronous
    public void sendEmail() {
        Message msg = new MimeMessage(mailSession);
        mailSession.setDebug(true);
        try {
            msg.setSubject("Library Test");
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress("eric_britsman@hotmail.com"));
            msg.setFrom(new InternetAddress("Admin@Library.com", "Bibliotek Online"));
            Address replyTo[] = { new InternetAddress("Admin@Library.com") }; // set here
            msg.setReplyTo(replyTo); 
            msg.setText("Test Email from Library");
            Transport.send(msg);
        } 
        catch (Exception e) {
            e.printStackTrace();
        } 
    }
}

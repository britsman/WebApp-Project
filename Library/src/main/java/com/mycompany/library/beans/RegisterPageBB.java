package com.mycompany.library.beans;

import com.mycompany.library.core.User;
import java.io.Serializable;
import java.util.Random;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Hannes
 */
@Named("register")
@ConversationScoped
public class RegisterPageBB implements Serializable {
    
    @Inject
    private Conversation convo;
    private UserRegistryBean users;
    private SessionBB loggedUser;
    private String username = "";
    private String email = "";
    private String password = "";
    private String confirmPassword = "";
    private String redirect = null;
    private Long generatedCode;
    private Long inputCode;
    private boolean triedRegister = false;

    public RegisterPageBB() {}
    @Inject
    public RegisterPageBB(UserRegistryBean users, UserPageBB privateUserBean, SessionBB loggedUser) {
        this.users = users;
        this.loggedUser = loggedUser;
    }
    public void registerUser() {
        if (convo.isTransient()) {
            convo.begin();
        }
        if (!triedRegister) {
            if (checkUser()) {
                if (checkPassword()) {
                    generatedCode = new Long(new Random().nextInt(100));
                    users.sendRegCode(email, generatedCode);
                    triedRegister = true;
                }
            }
        }
        else{
            completeRegistration();
        }
    }
    public void completeRegistration(){
        if (checkUser() == true && generatedCode.equals(inputCode)) {
            System.out.println("True");
                User newUser = new User(username, password, email, 0.0);
                newUser = users.update(newUser);
                loggedUser.setLoggedInUser(newUser);              
                redirect = "userPage?faces-redirect=true";
        }
        else{
            //visa att registration failed (koden var fel)
            System.out.println("False");
            redirect = "login?faces-redirect=true";
        }
        clear();
    }
    
    public String access(){
        return redirect;
    }

    public boolean checkUser() {
        if (users.getByUserName(username) == null) {
            return true;
        }
        return false;
    }

    public boolean checkPassword() {
        if (password.contentEquals(confirmPassword)) {
            return true;
        }
        return false;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public Long getInputCode() {
        return inputCode;
    }

    public void setInputCode(Long inputCode) {
        this.inputCode = inputCode;
    }
    public String closeConversation() {
        if (!convo.isTransient()) {
            convo.end();
        }
        try {
            return redirect; // Go back
        } catch (Exception e) {
            // Not implemented
            //return "error?faces-redirect=true&amp;cause=" + e.getMessage();
            return null;
        }
    }
    @PreDestroy  // MUST HAVE back button etc.
    public void destroy() {
        if (convo != null) {
            if (!convo.isTransient()) {
                convo.end();
            }
        }
    }
    private void clear() {
        triedRegister = false;
        username = "";
        email = "";
        password = "";
        confirmPassword = "";
        inputCode = null;
    }
}

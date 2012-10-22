package com.mycompany.library.beans;

import com.mycompany.library.core.User;
import com.mycompany.library.core.UserRegistry;
import com.mycompany.library.core.WebbLib;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author user
 */
@ApplicationScoped
public class UserRegistryBean implements Serializable{
    UserRegistry users = WebbLib.INSTANCE.getUsers();
    EmailBean emailBean;
    
    public UserRegistryBean(){}
    @Inject
    public UserRegistryBean(EmailBean emailBean){
        this.emailBean = emailBean;
    }
    
    public List<User> getAll(){
        return users.getAll();
    }
    public void add(User user){
        users.add(user);
    } 
    public void remove(Long id){
        users.remove(id);
    }
    public User update(User user){
        return users.update(user);
    }
    public User find(Long id){
        return users.find(id);
    }
    public User getByUserName(String name){
        return users.getByUsername(name);
    }
    public void sendReminders(){
        for(User user: users.getAll()){
            if(user.getBorrowedItems().size()> 0){
                emailBean.sendReminder(user);
            }
        }
        System.out.println("!!!!!\n");
    }
    public void sendFees(){
        for (User user : users.getAll()) {
            if(user.getBorrowedItems().size()> 0){
                emailBean.sendFee(user);
            }
        }
    }
    public void sendRegCode(String email, Long code){
        emailBean.sendRegCode(email, code);
    }
}

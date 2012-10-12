package com.mycompany.library.beans;

import com.mycompany.library.core.User;
import com.mycompany.library.core.UserRegistry;
import com.mycompany.library.core.WebbLib;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author user
 */
@SessionScoped
public class UserRegistryBean implements Serializable{
    UserRegistry users = WebbLib.INSTANCE.getUsers();
    
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
}

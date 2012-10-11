/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.beans;

import com.mycompany.library.core.User;
import com.mycompany.library.core.UserRegistry;
import com.mycompany.library.core.WebbLib;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author user
 */
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
    public void find(Long id){
        users.find(id);
    }
    public void getByUserName(String name){
        users.getByUsername(name);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.beans;

import com.mycompany.library.core.Creator;
import com.mycompany.library.core.CreatorCollection;
import com.mycompany.library.core.WebbLib;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author user
 */
@SessionScoped
public class CreatorBean implements Serializable{
    CreatorCollection creators = WebbLib.INSTANCE.getCreators();
      
    public List<Creator> getAll(){
        return creators.getAll();
    }
    public void add(Creator creator){
        creators.add(creator);
    } 
    public void remove(Long id){
        creators.remove(id);
    }
    public Creator update(Creator creator){
        return creators.update(creator);
    }
    public void find(Long id){
        creators.find(id);
    }
    public void getByName(String name){
        creators.getByName(name);
    }
}
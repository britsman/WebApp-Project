package com.mycompany.library.beans;

import com.mycompany.library.core.Creator;
import com.mycompany.library.core.CreatorCollection;
import com.mycompany.library.core.WebLib;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author user
 */
@ApplicationScoped
public class CreatorBean implements Serializable{
    CreatorCollection creators = WebLib.INSTANCE.getCreators();
    
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
    public Creator find(Long id){
        return creators.find(id);
    }
    public Creator getByName(String name){
        return creators.getByName(name);
    }
}

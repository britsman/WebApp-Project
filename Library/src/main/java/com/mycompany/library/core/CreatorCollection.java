package com.mycompany.library.core;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sjoholmf
 */
public class CreatorCollection extends Controller<Creator, Long> {
    
    public CreatorCollection(String puName){
        super(Creator.class, puName);
    }
    public List<Creator> getByName(String name) {
        List<Creator> found = new ArrayList<>();
        for (Creator c : getAll()) {
            if (c.getName().equals(name)) {
                found.add(c);
            }
        }
        return found;
    }
    
}

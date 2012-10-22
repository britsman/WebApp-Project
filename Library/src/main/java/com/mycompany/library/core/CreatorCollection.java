package com.mycompany.library.core;

/**
 *  Class representing collection of all item creators.
 */
public class CreatorCollection extends Controller<Creator, Long> {
    
    public CreatorCollection(String puName){
        super(Creator.class, puName);
    }
    //There cannot be several creators with the same name (in our current model)
    public Creator getByName(String name) {
        Creator found = null;
        for (Creator c : getAll()) {
            if (c.getName().equals(name)) {
                found = c;
                break;
            }
        }
        return found;
    }
}

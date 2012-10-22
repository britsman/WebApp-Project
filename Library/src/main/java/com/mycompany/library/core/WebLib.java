package com.mycompany.library.core;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * "Main" singleton for the online library project.
 */
public enum WebLib {

    INSTANCE;
    
    private final EntityManagerFactory emr = Persistence.createEntityManagerFactory("lib_pu");
    private final ItemCollection items = new ItemCollection ("lib_pu");
    private final UserRegistry users = new  UserRegistry("lib_pu");
    private final CreatorCollection creators = new CreatorCollection("lib_pu");
    private final QueryProccessor queryProccessor = new QueryProccessor(emr);

    public ItemCollection getItems() {
        return items;
    }

    public UserRegistry getUsers() {
        return users;
    }

    public CreatorCollection getCreators() {
        return creators;
    }

    public EntityManagerFactory getEmr() {
        return emr;
    }

    public QueryProccessor getQueryProccessor() {
        return queryProccessor;
    }
}

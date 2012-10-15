/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.core;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
public enum WebbLib {

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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.beans;

import com.mycompany.library.core.BorrowedItem;
import com.mycompany.library.core.QueryProccessor;
import com.mycompany.library.core.WebbLib;
import java.io.Serializable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.ejb.Asynchronous;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Eric
 */
@Named("AutoCheck")
@ApplicationScoped
public class AutomaticSystemBean implements Serializable{

    private ScheduledExecutorService scheduler;
    @Inject
    private UserRegistryBean users;
    private boolean started = false;

    public AutomaticSystemBean() {
    }
    
    public void tryStart(){
        if(!started){
            started = true;
            automaticChecker();
        }
    }

    private void automaticChecker() {
            scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Schemalagd Kontroll\n");
                    checkBorrowedItems();
                    //sendReminders(); //Kräver mail server setup.
                    //sendFees();
                }
            }, 1, 20, TimeUnit.MINUTES);
    }

    private void checkBorrowedItems() {
        QueryProccessor q = WebbLib.INSTANCE.getQueryProccessor();
        for (BorrowedItem item : q.getAllBorrowedItems()) {
            if (!item.isCollected()) {
                item.checkCollectDatePassed();
            }
        }
        try{
        System.out.println(users);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sendReminders() {
        users.sendReminders();
    }

    private void sendFees() {
        users.sendFees();
    }
}

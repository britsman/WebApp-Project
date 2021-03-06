package com.mycompany.library.beans;

import com.mycompany.library.core.BorrowedItem;
import com.mycompany.library.core.QueryProccessor;
import com.mycompany.library.core.WebLib;
import java.io.Serializable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Class for automatically checking and sending notifications to users about book
 * books to turn in, books to pay fees for etc. Also checks if any BorrowedItem
 * is still !collected after a certain number of days (and if true releases it from
 * borrowed status).
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
                    sendReminders(); 
                    sendFees();
                }
            }, 1, 20, TimeUnit.MINUTES);
    }

    private void checkBorrowedItems() {
        QueryProccessor q = WebLib.INSTANCE.getQueryProccessor();
        for (BorrowedItem item : q.getAllBorrowedItems()) {
            if (!item.isCollected()) {
                item.checkCollectDatePassed();
            }
        }
    }

    private void sendReminders() {
        users.sendReminders();
    }

    private void sendFees() {
        users.sendFees();
    }
}

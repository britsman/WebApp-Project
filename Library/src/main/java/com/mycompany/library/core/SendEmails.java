/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author user
 */
public class SendEmails {
    
    @Resource(name = "mail/gusbriter@student.gu.se")
    private static Session mailSession;
    public static final Long milliPerDay = 86400000L;
    
    @Asynchronous
    public static void sendReminder(User user){
        Message msg = new MimeMessage(mailSession);
        mailSession.setDebug(true);
        List<BorrowedItem> temp = user.getBorrowedItems();
        List<String> booksToReturn = new ArrayList<>();
        Date tempDate = new Date();

        for (int i = 0; i < temp.size(); i++) {
            Long lastDay = (temp.get(i).getLoanDate().getTime()
            + (temp.get(i).getItem().getLoan_period() * milliPerDay));
            if ((lastDay - tempDate.getTime()) / milliPerDay < 2) {
                booksToReturn.add(temp.get(i).getItem().getTitle() + "\n");
            }
        }
        if(booksToReturn.size() > 0) {
            try {
                msg.setSubject("Online-Biblioteket: Böter");
                msg.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
                msg.setFrom(new InternetAddress("noreply@onlinebiblo.se", "Online-Biblioteket"));
                Address replyTo[] = {new InternetAddress("noreply@onlinebiblo.se")};
                msg.setReplyTo(replyTo);
                msg.setText("Inom två dagar så måste du lämna in: " + booksToReturn);
                Transport.send(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Asynchronous
    public static void sendFee(User user) {
        Message msg = new MimeMessage(mailSession);
        mailSession.setDebug(true);
        List<BorrowedItem> temp = user.getBorrowedItems();
        List<String> lateBooks = new ArrayList<>();
        int totalFee = 0;
        Date tempDate = new Date();

        for (int i = 0; i < temp.size(); i++) {
            Long lastDay = (temp.get(i).getLoanDate().getTime()
                    + (temp.get(i).getItem().getLoan_period() * milliPerDay));
            if (lastDay < tempDate.getTime()) {
                int fee = (int) ((tempDate.getTime() - lastDay) / milliPerDay)
                        * temp.get(i).getItem().getFee();
                lateBooks.add(temp.get(i).getItem().getTitle()
                        + " är försenad. bötern är: " + fee + "\n");
                totalFee += fee;
            }
        }
        if (lateBooks.size() > 0) {
            try {
                msg.setSubject("Online-Biblioteket: Böter");
                msg.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
                msg.setFrom(new InternetAddress("noreply@onlinebiblo.se", "Online-Biblioteket"));
                Address replyTo[] = {new InternetAddress("noreply@onlinebiblo.se")};
                msg.setReplyTo(replyTo);
                msg.setText("Dessa böcker är försenade: " + lateBooks
                        + "Den totala bötern är: " + totalFee);
                Transport.send(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Asynchronous
    public static void sendRegCode(String email, Long code){
        Message msg = new MimeMessage(mailSession);
        mailSession.setDebug(true);
        try {
            msg.setSubject("Online-Biblioteket: Registrering");
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            msg.setFrom(new InternetAddress("noreply@onlinebiblo.se", "Online-Biblioteket"));
            Address replyTo[] = { new InternetAddress("noreply@onlinebiblo.se") }; 
            msg.setReplyTo(replyTo); 
            msg.setText("För att få ditt användarnamn registrerat, så måste du skriva in koden " +
            "nedanför i registreringskodfältet.\n Koden är: " + code);
            Transport.send(msg);
        } 
        catch (Exception e) {
            e.printStackTrace();
        } 
    }
}

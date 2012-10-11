/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.core;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Eric
 */
@Embeddable
public class QueElement implements Serializable{
    private int position;
    private User user;

    public QueElement() {
    }
    public QueElement(int p, User u) {
        this.position = p;
        this.user = u;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

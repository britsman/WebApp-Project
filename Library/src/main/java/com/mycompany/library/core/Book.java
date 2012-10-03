/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.core;

import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author Eric
 */
@Entity
public class Book extends Item {
    private String publisher;
    private List<Author> Authors;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthors() {
        return Authors;
    }

    public void setAuthors(List<Author> Authors) {
        this.Authors = Authors;
    }
}

package com.mycompany.library.core;

import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Eric
 */
@Entity
@Table(name="BOOKS")
@DiscriminatorValue("B")
public class Book extends Item implements Serializable{

    private int pageNum;
    
    
    public Book() {}

    public Book(String id, String title, List<Creator> creators, String publisher, 
    String language,  int year, int pageNum, String genre, String image, String description, 
    int quantity, int loan_period, int fee) {
        super(id, title,publisher, creators, language, year, genre, image,description, 
        quantity, loan_period, fee);
        this.pageNum = pageNum;
    }
    public Book(String id, String title, List<Creator> creators, String publisher, 
    String language,  int year, int pageNum, String genre, int quantity) {
        super(id, title,publisher, creators, language, year, genre, quantity);
        this.pageNum = pageNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}

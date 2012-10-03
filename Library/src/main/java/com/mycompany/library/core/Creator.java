/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.core;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Superclass for authors, musicians, bands etc. Due to @MappedSuperclass, all 
 * attributes that aren't unique to a certain type of creator can be placed here.
 * @author Eric
 */
@MappedSuperclass
public class Creator implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    /*Could be either person or a bandname, no need to divide into first/last 
     * name, just use whole name for string comparisons.*/
    private String name;
}

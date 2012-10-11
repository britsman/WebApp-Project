package com.mycompany.library.beans;

import com.mycompany.library.core.Creator;
import com.mycompany.library.core.Item;
import com.mycompany.library.core.WebbLib;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Backing bean for the UserPlusPage.xhtml page.
 * @author Fredrik
 */
@Named("userPlus")
@RequestScoped
public class UserPlusPageBB {
    
    private static final WebbLib library = WebbLib.INSTANCE;
    
    private String id;
    private String title;
    private String creators;
    private String image;
    private String description;
    private int loan_period;
    private int fee;
    private int year_release;
    private String genre;
    private String language;
    private int quantity;
    
    // Default constructor.
    public UserPlusPageBB() {}
    
    public List<Item> getAll() {
        return library.getItems().getAll();
    }
    
    public void createItem() {
        List<Creator> cs = new ArrayList<Creator>();
        String[] creatorStrings = this.creators.split(",");
        for (String s: creatorStrings) {
            cs.add(new Creator(s));
        }
        library.getItems().add(new Item(id, title, cs, language, year_release,
                genre, image, description, quantity, loan_period, fee));
    }
    
    public void updateItem(String id) {
        Item i = library.getItems().find(id);
        
        library.getItems().update(i);
    }
    
    public void removeItem(String id) {
        library.getItems().remove(id);
    }

    public String action() {
        return "userPlusPage?faces-redirect=true";
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreators() {
        return creators;
    }

    public void setCreators(String creators) {
        this.creators = creators;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLoan_period() {
        return loan_period;
    }

    public void setLoan_period(int loan_period) {
        this.loan_period = loan_period;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getYear_release() {
        return year_release;
    }

    public void setYear_release(int year_release) {
        this.year_release = year_release;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

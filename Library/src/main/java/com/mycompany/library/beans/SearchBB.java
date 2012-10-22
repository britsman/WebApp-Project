package com.mycompany.library.beans;

import com.mycompany.library.core.Item;
import com.mycompany.library.core.QueryProccessor;
import com.mycompany.library.core.WebLib;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Hannes
 */
@Named("search")
@ConversationScoped
public class SearchBB implements Serializable {
    
    @Inject
    private Conversation convo;
   
    private SessionBB session;
    private String id, title, creator, publisher, description, language, genre,type;
    private String topSearch;
    private int fromYear, toYear;
    private boolean inStock;
        
    @Inject
    private UserRegistryBean users;

    public SearchBB() {
    }
    @Inject
    public SearchBB(UserRegistryBean users, SessionBB session) {
        this.users = users;
        this.session = session;
    }

    public void searchAll() {
        checkConversation();
        QueryProccessor query = WebLib.INSTANCE.getQueryProccessor();
        if (!topSearch.equals("")) {
            session.setSearchResult(query.searchAll(topSearch));
        }
    }

    public void searchAdvanced() {
        checkConversation();
        QueryProccessor query = WebLib.INSTANCE.getQueryProccessor();
        genre=null;
        language=null;
        type=null;
        session.setSearchResult(query.searchItem(id, title, creator, publisher, description, fromYear, toYear, inStock, language, genre));

    }
    
     public void borrowOrReserve(Item item){
        checkConversation();
        if(item.getQuantity() > 0){
            session.getLoggedInUser().tryBorrowItem(item);
        }
        else{
            session.getLoggedInUser().tryReserveItem(item);
        }
        session.setLoggedInUser(users.update(session.getLoggedInUser()));
    }
    
     public void bookMark(Item item){
        if(!session.getLoggedInUser().getBookmarkedItems().contains(item)){
            session.getLoggedInUser().setBookmarkedItems(item);
        }        
        session.setLoggedInUser(users.update(session.getLoggedInUser()));
    }
    
    public boolean linkVisible(){  
        return session.getLoggedInUser() != null;
    }
    
    public String bookMarkImg(Item item){
        if(session.getLoggedInUser().getBookmarkedItems().contains(item)){
            return "/resources/img/star_full.png";
        }
        return "/resources/img/star_none.png";
    }
    
    
    public void checkConversation(){
        if (convo.isTransient()) {
            convo.begin();
        }
    }
    
    public String redirectToSelf() {
        try {
            return "searchPage?faces-redirect=true"; // Go back
        } catch (Exception e) {
            // Not implemented
            //return "error?faces-redirect=true&amp;cause=" + e.getMessage();
            return null;
        }
    }
    public String redirectToBookPage() {
        try {
            return "bookPage?faces-redirect=true"; // Go back
        } catch (Exception e) {
            // Not implemented
            //return "error?faces-redirect=true&amp;cause=" + e.getMessage();
            return null;
        }
    }
    @PreDestroy  // MUST HAVE back button etc.
    public void destroy() {
        if (convo != null) {
            if (!convo.isTransient()) {
                convo.end();
            }
        }
    }
    
    public String getTopSearch() {
        return topSearch;
    }

    public void setTopSearch(String topSearch) {
        this.topSearch = topSearch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id.isEmpty()) {
            this.id = null;
        } else {
            this.id =id;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.isEmpty()) {
            this.title = null;
        } else {
            this.title = title;
        }
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        if (creator.isEmpty()) {
            this.creator = null;
        } else {
            this.creator = creator;
        }
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        if (publisher.isEmpty()) {
            this.publisher = null;
        } else {
            this.publisher = publisher;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.isEmpty()) {
            this.description = null;
        } else {
            this.description = description;
        }
    }

    public int getFromYear() {
        return fromYear;
    }

    public void setFromYear(int fromYear) {
        this.fromYear = fromYear;
    }

    public int getToYear() {
        return toYear;
    }

    public void setToYear(int toYear) {
        this.toYear = toYear;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
            this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
            this.genre = genre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

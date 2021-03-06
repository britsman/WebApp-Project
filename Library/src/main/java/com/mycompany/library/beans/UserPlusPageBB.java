package com.mycompany.library.beans;

import com.mycompany.library.core.Book;
import com.mycompany.library.core.BorrowedItem;
import com.mycompany.library.core.Creator;
import com.mycompany.library.core.Item;
import com.mycompany.library.core.QueElement;
import com.mycompany.library.core.QueryProccessor;
import com.mycompany.library.core.ReservedItem;
import com.mycompany.library.core.User;
import com.mycompany.library.core.WebLib;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Backing bean for UserPlusPage.xhtml 
 */
@Named("userPlus")
@SessionScoped
public class UserPlusPageBB implements Serializable {

    // Collections
    private CreatorBean creatorCollection;
    private ItemBean itemCollection;
    private UserRegistryBean users;
    private SessionBB session;
    
    // Field for adding new items.
    private String id;
    private String title;
    private String creators;
    private String publisher;
    private String image;
    private String description;
    private int pageNum;
    private int loan_period;
    private int fee;
    private int year_release;
    private String genre;
    private String language;
    private int quantity;   
    
    // Fields for editing existing items.
    private String editId;
    private String editTitle;
    private String editCreators;
    private String editPublisher;
    private String editImage;
    private String editDescription;
    private int editPageNum;
    private int editLoan_period;
    private int editFee;
    private int editYear_release;
    private String editGenre;
    private String editLanguage;
    private int editQuantity;
    
    private List<BorrowedItem> isbnSearchResult;
    private String topSearch;
    private String show_content ="";
    private boolean edit = false;
    private String stdSort="";
    
    // Default constructor.
    public UserPlusPageBB() {}
    
    @Inject
    public UserPlusPageBB(CreatorBean creatorCollection, ItemBean itemCollection, UserRegistryBean users, SessionBB session) {
        this.creatorCollection = creatorCollection;
        this.itemCollection = itemCollection;
        this.users = users;
        this.session = session;
        show_content ="content_1";
    }
    
    public List<Item> getAll() {
        List<Item> list = itemCollection.getAll();
        String sortBy="title";
        switch(sortBy){
            case "title": 
                //Collections.sort(list,Item.ItemTitleComparator); 
                break;
            case "author":
                //Collections.sort(list,Item.ItemAuthorComparator);
                break;
            case "year" :
                //Collections.sort(list);
                break;
            case "isbn":
                //Collections.sort(list,Item.ItemISBNComparator);
                break;
            default:
                break;
        }
        return list;
    }
      
    public void createItem() {
        
        List<Creator> cs = new ArrayList<>();
        String[] creatorStrings = this.creators.split(",");
        for (String name: creatorStrings) {
            Creator creator = creatorCollection.getByName(name);
            if(creator == null){
                creator = new Creator(name);
            }
            cs.add(creator);
        }
        itemCollection.update(new Book(id, title, cs, publisher, language, year_release, pageNum,
                genre, image, description, quantity, loan_period, fee));
        id="";
        title="";
        cs.clear();
        publisher="";
        language="";
        year_release=0;
        pageNum=0;
        genre="";
        image="";
        description="";
        quantity=0;
        loan_period=0;
        fee=0;
       
    }
    

    public void prepareEdit(Item item) {
        editId = item.getId();
        editTitle = item.getTitle();
        editCreators = item.getCreatorNames();
        editPublisher = item.getPublisher();
        editImage = item.getImage();
        editDescription = item.getDescription();
        editPageNum = 3;
        editLoan_period = item.getLoan_period();
        editFee = item.getFee();
        editYear_release = item.getYear();
        editGenre = item.getGenre();
        editLanguage = item.getLanguage();
        editQuantity = item.getQuantity();
        edit=true;
    }
    
    public void updateItem(String id) {
        Book b = (Book) itemCollection.find(id);
        b.setTitle(editTitle);
        List<Creator> cs = new ArrayList<>();
        String[] creatorStrings = this.editCreators.split(",");
        for (String s: creatorStrings) {
            Creator creator = new Creator(s);
            cs.add(creator);
            creatorCollection.update(creator);
        }
        b.setCreators(cs);
        b.setPublisher(editPublisher);
        b.setImage(editImage);
        b.setDescription(editDescription);
        b.setPageNum(editPageNum);
        b.setLoan_period(editLoan_period);
        if(editFee > 0){
            b.setFee(editFee);
        }       
        if(editYear_release > 0){
            b.setYear(editYear_release);
        }
        b.setGenre(editGenre);
        b.setLanguage(editLanguage);
        if(editQuantity > b.getQuantity() && b.getQuantity() == 0){
            QueryProccessor query = WebLib.INSTANCE.getQueryProccessor();
            ReservedItem tempReserved = query.findReservedItem(b);
            while(tempReserved != null && editQuantity > 0){                   
                tempReserved.getItem().setQuantity(editQuantity);
                tempReserved.firstInQueBorrow();
                tempReserved = query.findReservedItem(tempReserved.getItem());
                editQuantity--;
            }                              
            b.setQuantity(editQuantity);
        }
        else if(editQuantity >= 0){
            b.setQuantity(editQuantity);
        }
        itemCollection.update(b);
        edit=false;
        session.setLoggedInUser(users.find(session.getLoggedInUser().getId()));
    }
    
    public void removeItem(String id) {
        QueryProccessor query = WebLib.INSTANCE.getQueryProccessor();
        ReservedItem tempReserved = query.findReservedItem(itemCollection.find(id));
        
        if (tempReserved != null) {
            for (QueElement que : tempReserved.getQue()) {
                tempReserved.updatePositions(que.getUser());
                users.update(que.getUser());
            }
            query.removeReservedItem(tempReserved.getId());
        }   
        for (BorrowedItem item : getAllBorrowedItems()) {
            if (item.getItem().getId().equals(id)) {
                item.getUser().removeBorrowedItem(item);
                users.update(item.getUser());
            }
        }
        for (User user : users.getAll()) {
            user.removeBookmarkedItem(id);
            user = users.update(user);
        }    
        session.setLoggedInUser(users.find(session.getLoggedInUser().getId()));
        itemCollection.remove(id);
    }

    public String action(String tab) {
        show_content = tab;
        return "userPlusPage?faces-redirect=true";
    }
    
    public List<BorrowedItem> getAllBorrowedItems(){
        QueryProccessor q = WebLib.INSTANCE.getQueryProccessor();
        return q.getAllBorrowedItems();
    }
    
    public List<BorrowedItem> getAllBorrowedItemByISBN(String isbn){
        List<BorrowedItem> tmp = new ArrayList<>();
        QueryProccessor query = WebLib.INSTANCE.getQueryProccessor();
        for(BorrowedItem b: query.getAllBorrowedItems()){
            
            if(b.getItem().getId().equals(isbn)){
                tmp.add(b); 
            }
        }
        isbnSearchResult = tmp;
        return tmp;
    }
    
    public void checkCollectDatePassed(){
        List<BorrowedItem> temp = this.getAllBorrowedItems();
        for(int i = 0; i < temp.size(); i++){
            temp.get(i).checkCollectDatePassed();
        }
    }

    public void checkInItem(BorrowedItem borrowedItem) {
        borrowedItem.getUser().removeBorrowedItem(borrowedItem);
        users.update(borrowedItem.getUser());
        QueryProccessor query = WebLib.INSTANCE.getQueryProccessor();
        ReservedItem tempReserved = query.findReservedItem(borrowedItem.getItem());
        if (tempReserved != null) {
            tempReserved.firstInQueBorrow();
        }       
        session.setLoggedInUser(users.find(session.getLoggedInUser().getId()));
    }

    public void checkOutItem(BorrowedItem borrowedItem) {
        borrowedItem.setCollected(true);
        users.update(borrowedItem.getUser());
        session.setLoggedInUser(users.find(session.getLoggedInUser().getId()));
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getEditPublisher() {
        return editPublisher;
    }

    public void setEditPublisher(String editPublisher) {
        this.editPublisher = editPublisher;
    }

    public int getEditPageNum() {
        return editPageNum;
    }

    public void setEditPageNum(int editPageNum) {
        this.editPageNum = editPageNum;
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

    public String getEditId() {
        return editId;
    }

    public void setEditId(String editId) {
        this.editId = editId;
    }

    public String getEditTitle() {
        return editTitle;
    }

    public void setEditTitle(String editTitle) {
        this.editTitle = editTitle;
    }

    public String getEditCreators() {
        return editCreators;
    }

    public void setEditCreators(String editCreators) {
        this.editCreators = editCreators;
    }

    public String getEditImage() {
        return editImage;
    }

    public void setEditImage(String editImage) {
        this.editImage = editImage;
    }

    public String getEditDescription() {
        return editDescription;
    }

    public void setEditDescription(String editDescription) {
        this.editDescription = editDescription;
    }

    public int getEditLoan_period() {
        return editLoan_period;
    }

    public void setEditLoan_period(int editLoan_period) {
        this.editLoan_period = editLoan_period;
    }

    public int getEditFee() {
        return editFee;
    }

    public void setEditFee(int eidtFee) {
        this.editFee = eidtFee;
    }

    public int getEditYear_release() {
        return editYear_release;
    }

    public void setEditYear_release(int editYear_release) {
        this.editYear_release = editYear_release;
    }

    public String getEditGenre() {
        return editGenre;
    }

    public void setEditGenre(String editGenre) {
        this.editGenre = editGenre;
    }

    public String getEditLanguage() {
        return editLanguage;
    }

    public void setEditLanguage(String editLanguage) {
        this.editLanguage = editLanguage;
    }

    public int getEditQuantity() {
        return editQuantity;
    }

    public void setEditQuantity(int editQuantity) {
        this.editQuantity = editQuantity;
    }

    public List<BorrowedItem> getIsbnSearchResult() {
        return isbnSearchResult;
    }

    public void setIsbnSearchResult(List<BorrowedItem> isbnSearchResult) {
        this.isbnSearchResult = isbnSearchResult;
    }


    public String getTopSearch() {
        return topSearch;
    }

    public void setTopSearch(String topSearch) {
        this.topSearch = topSearch;
    }

    public String getShow_content() {
        return show_content;
    }

    public void setShow_content(String show_content) {
        this.show_content = show_content;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public String getStdSort() {
        return stdSort;
    }

    public void setStdSort(String stdSort) {
        this.stdSort = stdSort;
    }  
    
}

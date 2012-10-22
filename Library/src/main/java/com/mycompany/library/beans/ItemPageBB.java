package com.mycompany.library.beans;

import com.mycompany.library.core.Book;
import com.mycompany.library.core.Item;
import java.io.Serializable;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *Backing bean for bookPage.xhtml.
 */
@Named("SingleItem")
@ConversationScoped
public class ItemPageBB implements Serializable {

    @Inject
    private Conversation convo;
    private UserRegistryBean users;
    private SessionBB session;
    private Book book;
    private String redirectPage;

    public ItemPageBB() {
    }
    @Inject
    public ItemPageBB(UserRegistryBean users, SessionBB session) {
        this.users = users;
        this.session = session;
    }
    public void borrowOrReserve() {
        if (book.getQuantity() > 0) {
            session.getLoggedInUser().tryBorrowItem(book);
        } else {
            session.getLoggedInUser().tryReserveItem(book);
        }
        session.setLoggedInUser(users.update(session.getLoggedInUser()));
    }
    public void bookMark() {
        if (!session.getLoggedInUser().getBookmarkedItems().contains(book)) {
            session.getLoggedInUser().setBookmarkedItems(book);
            session.setLoggedInUser(users.update(session.getLoggedInUser()));
        }
    }
    public String buttonValue() {
        if (book.getQuantity() > 0) {
            return "Låna";
        } else {
            return "Reservera";
        }
    }
    public boolean buttonVisible() {
        return session.getLoggedInUser() != null;
    }
    public Book getBook() {
        return book;
    }
    public void bookListener(Item item, String redirectPage) {
        if (convo.isTransient()) {
            convo.begin();
        }
        this.book = (Book) item;
        this.redirectPage = redirectPage;
    }
    public String action() {
        if (!convo.isTransient()) {
            convo.end();
        }
        try {
            return this.redirectPage + "?faces-redirect=true"; // Go back
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
}

package com.mycompany.library.core;

import com.mycompany.library.core.BorrowedItem;
import com.mycompany.library.core.Item;
import com.mycompany.library.core.ReservedItem;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-10-18T16:07:58")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, Double> feesOwed;
    public static volatile SingularAttribute<User, Boolean> isLibrarian;
    public static volatile ListAttribute<User, BorrowedItem> borrowedItems;
    public static volatile SingularAttribute<User, String> username;
    public static volatile SingularAttribute<User, String> email;
    public static volatile ListAttribute<User, Item> bookmarkedItems;
    public static volatile SingularAttribute<User, String> password;
    public static volatile ListAttribute<User, ReservedItem> reservedItems;

}
package com.mycompany.library.core;

import com.mycompany.library.core.Item;
import com.mycompany.library.core.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-10-18T16:07:58")
@StaticMetamodel(BorrowedItem.class)
public class BorrowedItem_ { 

    public static volatile SingularAttribute<BorrowedItem, Long> id;
    public static volatile SingularAttribute<BorrowedItem, Boolean> collected;
    public static volatile SingularAttribute<BorrowedItem, Item> item;
    public static volatile SingularAttribute<BorrowedItem, Date> loanDate;
    public static volatile SingularAttribute<BorrowedItem, User> user;

}
package com.mycompany.library.core;

import com.mycompany.library.core.Item;
import com.mycompany.library.core.QueElement;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-10-18T16:07:58")
@StaticMetamodel(ReservedItem.class)
public class ReservedItem_ { 

    public static volatile SingularAttribute<ReservedItem, Long> id;
    public static volatile SingularAttribute<ReservedItem, Item> item;
    public static volatile ListAttribute<ReservedItem, QueElement> que;

}
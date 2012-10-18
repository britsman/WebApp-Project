package com.mycompany.library.core;

import com.mycompany.library.core.Item;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-10-18T16:07:58")
@StaticMetamodel(Creator.class)
public class Creator_ { 

    public static volatile SingularAttribute<Creator, Long> id;
    public static volatile ListAttribute<Creator, Item> items;
    public static volatile SingularAttribute<Creator, String> name;

}
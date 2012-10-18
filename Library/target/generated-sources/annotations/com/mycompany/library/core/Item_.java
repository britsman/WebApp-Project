package com.mycompany.library.core;

import com.mycompany.library.core.Creator;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-10-18T16:07:58")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, String> id;
    public static volatile ListAttribute<Item, Creator> creators;
    public static volatile SingularAttribute<Item, Integer> fee;
    public static volatile SingularAttribute<Item, String> genre;
    public static volatile SingularAttribute<Item, String> title;
    public static volatile SingularAttribute<Item, String> description;
    public static volatile SingularAttribute<Item, Integer> loan_period;
    public static volatile SingularAttribute<Item, Integer> year_released;
    public static volatile SingularAttribute<Item, String> image;
    public static volatile SingularAttribute<Item, Integer> quantity;
    public static volatile SingularAttribute<Item, String> language;

}
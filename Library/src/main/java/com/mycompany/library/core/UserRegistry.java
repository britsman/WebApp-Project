
package com.mycompany.library.core;

/**
 *
 * @author sjoholmf
 */
public class UserRegistry extends Controller<User, String> {
    
    public UserRegistry(String puName){
        super(User.class, puName);
    }    
}

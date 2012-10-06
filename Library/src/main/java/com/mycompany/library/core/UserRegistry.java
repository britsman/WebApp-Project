
package com.mycompany.library.core;
/**
 *
 * @author sjoholmf
 */
public class UserRegistry extends Controller<User, Long> {
    
    public UserRegistry(String puName){
        super(User.class, puName);
    }    
    //Usernames are also unique
    public User getByUsername(String name) {
        User found = null;
        for (User u : getAll()) {
            if (u.getUsername().equals(name)) {
                found = u;
                break;
            }
        }
        return found;
    }
}

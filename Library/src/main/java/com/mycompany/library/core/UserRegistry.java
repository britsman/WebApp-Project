
package com.mycompany.library.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sjoholmf
 */
public class UserRegistry extends Controller<User, Long> {
    
    public UserRegistry(String puName){
        super(User.class, puName);
    }    
    
        public List<User> getByUsername(String name) {
        List<User> found = new ArrayList<>();
        for (User u : getAll()) {
            if (u.getUsername().equals(name)) {
                found.add(u);
            }
        }
        return found;
    }
}

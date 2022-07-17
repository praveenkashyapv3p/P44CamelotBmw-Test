package com.camelot.p44camelotbmw.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersService  implements UserDetailsService {


    @Autowired
    UsersDBQuery usersDBQuery;

    @Override
    public UsersHelper loadUserByUsername(final String username) throws UsernameNotFoundException {
        UsersPojo usersPojo;
        try {
            usersPojo = usersDBQuery.getUserDetails(username);
            return new UsersHelper(usersPojo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
    }
}

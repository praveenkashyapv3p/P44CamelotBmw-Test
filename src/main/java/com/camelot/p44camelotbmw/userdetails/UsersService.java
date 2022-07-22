package com.camelot.p44camelotbmw.userdetails;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UserDetailsService {
    private static final Logger logger = LogManager.getLogger(UsersService.class);
    
    @Autowired
    UsersDBQuery usersDBQuery;
    
    @Override
    public UsersHelper loadUserByUsername(final String username) throws UsernameNotFoundException {
        UsersPojo usersPojo;
        try {
            usersPojo = usersDBQuery.getUserDetails(username);
            return new UsersHelper(usersPojo);
        } catch (Exception e) {
            logger.error(username + " incorrect " + e);
            throw new UsernameNotFoundException("User " + username + " incorrect");
        }
    }
}
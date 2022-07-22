package com.camelot.p44camelotbmw.userdetails;

import com.camelot.p44camelotbmw.constants.UserList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class UsersDBQuery {
    private static final Logger logger = LogManager.getLogger(UsersDBQuery.class);
    
    public UsersPojo getUserDetails(String username) {
    
        Collection<GrantedAuthority> listOfGrantedAuthorities = new ArrayList<>();
        UsersPojo user = new UsersPojo();
        List<UsersPojo> list1 = new ArrayList<>();
        try {
            UserList userList = new UserList();
            Map<String, String> stCod = userList.getUser();
            user.setUsername(username);
            user.setPassword(stCod.get(username));
            list1.add(user);
        } catch (Exception e) {
            logger.error("Username or Password incorrect " + e);
        }
        if (list1.size() > 0) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
            listOfGrantedAuthorities.add(grantedAuthority);
            list1.get(0).setListOfGrantedAuthorities(listOfGrantedAuthorities);
            return list1.get(0);
        }
        return null;
    }
}
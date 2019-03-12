package ru.rt.astral.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.rt.astral.model.User;
import ru.rt.astral.repository.AbstractUserDAO;

@Service
public class UserService {
    
    public final static byte OK = 0;
    
    public final static byte USER_EXISTS = 1;
    
    @Autowired
    @Qualifier("jdbcUserDAO")
    private AbstractUserDAO userDAO;
    
    public byte createUser(User user){
        if (userDAO.loadUserByUsername(user.getLogin()) != null)
            return USER_EXISTS;
        userDAO.createUser(user);
        return OK;
    }
    
}

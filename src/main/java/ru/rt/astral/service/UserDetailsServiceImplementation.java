package ru.rt.astral.service;

import java.util.ArrayList;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.rt.astral.repository.AbstractUserDAO;

@Service(value = "userDetailsService")
public class UserDetailsServiceImplementation implements UserDetailsService{
    
    @Autowired
    @Qualifier("jdbcUserDAO")
    private AbstractUserDAO userDAO;
    
    @Getter
    private ru.rt.astral.model.User user;

    @Override
    public UserDetails loadUserByUsername(String login) 
            throws UsernameNotFoundException {
        user = userDAO.loadUserByUsername(login);
        if (user == null)
            throw new UsernameNotFoundException(""
                    + "Ошибка входа для логина " 
                    + login);
        return new User(user.getLogin(), user.getPassword(),new ArrayList<>());
    }
    
}

package ru.rt.astral.repository;

import ru.rt.astral.model.User;

public abstract class AbstractUserDAO {
    
    public abstract void createUser(User user);
    
    public abstract User loadUserByUsername(String login);
    
}

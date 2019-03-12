package ru.rt.astral.model;

import lombok.Data;

@Data
public class User extends AbstractModel{
    
    private String login;
    
    private String password;
    
}

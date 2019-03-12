package ru.rt.astral.model;

import lombok.Data;

@Data
public class Note extends AbstractModel{
    
    private String message;
    
    private User author;
    
    private byte[] image;
    
}

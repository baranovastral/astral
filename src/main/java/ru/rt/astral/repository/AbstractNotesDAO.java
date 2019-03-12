package ru.rt.astral.repository;

import java.util.List;
import ru.rt.astral.model.Note;
import ru.rt.astral.model.User;

public abstract class AbstractNotesDAO {
    
    public abstract List<Note> findAllByUserLikeMessage(
            User user,
            String message);
    
    public abstract void create(Note note);
    
    public abstract void delete(long id);
    
    public abstract Note findOne(long id);
    
    public abstract byte[] getNoteImage(long id);
    
}

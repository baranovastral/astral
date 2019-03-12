package ru.rt.astral.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.rt.astral.model.Note;
import ru.rt.astral.model.User;
import ru.rt.astral.repository.AbstractNotesDAO;

@Service
public class NotesService{
    
    @Autowired
    @Qualifier("jdbcNotesDAO")
    private AbstractNotesDAO notesDAO;
    
    @Autowired
    private UserDetailsServiceImplementation userDetailsService;
    
    public void createNote(Note note){
        notesDAO.create(note);
    }
    
    public List<Note> findAllLikeMessage(User user, String message) 
            throws NullPointerException{
        return notesDAO.findAllByUserLikeMessage(user,message); 
    }
    
    public boolean delete(long id){
        if ((userDetailsService.getUser() == null)
                ||(userDetailsService.getUser().getId() 
                != notesDAO.findOne(id).getAuthor().getId()))
            return false;
        notesDAO.delete(id);
        return true;
    }
    
    public Note findOne(long id){
        return notesDAO.findOne(id);
    }
    
    public byte[] getImage(long id){
        return notesDAO.getNoteImage(id);
    }
    
}

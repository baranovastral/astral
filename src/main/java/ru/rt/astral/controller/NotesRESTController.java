package ru.rt.astral.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rt.astral.model.Note;
import ru.rt.astral.service.NotesService;
import ru.rt.astral.service.UserDetailsServiceImplementation;

@RestController
@RequestMapping("/notes")
public class NotesRESTController {
    
    @Autowired
    private NotesService notesService;
    
    @Autowired
    private UserDetailsServiceImplementation userDetailsServiceImplementation;
    
    @RequestMapping(
            value = "/create",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> registerUser(@RequestBody Note note){
        note.setAuthor(userDetailsServiceImplementation.getUser());
        notesService.createNote(note);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
     @RequestMapping(
            value = "/get",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Note> getList(
            HttpServletRequest request, 
            @RequestParam(required = false, defaultValue = "") String message){
         try {
             return notesService.findAllLikeMessage(
                     userDetailsServiceImplementation.getUser(), message);
         } catch (NullPointerException e) {
             return new ArrayList<>();
         }
        
    }
    
    @RequestMapping(
            value = "/delete/{id}", 
            method = RequestMethod.DELETE)
    public void deleteDepartment(@PathVariable long id){
        System.out.println("TEST TEST TEST " + id);
        notesService.delete(id);
    }
    
    @RequestMapping(value = "img/{id}.svg", method = RequestMethod.GET)
    public ResponseEntity<byte[]> test(@PathVariable long id){
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("image", "svg+xml"));
        return new ResponseEntity<>(
                notesService.getImage(id),headers,HttpStatus.OK);
    }
    
    
}

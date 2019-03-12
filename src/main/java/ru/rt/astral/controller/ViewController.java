package ru.rt.astral.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout
        .SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.rt.astral.service.NotesService;

@Controller
public class ViewController {
    
    @Autowired
    NotesService notesService;
    
    @RequestMapping(value = "/")
    public String showNotesList(){
        return "index";
    }
    
    @RequestMapping(value = "/newNote")
    public String showCreateNoteForm(){
        return "newnote";
    }
    
    @RequestMapping(value = "/login")
    public String getLoginPage(){
        return "login";
    }
    
    @RequestMapping(value = "/register")
    public String getRegisterPage(){
        return "register";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage (
            HttpServletRequest request, 
            HttpServletResponse response) {
        Authentication auth = 
                SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
    
    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
    public String getNote(@PathVariable long id,Model model){
        model.addAttribute("note", notesService.findOne(id));
        return "note";
    }
    
}

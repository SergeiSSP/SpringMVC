package org.senkiv.webApplication.controllers;

import org.senkiv.webApplication.dao.PersonDAO;
import org.senkiv.webApplication.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class People {

    private final PersonDAO personDAO;

    @Autowired
    public People(PersonDAO personDAO){
        this.personDAO = personDAO;
    }
    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id,
                       Model model){
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person){
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,
                       Model model){
        model.addAttribute("person",personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }



}

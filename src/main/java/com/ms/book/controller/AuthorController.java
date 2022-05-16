package com.ms.book.controller;

import com.ms.book.model.AuthorModel;
import com.ms.book.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class AuthorController {

    @Autowired
    AuthorService service;

    @RequestMapping("/author")
    public String index() {
        return "author/index";
    }

    @GetMapping("/author/author-data")
    public ModelAndView formFindAuthor(@RequestParam(name = "uuid") UUID uuid) {
        AuthorModel authorModel = service.one(uuid).getContent();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("author", authorModel);
        return modelAndView;
    }

}

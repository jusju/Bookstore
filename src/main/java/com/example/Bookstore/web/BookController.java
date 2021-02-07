package com.example.Bookstore.web;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
    @Autowired
    private BookRepository repository;
    @RequestMapping(value={"/","/index"})
    public String bookList(Model model){
        model.addAttribute("books", repository.findAll());
        return "index";
    }
    @RequestMapping("/add")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        return "addbook";
    }
    @PostMapping("/save")
    public String saveBook(Book book){
        repository.save(book);
        return "redirect:index";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        repository.deleteById(bookId);
        return "redirect:../index";
    }
    @RequestMapping(value = "/edit/{id}")
    public String addStudent(@PathVariable("id") Long bookId, Model model){
        model.addAttribute("book", repository.findById(bookId));
        return "editbook";
    }
}

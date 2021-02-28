package com.example.Bookstore.web;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
    @Autowired
    private BookRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value={"/","/booklist"})
    public String bookList(Model model){
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
    @GetMapping("/books")
    public @ResponseBody List<Book> bookListRest() {
        return (List<Book>) repository.findAll();
    }
    @GetMapping("/book/{id}")
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId){
        return repository.findById(bookId);
    }
    @RequestMapping("/add")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }
    @PostMapping("/save")
    public String saveBook(Book book){
        repository.save(book);
        return "redirect:booklist";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        repository.deleteById(bookId);
        return "redirect:../booklist";
    }
    @RequestMapping(value = "/edit/{id}")
    public String addStudent(@PathVariable("id") Long bookId, Model model){
        model.addAttribute("book", repository.findById(bookId));
        model.addAttribute("categories", categoryRepository.findAll());
        return "editbook";
    }
}

package tn.esprit.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.library.entities.Book;
import tn.esprit.library.entities.Resource;
import tn.esprit.library.services.IBookService;

import java.util.List;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "http://localhost:4200") // Allow specific origin
public class BookController {
    @Autowired
    IBookService bookService;
    @PostMapping("/add")
    public Book addBook(@RequestBody Book book){return bookService.addBook(book); }

    @GetMapping("/getall")
    public List<Book> getAllBooks(){return bookService.getAllBooks(); }

    @GetMapping("/getbook/{idr}")
    public Book getBookById(@PathVariable("idr")Long id_book){return bookService.getBookById(id_book); }

    @PutMapping("/modify")
    public  Book updateBook(@RequestBody Book book){return bookService.updateBook(book); }


    @DeleteMapping("/delete/{idr}")
    public  void deleteBook(@PathVariable("idr")Long id_book){ bookService.deleteBook(id_book); }
}

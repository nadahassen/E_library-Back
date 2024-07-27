package tn.esprit.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.library.entities.Book;
import tn.esprit.library.repository.IBookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {
    @Autowired
    IBookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id_book) {
        Optional<Book> book = bookRepository.findById(id_book);
        return book.orElse(null); // or throw an exception if you prefer
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id_book) {
        bookRepository.deleteById(id_book);
    }

    @Override
    public Book updateBook(Book book) {
            return bookRepository.save(book);

    }
}

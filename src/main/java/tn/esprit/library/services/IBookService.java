package tn.esprit.library.services;

import tn.esprit.library.entities.Book;

import java.util.List;

public interface IBookService {
    public List<Book> getAllBooks();

    public Book getBookById(Long id_book);

    public Book addBook(Book book);

    public void deleteBook(Long id_book);

    public Book updateBook(Book book);

}

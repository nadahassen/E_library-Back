package tn.esprit.library.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.library.controller.BookController;
import tn.esprit.library.entities.Book;
import tn.esprit.library.entities.Reservation;
import tn.esprit.library.services.IBookService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBookService bookService;

    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new Book(1L, "Test Book", "Test Author", "Test Subject", "2023", null, true, new ArrayList<Reservation>());
    }

    @Test
    public void testAddBook() throws Exception {
        when(bookService.addBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/book/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(book.getName())));

        verify(bookService, times(1)).addBook(any(Book.class));
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(book);
        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/book/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(book.getName())));

        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    public void testGetBookById() throws Exception {
        when(bookService.getBookById(anyLong())).thenReturn(book);

        mockMvc.perform(get("/book/getbook/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(book.getName())));

        verify(bookService, times(1)).getBookById(anyLong());
    }

    @Test
    public void testUpdateBook() throws Exception {
        when(bookService.updateBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(put("/book/modify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(book.getName())));

        verify(bookService, times(1)).updateBook(any(Book.class));
    }

    @Test
    public void testDeleteBook() throws Exception {
        doNothing().when(bookService).deleteBook(anyLong());

        mockMvc.perform(delete("/book/delete/1"))
                .andExpect(status().isOk());

        verify(bookService, times(1)).deleteBook(anyLong());
    }
}

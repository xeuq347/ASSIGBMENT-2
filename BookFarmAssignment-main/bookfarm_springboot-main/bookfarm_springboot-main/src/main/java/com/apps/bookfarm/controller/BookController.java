package com.apps.bookfarm.controller;

import com.apps.bookfarm.exception.AuthorNotFoundException;
import com.apps.bookfarm.model.Author;
import com.apps.bookfarm.repository.BookRepository;
import com.apps.bookfarm.model.Book;
import com.apps.bookfarm.serviceimpl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class BookController {
    private final  BookRepository bookRepository;
    private  final BookServiceImpl bookService;

    @Autowired
    public  BookController(BookRepository bookRepository, BookServiceImpl bookService){
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }
    @GetMapping("/books")
    Iterable<Book> allBooks (){
        return bookService.getBooks();
    }
    //end::get-aggregate-root[]

    @GetMapping("/books/{id}")
    Book oneBook (@PathVariable Long id){
        Book book = bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        return bookRepository.findById(id).orElseThrow(()-> new AuthorNotFoundException(id));
    }
    @PostMapping("/books")
    public void addNewBook (@RequestBody Book newBook){
        bookService.addNewBook(newBook);
    }

    @DeleteMapping("/books/{}id")
    void  deleteBook(@PathVariable Long id){
        bookRepository.deleteById(id);
    }
    @PutMapping("book/{id}")
    Book replaceBook(@RequestBody Book newBook , @PathVariable Long id){
        return  bookRepository.findById(id).map(book -> {
            book.setAuthor(newBook.getAuthor());
            book.setPublisher(newBook.getPublisher());
            book.setIsbn(newBook.getIsbn());
            return  bookRepository.save(newBook);
        }).orElseGet(()->{newBook.getBookId(id);
        return  bookRepository.save(newBook);
        });
    }

}

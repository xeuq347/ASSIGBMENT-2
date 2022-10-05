package com.apps.bookfarm.serviceimpl;

import com.apps.bookfarm.model.Book;
import com.apps.bookfarm.repository.BookRepository;
import com.apps.bookfarm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class BookServiceImpl {

    public Iterable<Book> getBooks() {

        return null;
    }

    public void addNewBook(Book newBook) {
    }

    @Service
    public class bookServiceImpl implements BookService{
        private  final BookRepository bookRepository;
        
        @Autowired
        public bookServiceImpl(BookRepository bookRepository){
           this.bookRepository = bookRepository;
        }
        @Override
        public  Iterable<Book> getBooks(){
            return bookRepository.findAll();
        }

        @Override
        public Iterable<Book> getBooksN() {
            return null;
        }

        @Override
        public void addNewBook(Book book){
            List<Book> optionalBook = bookRepository.findAllById(book.getBookId());
            if(!optionalBook.isEmpty()){
                throw  new IllegalStateException("Book already exists");
            }else {
                bookRepository.save(book);
            }
        }
        
        @Override
        public  void  removeBook(Long id){
            
        }
ss
        @Override
        public void updateBook(Long id) {

        }

        @Override
        public void getBook(Long id) {

        }
    }}

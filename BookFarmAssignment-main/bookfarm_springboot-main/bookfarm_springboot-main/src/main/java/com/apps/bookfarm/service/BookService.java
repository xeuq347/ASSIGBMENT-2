package com.apps.bookfarm.service;

import com.apps.bookfarm.model.Book;

public interface BookService {
    Iterable<Book> getBooks();

    public Iterable<Book> getBooksN();

    //create
    public void addNewBook(Book book);

    //delete
    public void removeBook(Long id);

    //update
    public void updateBook(Long id);

    //get
    public void getBook(Long id);



}

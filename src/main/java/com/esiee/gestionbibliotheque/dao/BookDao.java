package com.esiee.gestionbibliotheque.dao;

import com.esiee.gestionbibliotheque.model.Book;

import java.util.List;

public interface BookDao {
    List<Book> searchBooks(String title, String isbn, String author, boolean available);
    void insertBook(Book book);


}

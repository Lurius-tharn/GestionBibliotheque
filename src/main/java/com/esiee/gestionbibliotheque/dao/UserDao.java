package com.esiee.gestionbibliotheque.dao;

import com.esiee.gestionbibliotheque.model.User;

import java.util.List;

public interface UserDao {
    List<User> findAllBooksByName(String nom);
    void insertBook(User user);

}

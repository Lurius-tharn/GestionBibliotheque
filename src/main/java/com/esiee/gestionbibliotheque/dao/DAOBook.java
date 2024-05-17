package com.esiee.gestionbibliotheque.dao;

import com.esiee.gestionbibliotheque.config.ConnectionDatabase;
import com.esiee.gestionbibliotheque.model.Book;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class DAOBook {

    private Book book;

    public DAOBook(final Book b) {
        this.book = b;
    }

    public DAOBook() {
    }

    public static ArrayList<Book> searchBooks(String title, String isbn, String author, boolean available){
        final ArrayList<Book> arr = new ArrayList<Book>();
        try {
            final Connection con = ConnectionDatabase.con();
            final StringBuilder str = new StringBuilder("SELECT * FROM livres WHERE 1=1");

            if (title != null && !title.isEmpty()) {
                str.append(" AND nom = ?");
            }
            if (isbn != null && !isbn.isEmpty()) {
                str.append(" AND isbn = ?");
            }
            if (author != null && !author.isEmpty()) {
                str.append(" AND auteur = ?");
            }
            str.append(" AND disponibilite = ?");

            final PreparedStatement getbooksStatemnet = con.prepareStatement(str.toString());
            int index = 1;
            if (title != null && !title.isEmpty()) {
                getbooksStatemnet.setString(index++, title);
            }
            if (isbn != null && !isbn.isEmpty()) {
                getbooksStatemnet.setString(index++, isbn);
            }
            if (author != null && !author.isEmpty()) {
                getbooksStatemnet.setString(index++, author);
            }
            getbooksStatemnet.setBoolean(index, available);


            final ResultSet listPlanning = getbooksStatemnet.executeQuery();
            while (listPlanning.next()) {
                arr.add(Book.builder()
                        .id(listPlanning.getInt(1))
                        .title(listPlanning.getString(2))
                        .author(listPlanning.getString(3))
                        .isbn(listPlanning.getString(4))
                        .available(listPlanning.getBoolean(5))
                        .build());
            }
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no charg\u00e9!");
        } catch (SQLException ex) {
        }
        return arr;
    }

    public void insert(Book book) {
        try {
            final Connection con = ConnectionDatabase.con();
            final String str = "INSERT INTO livres (nom) VALUES (?)";

            final PreparedStatement insertBookStatement = con.prepareStatement(str);
            insertBookStatement.setString(1, book.getTitle());

            insertBookStatement.executeUpdate();
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not loaded!");
        } catch (SQLException ex) {
        }
    }


    public Book recupererLivreParTitre(String title) {
        Book book = null;
        try {
            final Connection con = ConnectionDatabase.con();
            final String str = "SELECT * FROM livres WHERE nom = ?";

            final PreparedStatement getBookStatement = con.prepareStatement(str);
            getBookStatement.setString(1, title);

            final ResultSet rs = getBookStatement.executeQuery();
            if (rs.next()) {
                book = Book.builder()
                        .id(rs.getInt(1))
                        .title(rs.getString(2))
                        .author(rs.getString(3))
                        .isbn(rs.getString(4))
                        .build();
            }
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not loaded!");
        } catch (SQLException ex) {
            // Handle exception
        }
        return book;
    }

}

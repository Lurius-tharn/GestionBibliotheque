package com.esiee.gestionbibliotheque.dao;

import com.esiee.gestionbibliotheque.config.ConnectionDatabase;
import com.esiee.gestionbibliotheque.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DAOBook {

    private Book book;

    public DAOBook(final Book b) {
        this.book = b;
    }

    public DAOBook() {
    }

    public static void main(String[] args) {
        final Book b = new Book(1, "test");
        final DAOBook dao = new DAOBook(b);
        List<Book> books = DAOBook.listerParTitre("Fondation");
        System.out.println(books);
    }

    public static ArrayList<Book> listerParTitre(String titre) {
        final ArrayList<Book> arr = new ArrayList<Book>();
        try {
            final Connection con = ConnectionDatabase.con();
            final String str = "SELECT * FROM livres WHERE nom = ?";

            final PreparedStatement getbooksStatemnet = con.prepareStatement(str);
            getbooksStatemnet.setString(1, titre);

            final ResultSet listPlanning = getbooksStatemnet.executeQuery();
            while (listPlanning.next()) {
                arr.add(new Book(listPlanning.getInt(1), listPlanning.getString(2)));
            }
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no charg\u00e9!");
        } catch (SQLException ex) {
        }
        return arr;
    }

    public static ArrayList<Book> listeAll() {
        final ArrayList<Book> arr = new ArrayList<Book>();
        try {
            final Connection con = ConnectionDatabase.con();
            final String str = "SELECT * FROM livres";

            final PreparedStatement getbooksStatemnet = con.prepareStatement(str);

            final ResultSet listPlanning = getbooksStatemnet.executeQuery();
            while (listPlanning.next()) {
                arr.add(new Book(listPlanning.getInt(1), listPlanning.getString(2)));
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
                book = new Book(rs.getInt(1), rs.getString(2));
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

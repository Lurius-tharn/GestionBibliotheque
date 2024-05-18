package com.esiee.gestionbibliotheque.dao;

import com.esiee.gestionbibliotheque.config.ConnectionDatabase;
import com.esiee.gestionbibliotheque.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookDaoImpl implements BookDao{

    private Book book;
    public BookDaoImpl() {

    }

    public static void main(String[] args) {
        BookDao bookDao = new BookDaoImpl();
        List<Book> books = bookDao.searchBooks("Fondation",null,null,true);
        System.out.println(books);
    }

    /**
     * Searches for books in the database based on the provided parameters.
     * @param title Title of the book.
     * @param isbn ISBN of the book.
     * @param author Author of the book.
     * @param available Availability status of the book.
     * @return ArrayList of books that match the search criteria.
     */
    @Override
    public List<Book> searchBooks(String title, String isbn, String author, boolean available) {
        List<Book> books = new ArrayList<>();
        try {
            Connection con = ConnectionDatabase.con();
            String query = buildQuery(title, isbn, author);
            PreparedStatement getbooksStatement = con.prepareStatement(query);
            setParameters(getbooksStatement, title, isbn, author, available);
            ResultSet resultSet = executeQuery(getbooksStatement);
            books = processResults(resultSet);
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not loaded!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return books;
    }

    /**
     * Builds the SQL query string based on the provided parameters.
     * @param title Title of the book.
     * @param isbn ISBN of the book.
     * @param author Author of the book.
     * @return SQL query string.
     */
    private String buildQuery(String title, String isbn, String author) {
        StringBuilder str = new StringBuilder("SELECT * FROM livres WHERE 1=1");
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
        return str.toString();
    }

    /**
     * Sets the parameters of the prepared statement based on the provided parameters.
     * @param statement Prepared statement to set parameters for.
     * @param title Title of the book.
     * @param isbn ISBN of the book.
     * @param author Author of the book.
     * @param available Availability status of the book.
     * @throws SQLException
     */
    private void setParameters(PreparedStatement statement, String title, String isbn, String author, boolean available) throws SQLException {
        int index = 1;
        if (title != null && !title.isEmpty()) {
            statement.setString(index++, title);
        }
        if (isbn != null && !isbn.isEmpty()) {
            statement.setString(index++, isbn);
        }
        if (author != null && !author.isEmpty()) {
            statement.setString(index++, author);
        }
        statement.setBoolean(index, available);
    }

    /**
     * Executes the query and returns the ResultSet.
     * @param statement PreparedStatement object.
     * @return ResultSet object containing the results of the query execution.
     * @throws SQLException If an SQL error occurs.
     */
    private ResultSet executeQuery(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }
    /**
     * Processes the ResultSet and returns a list of books.
     * @param resultSet ResultSet object.
     * @return ArrayList of books.
     * @throws SQLException If an SQL error occurs.
     */
    private ArrayList<Book> processResults(ResultSet resultSet) throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            books.add(Book.builder()
                    .id(resultSet.getInt(1))
                    .title(resultSet.getString(2))
                    .author(resultSet.getString(3))
                    .isbn(resultSet.getString(4))
                    .available(resultSet.getBoolean(5))
                    .build());
        }
        return books;
    }
    /**
     * Inserts a new book into the database.
     * @param book Book object to be inserted.
     */
    @Override
    public void insertBook(Book book) {
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

}

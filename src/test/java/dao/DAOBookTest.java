package dao;

import com.esiee.gestionbibliotheque.dao.DAOBook;
import com.esiee.gestionbibliotheque.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class DAOBookTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    public void setup() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    public void listerParTitreReturnsEmptyListWhenNoBooksFound() throws SQLException, ClassNotFoundException {
        when(resultSet.next()).thenReturn(false);
        List<Book> books = DAOBook.searchBooks("Nonexistent",null, null, true);
        assertTrue(books.isEmpty());
    }

    @Test
    public void listerParTitreReturnsBookListWhenBooksFound() throws SQLException, ClassNotFoundException {
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getString(2)).thenReturn("Fondation");

        List<Book> books = DAOBook.searchBooks("Fondation",null, null,true);

        assertEquals(1, books.size());
        assertEquals(1, books.get(0).getId());
        assertEquals("Fondation", books.get(0).getTitle());
    }

    @Test
    public void searchByAuthorReturnsEmptyListWhenNoBooksFound() throws SQLException, ClassNotFoundException {
        when(resultSet.next()).thenReturn(false);
        List<Book> books = DAOBook.searchBooks(null, null, "Nonexistent", true);
        assertTrue(books.isEmpty());
    }

    @Test
    public void searchByAuthorReturnsBookListWhenBooksFound() throws SQLException, ClassNotFoundException {
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getString(3)).thenReturn("Isaac Asimov");

        List<Book> books = DAOBook.searchBooks(null, null, "Isaac Asimov", true);

        assertEquals(1, books.size());
        assertEquals(1, books.get(0).getId());
        assertEquals("Isaac Asimov", books.get(0).getAuthor());
    }

    @Test
    public void searchByISBNReturnsEmptyListWhenNoBooksFound() throws SQLException, ClassNotFoundException {
        when(resultSet.next()).thenReturn(false);
        List<Book> books = DAOBook.searchBooks(null, "Nonexistent", null, true);
        assertTrue(books.isEmpty());
    }

    @Test
    public void searchByISBNReturnsBookListWhenBooksFound() throws SQLException, ClassNotFoundException {
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getString(4)).thenReturn("978-2-226-10701-6");

        List<Book> books = DAOBook.searchBooks(null, "978-2-226-10701-6", null, true);

        assertEquals(1, books.size());
        assertEquals(1, books.get(0).getId());
        assertEquals("978-2-226-10701-6", books.get(0).getIsbn());
    }

    @Test
    public void searchByAvailabilityReturnsEmptyListWhenNoBooksFound() throws SQLException, ClassNotFoundException {
        when(resultSet.next()).thenReturn(false);
        List<Book> books = DAOBook.searchBooks(null, null, null, false);
        assertTrue(books.isEmpty());
    }

    @Test
    public void searchByAvailabilityReturnsBookListWhenBooksFound() throws SQLException, ClassNotFoundException {
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getBoolean(5)).thenReturn(true);

        List<Book> books = DAOBook.searchBooks(null, null, null, true);

        assertEquals(1, books.size());
        assertEquals(1, books.get(0).getId());
        assertTrue(books.get(0).isAvailable());
    }

    @Test
    public void searchByAllReturnsEmptyListWhenNoBooksFound() throws SQLException, ClassNotFoundException {
        when(resultSet.next()).thenReturn(false);
        List<Book> books = DAOBook.searchBooks("Nonexistent", "Nonexistent", "Nonexistent", false);
        assertTrue(books.isEmpty());
    }

    @Test
    public void searchByAllReturnsBookListWhenBooksFound() throws SQLException, ClassNotFoundException {
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getString(2)).thenReturn("Fondation");
        when(resultSet.getString(3)).thenReturn("Isaac Isaac Asimov");
        when(resultSet.getString(4)).thenReturn("978-2-226-10701-6");
        when(resultSet.getBoolean(5)).thenReturn(true);

        List<Book> books = DAOBook.searchBooks("Fondation", "978-2-226-10701-6", "Isaac Asimov", true);

        assertEquals(1, books.size());
        assertEquals(1, books.get(0).getId());
        assertEquals("Fondation", books.get(0).getTitle());
        assertEquals("Isaac Asimov", books.get(0).getAuthor());
        assertEquals("978-2-226-10701-6", books.get(0).getIsbn());
        assertTrue(books.get(0).isAvailable());
    }
}

package dao;

import com.esiee.gestionbibliotheque.dao.BookDao;
import com.esiee.gestionbibliotheque.dao.BookDaoImpl;
import com.esiee.gestionbibliotheque.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookDaoTest {

    private BookDao bookDao;
    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException {
        bookDao = new BookDaoImpl();
    }
    @Test
    @DisplayName("Search Books by Title with No Books Found")
    public void searchBooks_ByTitleNoBooksFound_ReturnsEmptyList() throws SQLException, ClassNotFoundException {
        List<Book> books = bookDao.searchBooks("Nonexistent",null, null, true);
        assertTrue(books.isEmpty());
    }

    @Test
    @DisplayName("Search Books by Title with Books Found")
    public void searchBooks_ByTitleBooksFound_ReturnsBookList() throws SQLException, ClassNotFoundException {
        List<Book> books = bookDao.searchBooks("Fondation",null, null,true);

        assertEquals(1, books.size());
        assertEquals(1, books.get(0).getId());
        assertEquals("Fondation", books.get(0).getTitle());
    }

    @Test
    @DisplayName("Search Books by Author with No Books Found")
    public void searchBooks_ByAuthorNoBooksFound_ReturnsEmptyList() throws SQLException, ClassNotFoundException {
        List<Book> books = bookDao.searchBooks(null, null, "Nonexistent", true);
        assertTrue(books.isEmpty());
    }

    @Test
    @DisplayName("Search Books by Author with Books Found")
    public void searchBooks_ByAuthorBooksFound_ReturnsBookList() throws SQLException, ClassNotFoundException {
        List<Book> books = bookDao.searchBooks(null, null, "Isaac Asimov", true);

        assertEquals(1, books.size());
        assertEquals(1, books.get(0).getId());
        assertEquals("Isaac Asimov", books.get(0).getAuthor());
    }

    @Test
    @DisplayName("Search Books by ISBN with No Books Found")
    public void searchBooks_ByISBNNoBooksFound_ReturnsEmptyList() throws SQLException, ClassNotFoundException {
        List<Book> books = bookDao.searchBooks(null, "Nonexistent", null, true);
        assertTrue(books.isEmpty());
    }

    @Test
    @DisplayName("Search Books by ISBN with Books Found")
    public void searchBooks_ByISBNBooksFound_ReturnsBookList() throws SQLException, ClassNotFoundException {
        List<Book> books = bookDao.searchBooks(null, "978-2-226-10701-6", null, true);

        assertEquals(1, books.size());
        assertEquals(1, books.get(0).getId());
        assertEquals("978-2-226-10701-6", books.get(0).getIsbn());
    }

    @Test
    @DisplayName("Search Books by Availability with No Books Found")
    public void searchBooks_ByAvailabilityNoBooksFound_ReturnsEmptyList() throws SQLException, ClassNotFoundException {
        List<Book> books = bookDao.searchBooks(null, null, null, false);
        assertTrue(books.isEmpty());
    }

    @Test
    @DisplayName("Search Books by Availability with Books Found")
    public void searchBooks_ByAvailabilityBooksFound_ReturnsBookList() throws SQLException, ClassNotFoundException {
        List<Book> books = bookDao.searchBooks(null, null, null, true);

        assertEquals(1, books.size());
        assertEquals(1, books.get(0).getId());
        assertTrue(books.get(0).isAvailable());
    }

    @Test
    @DisplayName("Search Books by All Parameters with No Books Found")
    public void searchBooks_ByAllParametersNoBooksFound_ReturnsEmptyList() throws SQLException, ClassNotFoundException {
        List<Book> books = bookDao.searchBooks("Nonexistent", "Nonexistent", "Nonexistent", false);
        assertTrue(books.isEmpty());
    }

    @Test
    @DisplayName("Search Books by All Parameters with Books Found")
    public void searchBooks_ByAllParametersBooksFound_ReturnsBookList() throws SQLException, ClassNotFoundException {
        List<Book> books = bookDao.searchBooks("Fondation", "978-2-226-10701-6", "Isaac Asimov", true);

        assertEquals(1, books.size());
        assertEquals(1, books.get(0).getId());
        assertEquals("Fondation", books.get(0).getTitle());
        assertEquals("Isaac Asimov", books.get(0).getAuthor());
        assertEquals("978-2-226-10701-6", books.get(0).getIsbn());
        assertTrue(books.get(0).isAvailable());
    }
}

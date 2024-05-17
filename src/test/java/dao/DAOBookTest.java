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
        List<Book> books = DAOBook.listerParTitre("Nonexistent");
        assertTrue(books.isEmpty());
    }

    @Test
    public void listerParTitreReturnsBookListWhenBooksFound() throws SQLException, ClassNotFoundException {
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getString(2)).thenReturn("Fondation");

        List<Book> books = DAOBook.listerParTitre("Fondation");

        assertEquals(1, books.size());
        assertEquals(1, books.get(0).getId());
        assertEquals("Fondation", books.get(0).getTitle());
    }
}

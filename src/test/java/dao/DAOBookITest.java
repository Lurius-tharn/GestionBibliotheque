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
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

public class DAOBookITest {

    @Test
    public void testRealDatabaseWithBookFoundation() throws SQLException {

        DAOBook daoBook = new DAOBook();

        Book book = new Book().toBuilder()
                .id(1)
                .title("Fondation")
                .build();

        daoBook.insert(book);

        Book retrievedBook = daoBook.recupererLivreParTitre("Fondation");

        assertEquals(book.getTitle(), retrievedBook.getTitle());
    }

}

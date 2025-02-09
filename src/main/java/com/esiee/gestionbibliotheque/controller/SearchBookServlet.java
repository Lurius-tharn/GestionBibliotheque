package com.esiee.gestionbibliotheque.controller;

import com.esiee.gestionbibliotheque.dao.BookDao;
import com.esiee.gestionbibliotheque.dao.BookDaoImpl;
import com.esiee.gestionbibliotheque.model.Book;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchBookServlet")
public class SearchBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDao bookDao = new BookDaoImpl();
        String title = request.getParameter("title");
        String isbn = request.getParameter("isbn");
        String author = request.getParameter("author");
        String dispo = request.getParameter("dispo");

        List<Book> books = bookDao.searchBooks(title, isbn, author, dispo != null && dispo.equals("1"));
        request.setAttribute("books", books);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}

package com.esiee.gestionbibliotheque.controller;

import com.esiee.gestionbibliotheque.dao.DAOBook;
import com.esiee.gestionbibliotheque.model.Book;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchBookServlet")
public class SearchBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");

        List<Book> books = DAOBook.listerParTitre(title);

        req.setAttribute("books", books);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}

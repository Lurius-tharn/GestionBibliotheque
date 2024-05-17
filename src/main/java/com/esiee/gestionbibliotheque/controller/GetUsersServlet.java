package com.esiee.gestionbibliotheque.controller;

import com.esiee.gestionbibliotheque.dao.DAOUser;
import com.esiee.gestionbibliotheque.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/GetUsersServlet")
public class GetUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");

        List<User> users = DAOUser.listerParNom(nom);

        req.setAttribute("users", users);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}

package com.esiee.gestionbibliotheque.dao;

import com.esiee.gestionbibliotheque.config.ConnectionDatabase;
import com.esiee.gestionbibliotheque.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    /**
     * This method is used to find all users by their name.
     * @param nom This is the name of the user.
     * @return List<User> This returns a list of users with the given name.
     */
    public List<User> findAllBooksByName(String nom) {
        final ArrayList<User> arr = new ArrayList<User>();
        try {
            final Connection con = ConnectionDatabase.con();
            final String str = "SELECT * FROM users WHERE nom = ?";

            final PreparedStatement getUsersStatement = con.prepareStatement(str);
            getUsersStatement.setString(1, nom);

            final ResultSet listeUsers = getUsersStatement.executeQuery();
            while (listeUsers.next()) {
                arr.add(new User(listeUsers.getInt(1), listeUsers.getString(2)));
            }
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no chargé!");
        } catch (SQLException ex) {
        }
        return arr;
    }
    /**
     * This method is used to insert a new user into the database.
     * @param book This is the user to be inserted.
     */
    public void insertBook(User book) {
        try {
            final Connection con = ConnectionDatabase.con();
            final String str = "INSERT INTO users (nom) VALUES (?)";

            final PreparedStatement insertUserStatement = con.prepareStatement(str);
            insertUserStatement.setString(1, book.getName());

            insertUserStatement.executeUpdate();
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not loaded!");
        } catch (SQLException ex) {
        }
    }

    public User recupererUserParNom(String nom) {
        User user = null;
        try {
            final Connection con = ConnectionDatabase.con();
            final String str = "SELECT * FROM users WHERE nom = ?";

            final PreparedStatement getUserStatement = con.prepareStatement(str);
            getUserStatement.setString(1, nom);

            final ResultSet rs = getUserStatement.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2));
            }
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not loaded!");
        } catch (SQLException ex) {
            // Handle exception
        }
        return user;
    }

}

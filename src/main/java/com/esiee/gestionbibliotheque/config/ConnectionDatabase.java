package com.esiee.gestionbibliotheque.config;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {
    public static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    public static Connection con() throws ClassNotFoundException, SQLException {
        Dotenv dotenv = Dotenv.load();
        Class.forName(CLASS_NAME);
        final Connection con = DriverManager.getConnection(dotenv.get("DB_URL")
                , dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"));
        return con;
    }
}

<%@ page import="com.esiee.gestionbibliotheque.dao.UserDaoImpl" %>
<%@ page import="com.esiee.gestionbibliotheque.model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.esiee.gestionbibliotheque.dao.UserDao" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: theo
  Date: 17/05/2024
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    UserDao userDao = new UserDaoImpl();
    List<User> listeUsers = userDao.findAllBooksByName("prenom");
%>

<html>
<head>
    <link rel="stylesheet" href="style.css"/>
    <title>Gestion biblio</title>
</head>
<body>

<%@ include file="layout.jspf" %>

<div class="userdiv">

        <% for (User u : listeUsers) { %>

    <ul>
        <li><%= u.getId()%> / <%= u.getName() %>
        </li>
    </ul>

        <% } %>

</body>

<div/>

</html>

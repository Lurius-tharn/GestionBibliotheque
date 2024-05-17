<%@ page import="com.esiee.gestionbibliotheque.dao.DAOUser" %>
<%@ page import="com.esiee.gestionbibliotheque.model.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: theo
  Date: 17/05/2024
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<User> listeUsers = DAOUser.listerParNom("prenom");
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
        <li><%= u.getId()%> / <%= u.getNom() %>
        </li>
    </ul>

        <% } %>

</body>

<div/>

</html>

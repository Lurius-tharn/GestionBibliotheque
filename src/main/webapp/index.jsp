<%@ page import="com.esiee.gestionbibliotheque.model.Book" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Gestion Biblio</title>
</head>

<body>

<%@ include file="layout.jspf" %>

<div class="maindiv">

    <br/>

    <form action="SearchBookServlet" method="get">
        <label for="title">Book Title:</label><br>
        <input type="text" id="title" name="title"><br>
        <label for="isbn">ISBN:</label><br>
        <input type="text" id="isbn" name="isbn"><br>
        <label for="author">Author:</label><br>
        <input type="text" id="author" name="author"><br>
        <label for="author">Disponibilité</label><br>
        <input type="checkbox" id="dispo" name="dispo" checked value="1">

        <input type="submit" value="Search">

    </form>

    <%
        List<Book> books = (List<Book>) request.getAttribute("books");
        if (books != null && !books.isEmpty()) {
    %>
    <table>
        <tr>
            <th>ID</th>
            <th>titre</th>
            <th>Auteur</th>
            <th>ISBN</th>
            <th>Disponibilité</th>

        </tr>
        <% for (Book book : books) { %>
        <tr>
            <td><%= book.getId() %></td>
            <td><%= book.getTitle() %></td>
            <td><%= book.getAuthor() %></td>
            <td><%= book.getIsbn() %></td>
            <td><%= book.isAvailable() ? "Disponible" : "Emprunté" %></td>

        </tr>
        <% } %>
    </table>
    <% } %>

</div>
</body>
</html>

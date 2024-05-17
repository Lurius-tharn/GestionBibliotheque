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
        <label for="title">Titre du livre:</label><br/>
        <input type="text" id="title" name="title" required/><br/>
        <br/>
        <input type="submit" value="Chercher">
    </form>

    <%
        List<Book> books = (List<Book>) request.getAttribute("books");
        if (books != null && !books.isEmpty()) {
    %>
    <table>
        <tr>
            <th>ID</th>
            <th>Nom</th>
        </tr>
        <br/>
        <% for (Book book : books) { %>
        <tr>
            <td><%= book.getId() %>
            </td>
            <td><%= book.getTitle() %>
            </td>
        </tr>
        <% } %>
    </table>
    <% } %>

</div>
</body>
</html>

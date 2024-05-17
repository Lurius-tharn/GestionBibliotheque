<%@ page import="com.esiee.gestionbibliotheque.model.Book" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Books</title>
</head>
<body>
    <form action="SearchBookServlet" method="get">
        <label for="title">Book Title:</label><br>
        <input type="text" id="title" name="title"><br>
        <input type="submit" value="Search">
    </form>

    <%
        List<Book> books = (List<Book>) request.getAttribute("books");
        if (books != null && !books.isEmpty()) {
    %>
    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
        </tr>
        <% for (Book book : books) { %>
        <tr>
            <td><%= book.getId() %></td>
            <td><%= book.getTitle() %></td>
        </tr>
        <% } %>
    </table>
    <% } %>
</body>
</html>

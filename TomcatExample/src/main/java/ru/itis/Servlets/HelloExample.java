package ru.itis.Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloExample extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        PrintWriter writer = response.getWriter();

        writer.write("<h1> Welcome, Welcome </h1>");
        writer.write("<h2>Hello, " + name + "</h2>");
    }
}

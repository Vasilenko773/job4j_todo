package ru.job4j.servlet;

import ru.job4j.model.User;
import ru.job4j.store.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if (HibernateUtil.instOf().findByNameUser(name) == null) {
            HttpSession sc = req.getSession();
            User user = new User(name, password);
            HibernateUtil.instOf().saveUser(user);
            sc.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");

        } else {
            req.setAttribute("error", "пользователь с таким именем уже существует. Попробуйде снова");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        }
    }
}



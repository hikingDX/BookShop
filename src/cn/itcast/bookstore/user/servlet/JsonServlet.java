package cn.itcast.bookstore.user.servlet;

import cn.itcast.bookstore.user.domain.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/1/3.
 */
@WebServlet(name = "JsonServlet",urlPatterns = {"/sayHello"})
public class JsonServlet extends JsonBaseServlet {

    public void first(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        User p = new User();
        p.setEmail("bob@hotmail.com");
        p.setUsername("Bob Qin");
        p.setPassword("123");

        PrintWriter writer = response.getWriter();
        writer.write(new Gson().toJson(p, User.class));
        writer.flush();
    }
}

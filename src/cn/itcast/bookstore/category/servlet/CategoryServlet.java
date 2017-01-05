package cn.itcast.bookstore.category.servlet;

import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.bookstore.category.service.CategoryService;
import cn.itcast.bookstore.user.domain.User;
import cn.itcast.bookstore.user.service.UserService;
import cn.itcast.bookstore.user.servlet.JsonBaseServlet;
import cn.itcast.bookstore.utils.ResultBean;
import cn.itcast.servlet.BaseServlet;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/4.
 */
@WebServlet(name = "CategoryServlet",urlPatterns = "/Category")
public class CategoryServlet extends JsonBaseServlet{
    private CategoryService categoryService = new CategoryService();
    private UserService userService = new UserService();
    public String findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        User user = userService.findByToken(token);
        if (user==null){
            return "{\"error\":\"没有登陆\"}";
        }
        List list = new ArrayList();
        List<Category> all = categoryService.findAll();
        list.add(all);
        list.add(user);
        ResultBean resultBean = new ResultBean("success","10001",list);
        Gson gson = new Gson();
        String ok = gson.toJson(resultBean);
        return ok;
    }
}

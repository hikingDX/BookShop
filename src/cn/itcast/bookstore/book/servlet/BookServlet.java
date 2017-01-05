package cn.itcast.bookstore.book.servlet;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.bookstore.user.domain.User;
import cn.itcast.bookstore.user.service.UserService;
import cn.itcast.bookstore.user.servlet.JsonBaseServlet;
import cn.itcast.bookstore.utils.ResultBean;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/1/5.
 */
@WebServlet(name = "BookServlet",urlPatterns = "/Book")
public class BookServlet extends JsonBaseServlet{
    private UserService userService = new UserService();
    private BookService bookService = new BookService();
    //Book?method=load&token=*****&bid=***
    public String load(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.验证token
        String token = req.getParameter("token");
        Gson gson = new Gson();
        User user = userService.findByToken(token);
        if (user==null){
            return "{\"error\":\"没有登陆\"}";
        }
        //2.返回数据
        Book book = bookService.load(req.getParameter("bid"));
        ResultBean<Book> resultBean = new ResultBean<>("success","10001",book);
        resultBean.setResult(book);
        return gson.toJson(resultBean);
    }
}

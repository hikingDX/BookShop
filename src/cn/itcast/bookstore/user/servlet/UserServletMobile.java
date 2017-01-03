package cn.itcast.bookstore.user.servlet;

import cn.itcast.bookstore.user.domain.User;
import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.bookstore.user.service.UserService;
import cn.itcast.commons.CommonUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/3.
 */
@WebServlet(name = "UserServlet",urlPatterns = {"/UserServlet"})
public class UserServletMobile extends JsonBaseServlet {
    private UserService userService = new UserService();

    public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.封装表单数据到form对象中
        //2.不全uid.code
        //3.输入校验
        //>保存错误转发到regist.jsp
        //4.调用service方法完成注册
        //>保存错误转发到regist.jsp
        //5.发邮件
        //6.保存成功信息转发到msg.jsp

        //1.
        String n = request.getParameter("n");
        String p = request.getParameter("p");
        String em = request.getParameter("email");
//        User form = CommonUtils.toBean(request.getParameterMap(), User.class);
        User form = new User(n,p,em);

        form.setUid(CommonUtils.uuid());
        form.setCode(CommonUtils.uuid() + CommonUtils.uuid());
        //2.
        /**
         * 1.创建map，封装错误信息,其中key为表单字段名称,值为错误信息
         */
        Map<String, String> errors = new HashMap<>();
        /**
         * 2.获取form中的username,password,email进行校验
         */
        String username = form.getUsername();
        if (username == null || username.trim().isEmpty()) {
            errors.put("username", "用户名不能为空!");
        } else if (username.length() < 3 || username.length() > 10) {
            errors.put("username", "用户名长度必须在3-10之间!");
        }
        String password = form.getPassword();
        if (password == null || password.trim().isEmpty()) {
            errors.put("password", "密码不能为空!");
        } else if (password.length() < 3 || password.length() > 10) {
            errors.put("password", "密码长度必须在3-10之间!");
        }
        String email = form.getEmail();
        if (email == null || email.trim().isEmpty()) {
            errors.put("email", "Email不能为空!");
        } else if (email.matches("\\w+@\\w.\\w")) {
            errors.put("email", "Email格式错误");
        }
        /**
         * 3.判断是否存在错误信息
         */
        if (errors.size() > 0) {
            Gson gson = new Gson();
            return gson.toJson(errors);
        }
        /**
         * 调用service的regist()方法
         */
        try {
            userService.regist(form);

        } catch (UserException e) {
            e.printStackTrace();
//            request.setAttribute("msg", e.getMessage());
//            request.setAttribute("form", form);
            Gson gson = new Gson();
            return gson.toJson("{\"error\":\'fail\'}");
        }
        /**
         * 发邮件
         * 准备配置文件
         */
//        Properties properties = new Properties();
//        properties.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
//        String host =
        /**
         * 执行到这里说明userService执行成功
         * 1.保存成功信息
         * 2.转发到msg.jsp
         */
        Gson gson = new Gson();
        return gson.toJson("{\'msg\':\'resgist success!\'}");
    }
}

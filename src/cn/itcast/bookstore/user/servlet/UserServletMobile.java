package cn.itcast.bookstore.user.servlet;

import cn.itcast.bookstore.user.domain.User;
import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.bookstore.user.service.UserService;
import cn.itcast.bookstore.utils.ErrorBean;
import cn.itcast.bookstore.utils.ResultBean;
import cn.itcast.commons.CommonUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 12222
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
         * 2.获取form中的username,password,email进行校验
         */
        String username = form.getUsername();
        ErrorBean errorBean = new ErrorBean();
        Gson gson = new Gson();
        if (username == null || username.trim().isEmpty()) {
            errorBean.setError("用户名不能为空");
        } else if (username.length() < 3 || username.length() > 10) {
            errorBean.setError("用户名长度必须在3-10之间!");
        }
        String password = form.getPassword();
        if (password == null || password.trim().isEmpty()) {
            errorBean.setError("密码不能为空!");
        } else if (password.length() < 3 || password.length() > 10) {
            errorBean.setError("密码长度必须在3-10之间!");
        }
        String email = form.getEmail();
        if (email == null || email.trim().isEmpty()) {
            errorBean.setError("Email不能为空!");
        } else if (email.matches("\\w+@\\w.\\w")) {
            errorBean.setError("Email格式错误!");
        }
        /**
         * 3.判断是否存在错误信息
         */
        if (errorBean.getError()!=null) {
            return gson.toJson(errorBean);
        }
        /**
         * 调用service的regist()方法
         */
        try {
            userService.regist(form);

        } catch (UserException e) {
            e.printStackTrace();
            errorBean.setError(e.getMessage());

            return gson.toJson(errorBean);
        }
        /**
         * 发邮件
         * 准备配置文件
         */

        /**
         * 执行到这里说明userService执行成功
         * 1.保存成功信息
         * 2.转发到msg.jsp
         */
        ResultBean resultBean = new ResultBean();
        resultBean.setMsg("resgist success!");
        return gson.toJson(resultBean);
    }

    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1.封装表单数据到form中
         * 2.输入校验
         * 3.调用service完成激活
         * 4.保存用户信息到session中,然后重定向到index.jsp4.保存用户信息到session中,然后重定向到index.jsp4.保存用户信息到session中,然后重定向到index.jsp4.保存用户信息到session中,然后重定向到index.jsp
         */
        User form = CommonUtils.toBean(request.getParameterMap(),User.class);
        Gson gson = new Gson();
        ResultBean resultBean = new ResultBean();
        ErrorBean errorBean = new ErrorBean();
        try {
            User user = userService.login(form);
            if(user == null){
                errorBean.setError("login fail");
                return gson.toJson(errorBean);
            }
            String token = user.getCode();

            resultBean.setMsg("login success");
            resultBean.setResult("{'token':'"+token+"'}");
//            return
            return gson.toJson(resultBean);
        } catch (UserException e) {
            e.printStackTrace();
        }
        return null;
    }
}

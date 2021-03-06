package cn.itcast.bookstore.user.servlet;

import cn.itcast.bookstore.user.domain.User;
import com.google.gson.Gson;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/3.
 */
public class JsonBaseServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json;charset=UTF-8");//处理响应编码
        request.setCharacterEncoding("UTF-8");

        /**
         * 1. 获取method参数，它是用户想调用的方法 2. 把方法名称变成Method类的实例对象 3. 通过invoke()来调用这个方法
         */
        String methodName = request.getParameter("method");
        Method method = null;
        /**
         * 2. 通过方法名称获取Method对象
         */
        try {
            method = this.getClass().getMethod(methodName,
                    HttpServletRequest.class, HttpServletResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("您要调用的方法：" + methodName + "它不存在！", e);
        }
        /**
         * 3. 通过method对象来调用它
         */
        try {
            String result = (String) method.invoke(this, request, response);
            if (result != null && !result.trim().isEmpty()) {//如果请求处理方法返回不为空
                PrintWriter writer = response.getWriter();
                writer.write(result);
                writer.flush();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void doAuth(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("session_user");
        if (user == null) {
            //未登录
            render("10001", "请登录!", null);
        }
        //已经登录
    }

    public void render(String code, String message, ArrayList<Map<String, String>> result) {
        for (Map<String,String> map:result) {

        }
    }
}

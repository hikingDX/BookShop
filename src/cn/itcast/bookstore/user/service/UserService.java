package cn.itcast.bookstore.user.service;

import java.sql.SQLException;

import cn.itcast.bookstore.user.dao.UserDao;
import cn.itcast.bookstore.user.domain.User;

/**
 * User业务层
 */
public class UserService {
    private UserDao userDao = new UserDao();

    public void regist(User form) throws UserException {
        User user = userDao.findByUsername(form.getUsername());
        //校验用户名
        if (user != null) throw new UserException("用户名已经注册!");
        user = userDao.findByEmail(form.getEmail());
        if (user!=null)throw new UserException("email已经注册!");
        //插入用户
        userDao.add(form);
    }

}

package cn.itcast.bookstore.user.service;
import cn.itcast.bookstore.user.dao.UserDao;
import cn.itcast.bookstore.user.domain.User;
/**
 * User业务层
 */
public class UserService {
    private UserDao userDao = new UserDao();

    public User findByToken(String token){
        return userDao.findByToken(token);
    }

    public void regist(User form) throws UserException {
        User user = userDao.findByUsername(form.getUsername());
        //校验用户名
        if (user != null) throw new UserException("用户名已经注册!");
        user = userDao.findByEmail(form.getEmail());
        if (user!=null)throw new UserException("email已经注册!");
        //插入用户
        userDao.add(form);
    }

    /**
     * 登录功能
     * @param form
     * @return
     */
    public User login(User form) throws UserException {
        /*
        1.使用usename查询,得到User
        2.如果user为null,抛出异常 用户名不存在
        3.比较form和user的密码,若不同，抛出异常 密码错误
        4.查看用户状态，若为false，抛出异常 尚未激活
        5.返回User
         */
        User user = userDao.findByUsername(form.getUsername());
        if (user == null)throw new UserException("用户名不存在!");
        if (!user.getPassword().equals(form.getPassword())){
            throw new UserException("密码错误!");
        }
//        if (!user.isState()){
//            throw new UserException("尚未激活!");
//        }
        return user;
    }

}

package cn.itcast.bookstore.user.dao;

import java.sql.SQLException;
import java.util.Objects;

import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.bookstore.user.domain.User;

/**
 * User持久层
 */
public class UserDao {
    private QueryRunner qr = new TxQueryRunner();

    /**
     * 插入User
     *
     * @param user
     * @return
     */
    public void add(User user) {
        try {
            String sql = "insert into tb_user values(?,?,?,?,?,?)";
            Object[] params = {user.getUid(), user.getUsername(), user.getPassword(),
                    user.getEmail(), user.getCode(), user.isState()};
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 按email查询
     *
     * @param email
     * @return
     */
    public User findByEmail(String email) {
        try {
            String sql = "select * from tb_user where email=?";
            return qr.query(sql, new BeanHandler<User>(User.class), email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 按用户名查询
     *
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        try {
            String sql = "select * from tb_user where username=?";
            return qr.query(sql, new BeanHandler<User>(User.class), username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

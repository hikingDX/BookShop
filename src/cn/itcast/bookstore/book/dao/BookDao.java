package cn.itcast.bookstore.book.dao;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/1/5.
 */
public class BookDao {
    private QueryRunner qr = new TxQueryRunner();

    /**
     * 查询所有图书
     * @return
     */
    public List<Book> findAll(){
        try {
            String sql = "select * from book";
            return qr.query(sql,new BeanListHandler<Book>(Book.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 按分类查询
     * @param cid
     * @return
     */
    public List<Book> findByCategory(String cid){
        try {
            String sql = "select * from book where cid=?";
            return qr.query(sql,new BeanListHandler<Book>(Book.class),cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过id查询
     * @param bid
     * @return
     */
    public Book findByid(String bid){
        try {
            String sql = "select * from book where bid=?";
            return qr.query(sql,new BeanHandler<Book>(Book.class),bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

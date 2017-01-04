package cn.itcast.bookstore.category.dao;

import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */
public class CategoryDao {
    private QueryRunner qr = new TxQueryRunner();

    /**
     * 查询所有分类
     * @return
     */
    public List<Category> findAll(){
        try {
            String sql = "select * from category";
            return qr.query(sql,new BeanListHandler<Category>(Category.class) );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

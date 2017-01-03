package cn.itcast.bookstore.user.service;

/**
 * Created by Administrator on 2017/1/3.
 */
public class UserException extends Exception{
    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }
}

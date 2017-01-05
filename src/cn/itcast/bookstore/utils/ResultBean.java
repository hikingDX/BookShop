package cn.itcast.bookstore.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/5.
 */
public class ResultBean <T>{
    private String msg;
    private String code;
    private String time;
    private T result;

    public ResultBean() {
    }

    public ResultBean(String msg, String code, T result) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        this.time = df.format(new Date());
        this.msg = msg;
        this.code = code;
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

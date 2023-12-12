package top.jocularchao.entity;

import lombok.Data;

/**
 * @author jocularchao
 * @date 2023-12-12 17:30
 */
/*系统返回的信息类*/
//具体类型不知道所以要定义泛型类
@Data
public class RestBean<T> {
    //三个关键数据：

    //当前的状态
    private int status;
    //是否成功
    private boolean success;
    //返回的数据
    private T message;

    //定义私有的全参构造方法
    private RestBean( int status, boolean success,T message) {
        this.status = status;
        this.success = success;
        this.message = message;
    }

    //定义几个工具方法
    //登录成功
    public static <T> RestBean<T> success(){
        return new RestBean<>(200,true,null);
    }
    public static <T> RestBean<T> success(T data){
        return new RestBean<>(200,true,data);
    }

    //登录失败
    public static <T> RestBean<T> failure(int status){
        return new RestBean<>(status,false,null);
    }
    public static <T> RestBean<T> failure(int status,T data){
        return new RestBean<>(status,false,data);
    }
}

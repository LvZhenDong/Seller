package food.xinyuan.seller.app.utils;

import java.util.List;

/**
 * Created by f-x on 2017/12/12  14:02
 * Description
 */

public class MyThrowable extends Throwable {

    public String message;
    public int errorCode;
    public List<String> exceptions;

    public MyThrowable(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }
}

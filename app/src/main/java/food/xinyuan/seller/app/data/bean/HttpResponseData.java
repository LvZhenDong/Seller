package food.xinyuan.seller.app.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by f-x on 2017/12/11  19:39
 * Description
 */

public class HttpResponseData <T> implements Serializable {

    private static final long serialVersionUID = 5213230387175987834L;
    public String message;
    public int errorCode;
    public boolean status;
    public List<String> exceptions;


    public T data;

    public T getData() {
        return data;
    }

    public void setData(T datas) {
        this.data = datas;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getExceptions() {
        return exceptions;
    }

    @Override
    public String toString() {
        return "HttpResponseData{" +
                "message='" + message + '\'' +
                ", errorCode=" + errorCode +
                ", status=" + status +
                ", exceptions=" + exceptions +
                ", data=" + data +
                '}';
    }
}

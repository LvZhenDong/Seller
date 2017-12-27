package food.xinyuan.seller.app.data.bean;

import java.io.Serializable;

/**
 * Created by f-x on 2017/12/816:19
 */

public class StepBean implements Serializable {

    private String time;
    private String content;

    public StepBean(String time, String content) {
        this.time = time;
        this.content = content;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

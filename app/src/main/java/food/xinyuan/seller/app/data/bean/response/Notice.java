package food.xinyuan.seller.app.data.bean.response;

import food.xinyuan.seller.app.utils.XDateUtils;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/4
 */
public class Notice {

    /**
     * noticeId : 20171
     * title : 新订单
     * content : 你有新的共享点餐订单，请及时处理！
     * createTime : 1515053635000
     * contentType : ORDER_CREATE
     * read : false
     */

    private int noticeId;
    private String title;
    private String content;
    private long createTime;
    private String contentType;
    private boolean read;

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public String getCreateTimeStr(){
        return XDateUtils.millis2String(createTime,"MM-dd HH:mm");
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}

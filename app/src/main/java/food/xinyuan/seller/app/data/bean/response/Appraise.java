package food.xinyuan.seller.app.data.bean.response;

import java.util.List;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/12
 */
public class Appraise {

    /**
     * shopAppraiseId : 1570
     * orderId : 4989
     * orderName : 哈喽果汁(大杯) 等2件商品
     * shopId : 61
     * shopName : 鑫圆共享测试店铺(不对外点餐)
     * userId : 4422
     * shopAppraise : 5
     * tagShopAppraise :
     * contentShopAppraise : 非常好
     * appraiseTime : 1515138568000
     * appraisePageView : 0
     * appraisePraiseCount : 0
     * praise : false
     * userName : 哈哈
     * avatorUrl : /avator/4422/20171220104846387.png
     * commentList : [{"replyId":128,"shopAppraiseId":1570,"commentContent":"啊","commentTime":1515667662000}]
     * goodsAppraiseList : [{"goodsAppraiseId":2167,"appraiseLevel":5,"appraiseContent":"非常好","appraiseTime":1515138568000,"goodsId":9087,"goodsName":"哈喽果汁(大杯)","shopId":61,"userId":4422,"orderId":4989,"tagGoodsAppraise":""},{"goodsAppraiseId":2168,"appraiseLevel":5,"appraiseContent":"非常好","appraiseTime":1515138568000,"goodsId":9094,"goodsName":"商品名称(小规格)","shopId":61,"userId":4422,"orderId":4989,"tagGoodsAppraise":""}]
     * deliveryAppraise : {"deliveryAppraiseId":399,"appraiseLevel":5,"appraiseContent":"非常好","appraiseTime":1515138568000,"shopId":61,"userId":4422,"orderId":4989,"tagDeliveryAppraise":""}
     */

    private int shopAppraiseId;
    private int orderId;
    private String orderName;
    private int shopId;
    private String shopName;
    private int userId;
    private int shopAppraise;
    private String tagShopAppraise;
    private String contentShopAppraise;
    private long appraiseTime;
    private int appraisePageView;
    private int appraisePraiseCount;
    private boolean praise;
    private String userName;
    private String avatorUrl;
    private DeliveryAppraiseBean deliveryAppraise;
    private List<CommentListBean> commentList;
    private List<GoodsAppraiseListBean> goodsAppraiseList;

    public int getShopAppraiseId() {
        return shopAppraiseId;
    }

    public void setShopAppraiseId(int shopAppraiseId) {
        this.shopAppraiseId = shopAppraiseId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShopAppraise() {
        return shopAppraise;
    }

    public void setShopAppraise(int shopAppraise) {
        this.shopAppraise = shopAppraise;
    }

    public String getTagShopAppraise() {
        return tagShopAppraise;
    }

    public void setTagShopAppraise(String tagShopAppraise) {
        this.tagShopAppraise = tagShopAppraise;
    }

    public String getContentShopAppraise() {
        return contentShopAppraise;
    }

    public void setContentShopAppraise(String contentShopAppraise) {
        this.contentShopAppraise = contentShopAppraise;
    }

    public long getAppraiseTime() {
        return appraiseTime;
    }

    public void setAppraiseTime(long appraiseTime) {
        this.appraiseTime = appraiseTime;
    }

    public int getAppraisePageView() {
        return appraisePageView;
    }

    public void setAppraisePageView(int appraisePageView) {
        this.appraisePageView = appraisePageView;
    }

    public int getAppraisePraiseCount() {
        return appraisePraiseCount;
    }

    public void setAppraisePraiseCount(int appraisePraiseCount) {
        this.appraisePraiseCount = appraisePraiseCount;
    }

    public boolean isPraise() {
        return praise;
    }

    public void setPraise(boolean praise) {
        this.praise = praise;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatorUrl() {
        return avatorUrl;
    }

    public void setAvatorUrl(String avatorUrl) {
        this.avatorUrl = avatorUrl;
    }

    public DeliveryAppraiseBean getDeliveryAppraise() {
        return deliveryAppraise;
    }

    public void setDeliveryAppraise(DeliveryAppraiseBean deliveryAppraise) {
        this.deliveryAppraise = deliveryAppraise;
    }

    public List<CommentListBean> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentListBean> commentList) {
        this.commentList = commentList;
    }

    public List<GoodsAppraiseListBean> getGoodsAppraiseList() {
        return goodsAppraiseList;
    }

    public void setGoodsAppraiseList(List<GoodsAppraiseListBean> goodsAppraiseList) {
        this.goodsAppraiseList = goodsAppraiseList;
    }

    public static class DeliveryAppraiseBean {
        /**
         * deliveryAppraiseId : 399
         * appraiseLevel : 5
         * appraiseContent : 非常好
         * appraiseTime : 1515138568000
         * shopId : 61
         * userId : 4422
         * orderId : 4989
         * tagDeliveryAppraise :
         */

        private int deliveryAppraiseId;
        private int appraiseLevel;
        private String appraiseContent;
        private long appraiseTime;
        private int shopId;
        private int userId;
        private int orderId;
        private String tagDeliveryAppraise;

        public int getDeliveryAppraiseId() {
            return deliveryAppraiseId;
        }

        public void setDeliveryAppraiseId(int deliveryAppraiseId) {
            this.deliveryAppraiseId = deliveryAppraiseId;
        }

        public int getAppraiseLevel() {
            return appraiseLevel;
        }

        public void setAppraiseLevel(int appraiseLevel) {
            this.appraiseLevel = appraiseLevel;
        }

        public String getAppraiseContent() {
            return appraiseContent;
        }

        public void setAppraiseContent(String appraiseContent) {
            this.appraiseContent = appraiseContent;
        }

        public long getAppraiseTime() {
            return appraiseTime;
        }

        public void setAppraiseTime(long appraiseTime) {
            this.appraiseTime = appraiseTime;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getTagDeliveryAppraise() {
            return tagDeliveryAppraise;
        }

        public void setTagDeliveryAppraise(String tagDeliveryAppraise) {
            this.tagDeliveryAppraise = tagDeliveryAppraise;
        }
    }

    public static class CommentListBean {
        /**
         * replyId : 128
         * shopAppraiseId : 1570
         * commentContent : 啊
         * commentTime : 1515667662000
         */

        private int replyId;
        private int shopAppraiseId;
        private String commentContent;
        private long commentTime;

        public int getReplyId() {
            return replyId;
        }

        public void setReplyId(int replyId) {
            this.replyId = replyId;
        }

        public int getShopAppraiseId() {
            return shopAppraiseId;
        }

        public void setShopAppraiseId(int shopAppraiseId) {
            this.shopAppraiseId = shopAppraiseId;
        }

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }
    }

    public static class GoodsAppraiseListBean {
        /**
         * goodsAppraiseId : 2167
         * appraiseLevel : 5
         * appraiseContent : 非常好
         * appraiseTime : 1515138568000
         * goodsId : 9087
         * goodsName : 哈喽果汁(大杯)
         * shopId : 61
         * userId : 4422
         * orderId : 4989
         * tagGoodsAppraise :
         */

        private int goodsAppraiseId;
        private int appraiseLevel;
        private String appraiseContent;
        private long appraiseTime;
        private int goodsId;
        private String goodsName;
        private int shopId;
        private int userId;
        private int orderId;
        private String tagGoodsAppraise;

        public int getGoodsAppraiseId() {
            return goodsAppraiseId;
        }

        public void setGoodsAppraiseId(int goodsAppraiseId) {
            this.goodsAppraiseId = goodsAppraiseId;
        }

        public int getAppraiseLevel() {
            return appraiseLevel;
        }

        public void setAppraiseLevel(int appraiseLevel) {
            this.appraiseLevel = appraiseLevel;
        }

        public String getAppraiseContent() {
            return appraiseContent;
        }

        public void setAppraiseContent(String appraiseContent) {
            this.appraiseContent = appraiseContent;
        }

        public long getAppraiseTime() {
            return appraiseTime;
        }

        public void setAppraiseTime(long appraiseTime) {
            this.appraiseTime = appraiseTime;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getTagGoodsAppraise() {
            return tagGoodsAppraise;
        }

        public void setTagGoodsAppraise(String tagGoodsAppraise) {
            this.tagGoodsAppraise = tagGoodsAppraise;
        }
    }
}

package food.xinyuan.seller.mvp.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.data.bean.response.Order;
import food.xinyuan.seller.app.utils.ConstantUtil;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/8
 */
public class OrderAdapter extends BaseQuickAdapter<Order,BaseViewHolder> {

    public OrderAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Order item) {
        helper.setText(R.id.tv_order_short_num,item.getShortOrderNum())
                .setText(R.id.tv_order_num,item.getOrderNum())
                .setText(R.id.tv_contact_name,item.getOrderContact().getContactName())
                .setText(R.id.tv_phone,item.getOrderContact().getContactPhone())
                .setText(R.id.tv_address,item.getOrderContact().getAddress())
                .setText(R.id.tv_time,item.getPayTimeStr())
                .setText(R.id.tv_price,item.getOrderPrice()+"")
                .setText(R.id.tv_type,item.getOrderTypeStr())
                .setText(R.id.tv_content,item.getOrderContent());

        helper.setText(R.id.tv_status,item.getOrderStatusStr());
        if(item.getOrderStatus().equals(ConstantUtil.ORDER_STATUS_CANCELED)){
            helper.setVisible(R.id.tv_cancel_type,true);
            helper.setText(R.id.tv_cancel_type,item.getOrderCancel().getCancelTypeStr());
        }else {
            helper.setVisible(R.id.tv_cancel_type,false);
        }



    }
}

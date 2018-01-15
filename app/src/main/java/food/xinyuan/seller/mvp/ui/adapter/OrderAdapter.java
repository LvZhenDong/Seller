package food.xinyuan.seller.mvp.ui.adapter;

import android.text.TextUtils;

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
public class OrderAdapter extends BaseQuickAdapter<Order, BaseViewHolder> {

    public OrderAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Order item) {
        helper.setText(R.id.tv_order_short_num, item.getShortOrderNum())
                .setText(R.id.tv_order_num, item.getOrderNum())
                .setText(R.id.tv_contact_name, item.getOrderContact().getContactNameStr())
                .setText(R.id.tv_phone, item.getOrderContact().getContactPhone())
                .setText(R.id.tv_address, item.getOrderContact().getAddress())
                .setText(R.id.tv_time, item.getAddTimeStr())
                .setText(R.id.tv_price, "¥ " + item.getOrderPrice())
                .setText(R.id.tv_type, item.getOrderTypeStr())
                .setText(R.id.tv_content, item.getOrderContent())
                .setText(R.id.tv_status, item.getOrderStatusStr())
                .setText(R.id.tv_rider, item.getOrderTakeout().getCarrierDriverName() + item.getOrderTakeout().getCarrierDriverphone())
                .setText(R.id.tv_cancel,TextUtils.equals(item.getOrderStatus(),ConstantUtil.ORDER_STATUS_NEW)?"拒绝接单":"取消订单")
                .setText(R.id.tv_cancel_type, item.getOrderCancel().getCancelTypeStr());

        if (TextUtils.equals(item.getOrderStatus(), ConstantUtil.ORDER_STATUS_CANCELED)) {   //已取消的显示取消原因
            helper.setVisible(R.id.tv_cancel_type, true);
            helper.setTextColor(R.id.tv_status, mContext.getResources().getColor(R.color.tv_red));
        } else {
            helper.setVisible(R.id.tv_cancel_type, false);
            helper.setTextColor(R.id.tv_status, mContext.getResources().getColor(R.color.colorPrimary));
        }

        helper.setVisible(R.id.tv_cancel, (TextUtils.equals(item.getOrderStatus(), ConstantUtil.ORDER_STATUS_RECEIPT)||
                TextUtils.equals(item.getOrderStatus(), ConstantUtil.ORDER_STATUS_NEW)) ? true : false)//已接单，新订单显示取消订单按钮
                .setVisible(R.id.tv_receipt,TextUtils.equals(item.getOrderStatus(),ConstantUtil.ORDER_STATUS_NEW))  //新订单显示接单
                .setVisible(R.id.rl_rider, TextUtils.equals(item.getOrderStatus(), ConstantUtil.ORDER_STATUS_SHIPPING) ||
                        TextUtils.equals(item.getOrderStatus(), ConstantUtil.ORDER_STATUS_FINISHED) ? true : false);//配送中，已完成显示骑手信息

        helper.addOnClickListener(R.id.iv_contact_phone)
                .addOnClickListener(R.id.tv_rider)
                .addOnClickListener(R.id.tv_cancel)
                .addOnClickListener(R.id.tv_receipt)
                .addOnClickListener(R.id.tv_print);


    }
}

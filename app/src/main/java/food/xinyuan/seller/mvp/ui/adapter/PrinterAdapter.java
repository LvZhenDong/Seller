package food.xinyuan.seller.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import food.xinyuan.seller.R;
import food.xinyuan.seller.app.data.bean.response.Printer;
import food.xinyuan.seller.app.utils.DataUtils;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2017/12/28
 */
public class PrinterAdapter extends BaseQuickAdapter<Printer,BaseViewHolder> {

    public PrinterAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Printer item) {
        helper.setText(R.id.tv_device_name,item.getDeviceName());
//        helper.setText(R.id.tv_device_name_hint,item.getDeviceName());

        helper.setText(R.id.tv_device_status,
                item.getPrinterPageType()+"/"+item.getCopies()+"份/"
                        +(TextUtils.equals("ONLINE",item.getPrinterStatus())?"在线":"离线"));
    }
}

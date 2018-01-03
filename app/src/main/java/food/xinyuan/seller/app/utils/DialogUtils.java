package food.xinyuan.seller.app.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import food.xinyuan.seller.R;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2018/1/3
 */
public class DialogUtils {

    public interface OnBtnClickCallback{
        void onPositive();

//        void onNegative();
    }

    public static MaterialDialog commonChooseDialog(Context context, String content, OnBtnClickCallback callback){
        return new MaterialDialog.Builder(context)
                .title("提示")
                .content(content)
                .positiveText("确定")
                .negativeText("取消")
                .positiveColor(context.getResources().getColor(R.color.colorPrimary))
                .negativeColor(context.getResources().getColor(R.color.tv_red))
                .onAny((dialog, which) -> {
                    if (which == DialogAction.POSITIVE) {
                        callback.onPositive();
                    } else if (which == DialogAction.NEGATIVE) {

                    }

                }).build();
    }
}

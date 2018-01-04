package food.xinyuan.seller.app.utils;

import android.content.Context;
import android.support.annotation.ArrayRes;

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

    /**
     * 确认dialog
     *
     * @param context
     * @param content
     * @param callback
     * @return
     */
    public static MaterialDialog commonChooseDialog(Context context, String content,
                                                    MaterialDialog.SingleButtonCallback callback) {
        return new MaterialDialog.Builder(context)
                .title("提示")
                .content(content)
                .positiveText("确定")
                .negativeText("取消")
                .positiveColor(context.getResources().getColor(R.color.colorPrimary))
                .negativeColor(context.getResources().getColor(R.color.tv_red))
                .onPositive(callback).build();
    }

    /**
     * 输入dialog
     *
     * @param context
     * @param title
     * @param hint
     * @param inputType
     * @param callback
     * @return
     */
    public static MaterialDialog inputDialog(Context context, String title, String hint, int
            inputType, MaterialDialog.InputCallback callback) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .inputType(inputType)
                .positiveText("确定")
                .negativeText("取消")
                .negativeColor(context.getResources().getColor(R.color.tv_red))
                .positiveColor(context.getResources().getColor(R.color.colorPrimary))
                .input(hint, "", callback).build();
    }

    /**
     * 单选dialog
     *
     * @param context
     * @param arrayRes
     * @param callback
     * @return
     */
    public static MaterialDialog singleChoiceDialog(Context context, @ArrayRes int arrayRes, int
            selected, MaterialDialog.ListCallbackSingleChoice callback) {
        return new MaterialDialog.Builder(context)
                .items(arrayRes)
                .itemsCallbackSingleChoice(selected, callback).build();
    }
}

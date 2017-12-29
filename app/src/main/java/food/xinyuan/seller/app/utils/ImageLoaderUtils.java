package food.xinyuan.seller.app.utils;

import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;

import food.xinyuan.seller.R;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author lzd
 * @CreateDate 2017/12/29
 */
public class ImageLoaderUtils {

    /**
     * 加载图片
     *
     * @param appComponent
     * @param url
     * @param imageView
     */
    public static void loadImg(AppComponent appComponent, String url, ImageView imageView) {
        L.i(ConstantUtil.IMAGE + url);
        appComponent
                .imageLoader()
                .loadImage(appComponent.application(),
                        ImageConfigImpl
                                .builder()
                                .url(ConstantUtil.IMAGE + url)
                                .placeholder(R.drawable.img_default)
                                .errorPic(R.drawable.img_default)
                                .imageView(imageView)
                                .build());
    }

    /**
     * 加载圆形图片
     * @param appComponent
     * @param url
     * @param imageView
     */
    public static void loadCirImg(AppComponent appComponent, String url, ImageView imageView) {
        L.i(ConstantUtil.IMAGE + url);
        appComponent
                .imageLoader()
                .loadImage(appComponent.application(),
                        ImageConfigImpl
                                .builder()
                                .url(ConstantUtil.IMAGE + url)
                                .transformation(new CircleCrop())
                                .placeholder(R.drawable.img_default)
                                .errorPic(R.drawable.img_default)
                                .imageView(imageView)
                                .build());
    }
}

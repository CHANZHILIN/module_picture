package chen.module_picture.banner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import chen.module_picture.R;
import chen.module_picture.banner.listener.OnLoadImageViewListener;


public abstract class OnDefaultImageViewLoader implements OnLoadImageViewListener {

    @Override
    public View createImageView(Context context, boolean isScaleAnimation) {
        View view;
        if (!isScaleAnimation) {
            view = LayoutInflater.from(context).inflate(R.layout.item_banner, null, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_animation_banner, null, false);

        }
        return view;
    }
}

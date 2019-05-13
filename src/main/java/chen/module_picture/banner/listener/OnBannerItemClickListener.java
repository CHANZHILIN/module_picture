package chen.module_picture.banner.listener;


import java.util.ArrayList;

import chen.module_picture.banner.bean.BannerInfo;


public interface OnBannerItemClickListener {
    /**
     * banner click
     *
     * @param index  subscript
     * @param banner bean
     */
    void onBannerClick(int index, ArrayList<BannerInfo> banner);
}

package chen.module_picture;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import chen.module_picture.banner.IndicatorLocation;
import chen.module_picture.banner.LoopLayout;
import chen.module_picture.banner.LoopStyle;
import chen.module_picture.banner.OnDefaultImageViewLoader;
import chen.module_picture.banner.bean.BannerInfo;
import chen.module_picture.banner.listener.OnBannerItemClickListener;
import chen.module_picture.banner.view.BannerBgContainer;
import chen.module_picture.behavior.MainHeaderBehavior;


public class PictureFragment extends Fragment implements MainHeaderBehavior.OnHeaderStateListener, OnBannerItemClickListener {
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;
    BannerBgContainer bannerBgContainer;
    LoopLayout loopLayout;

    private ViewPager mViewPager;

    private MainHeaderBehavior mHeaderBehavior;

//    private RecyclerView mRecyclerView;

//    private TextView mTextView;


    public static PictureFragment newInstance(String param1) {
        PictureFragment fragment = new PictureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_picture, container, false);
        loopLayout = view.findViewById(R.id.loop_layout);
        bannerBgContainer = view.findViewById(R.id.banner_bg_container);
        initData();
//        mRecyclerView = view.findViewById(R.id.my_list);
        //初始化数据
//        List<String> datas = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            datas.add("item " + i);
//        }
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mRecyclerView.setAdapter(new GeneralAdapter(getContext(), datas));
//        mTextView = view.findViewById(R.id.textView);
//        mTextView.setText(mParam1 + "_fragment");
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();

        fragments.add(TypeFragment.newInstance());
        fragments.add(TypeFragment.newInstance());
        fragments.add(TypeFragment.newInstance());

        titles.add("静态图片");
        titles.add("GIF动图");
        titles.add("全景图");

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        TabLayout tableLayout = (TabLayout) view.findViewById(R.id.tablayout);

        TypePageAdapter mTypeAdapter = new TypePageAdapter(getFragmentManager());
        mTypeAdapter.setData(fragments, titles);
        mViewPager.setAdapter(mTypeAdapter);
        mViewPager.setOffscreenPageLimit(titles.size() - 1);

        tableLayout.setupWithViewPager(mViewPager);
        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    private void initData() {
        loopLayout.setLoop_ms(3000);//轮播的速度(毫秒)
        loopLayout.setLoop_duration(800);//滑动的速率(毫秒)
        loopLayout.setScaleAnimation(true);// 设置是否需要动画
        loopLayout.setLoop_style(LoopStyle.Empty);//轮播的样式-默认empty
        loopLayout.setIndicatorLocation(IndicatorLocation.Center);//指示器位置-中Center
        loopLayout.initializeData(getContext());
        // 准备数据
        ArrayList<BannerInfo> bannerInfos = new ArrayList<>();
        List<Object> bgList = new ArrayList<>();
        bannerInfos.add(new BannerInfo("http://img.cndesign.com/Works/20190219/14abdb3bbbfd4c34b8d64893ff31b144.jpeg", "one"));
        bannerInfos.add(new BannerInfo("http://img.cndesign.com/Works/20190219/0a944c4896b24d5cb210cd6d136b8c03.jpeg", "two"));
        bannerInfos.add(new BannerInfo("http://img.cndesign.com/Works/20190219/f9861d723d894346a20d2502326b9d75.jpeg", "three"));
        bgList.add("http://img.cndesign.com/Works/20190219/14abdb3bbbfd4c34b8d64893ff31b144.jpeg");
        bgList.add("http://img.cndesign.com/Works/20190219/0a944c4896b24d5cb210cd6d136b8c03.jpeg");
        bgList.add("http://img.cndesign.com/Works/20190219/f9861d723d894346a20d2502326b9d75.jpeg");

        // 设置监听
        loopLayout.setOnLoadImageViewListener(new OnDefaultImageViewLoader() {
            @Override
            public void onLoadImageView(ImageView view, Object object) {
                Glide.with(view.getContext())
                        .load(object)
                        .into(view);
//                GlideUtil.getInstance().loadBigImage(view.getContext(), (String) object,view);
            }
        });
        loopLayout.setOnBannerItemClickListener(this);


        loopLayout.setLoopData(bannerInfos);
        bannerBgContainer.setBannerBackBg(getContext(), bgList);
        loopLayout.setBannerBgContainer(bannerBgContainer);
        loopLayout.startLoop();

    }

    @Override
    public void onHeaderClosed() {

    }

    @Override
    public void onHeaderOpened() {

    }

    @Override
    public void onBannerClick(int index, ArrayList<BannerInfo> banner) {

    }
}

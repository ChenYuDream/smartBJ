package com.jypj.chenyu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jypj.chenyu.smartbj.R;
import com.jypj.chenyu.utils.Constants;
import com.jypj.chenyu.utils.DisplayUtils;
import com.jypj.chenyu.utils.SpTools;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {

    private LinearLayout ll_points;
    private ViewPager vp_pages;
    private List<ImageView> iv_guides;
    private View v_guide_redpoint;
    private MyAdapter adapter;
    private Button btn_start;
    private int distence;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initAdapter();
        initEvent();
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        /**
         * 设置vp的监听事件
         */
        vp_pages.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * 当页面正在滑动的时候调用此方法
             * @param position 第几个界面
             * @param positionOffset 页面滑动的百分比 0-1
             * @param positionOffsetPixels 页面滑动的像素
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                float removeDistence = distence * (positionOffset + position);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) v_guide_redpoint.getLayoutParams();
                params.leftMargin = Math.round(removeDistence);//将float四舍五入成int类型
                v_guide_redpoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == iv_guides.size() - 1) {
                    btn_start.setVisibility(View.VISIBLE);
                } else {
                    btn_start.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpTools.setSpBoolean(getApplicationContext(), Constants.SP_ISSETUP,true);
                Intent it = new Intent(GuideActivity.this, HomeActivity.class);
                startActivity(it);
                finish();
            }
        });
        //view绘制事件的观察者
        v_guide_redpoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            //当view被全部绘制完全是调用此方法
            @Override
            public void onGlobalLayout() {
                //判断两个点之间的间距
                distence = ll_points.getChildAt(1).getLeft() - ll_points.getChildAt(0).getLeft();
                v_guide_redpoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }


    private void initView() {
        setContentView(R.layout.activity_guide);
        //viewpager组件
        vp_pages = (ViewPager) findViewById(R.id.vp_guide_pages);
        //动态加点的容器
        ll_points = (LinearLayout) findViewById(R.id.ll_points);
        //滚动的红点
        v_guide_redpoint = findViewById(R.id.v_guide_redpoint);

        btn_start = (Button) findViewById(R.id.btn_start);

    }

    /**
     * 初始化数据
     */
    private void initData() {
        iv_guides = new ArrayList<>();
        int[] guides = {R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
        int i = 0;
        for (int guide : guides) {
            ImageView iv = new ImageView(getApplicationContext());
            iv.setBackgroundResource(guide);
            iv_guides.add(iv);
            //给view设置宽高
            float dp = 10;
            int px = DisplayUtils.dip2px(getApplicationContext(), dp);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(px, px);
            if (i != 0) {
                params.leftMargin = px;
            }
            View view = new View(this);
            view.setBackgroundResource(R.drawable.gray_point);
            view.setLayoutParams(params);
            ll_points.addView(view);
            i++;
        }
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        adapter = new MyAdapter();
        vp_pages.setAdapter(adapter);
    }

    /**
     * vp的适配器
     */

    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return iv_guides.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;//过滤和缓存的作用
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = iv_guides.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}

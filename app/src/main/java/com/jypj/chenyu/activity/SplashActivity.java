package com.jypj.chenyu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.jypj.chenyu.smartbj.R;
import com.jypj.chenyu.utils.Constants;
import com.jypj.chenyu.utils.SpTools;

public class SplashActivity extends Activity {


    private ImageView iv_splash;
    private AnimationSet as;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化布局
        initView();
        //开始播放动画
        startAnimation();
        //初始化事件
        initEvent();

    }

    private void initView() {
        setContentView(R.layout.activity_splash);
        iv_splash = (ImageView) findViewById(R.id.iv_splash);


    }

    private void initEvent() {
        //设置动画监听器
        as.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //判断当动画完成时时进入主界面还是导航页为true 已经设置过 false没有设置
                if (SpTools.getSpBoolean(getApplicationContext(), Constants.SP_ISSETUP, false)) {
                    Intent it = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    Intent it = new Intent(SplashActivity.this, GuideActivity.class);
                    startActivity(it);
                    finish();
                }


            }

            @Override
            public void onAnimationRepeat(Animation animation) {


            }
        });


    }

    /**
     * 开始播放动画 旋转,缩放，渐变
     */
    private void startAnimation() {

        //false代表每个动画都采用自己的动画插入器

        as = new AnimationSet(false);
        //旋转动画
        RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //设置动画播放时间
        ra.setDuration(2000);
        //动画播放完后停留在当前状态
        ra.setFillAfter(true);
        //渐变动画
        AlphaAnimation aa = new AlphaAnimation(0, 1.0f);
        aa.setFillAfter(true);
        aa.setDuration(2000);
        //缩放动画
        ScaleAnimation sa = new ScaleAnimation(0, 1.0f, 0, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //设置动画播放的时间
        sa.setDuration(2000);
        //动画播放完后停留在当前状态
        sa.setFillAfter(true);

        as.addAnimation(ra);
        as.addAnimation(aa);
        as.addAnimation(sa);

        iv_splash.startAnimation(as);


    }
}

package com.example.mydome;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhaoYaFei on 2018/11/20.
 * Tablayou 指示器与字体长度一直的
 */

public class MyTabLayout extends AppCompatActivity {
    private TabLayout enhance_tab_view;
    private ViewPager wiewpage;
    private List<Fragment> list=new ArrayList<>();
    private String title[]={"哈哈","哈哈哈哈哈哈啊哈哈哈"};
    private MyFragmentAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablayout_activity);
        wiewpage = (ViewPager)findViewById(R.id.wiewpage);
        enhance_tab_view = (TabLayout) findViewById(R.id.enhance_tab_view);
        enhance_tab_view.addTab(enhance_tab_view.newTab().setText("1"));
        enhance_tab_view.addTab(enhance_tab_view.newTab().setText("哈哈哈哈哈哈哈哈哈啊哈哈哈"));
        reflex(enhance_tab_view);
       // initFragment();
    }
    private void initFragment() {
        BlankFragment blankFragment1=new BlankFragment();
        BlankFragment2 blankFragment2=new BlankFragment2();

        list.add(blankFragment1);
        list.add(blankFragment2);

        adapter=new MyFragmentAdapter(getSupportFragmentManager(),title,list);
        wiewpage.setAdapter(adapter);
        enhance_tab_view.setupWithViewPager(wiewpage);
    }
    public static void reflex(final TabLayout tabLayout) {
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    Field mTabStripField = tabLayout.getClass().getDeclaredField("mTabStrip");
                    mTabStripField.setAccessible(true);
                    LinearLayout mTabStrip = (LinearLayout) mTabStripField.get(tabLayout);
                    int dp10 = dip2px1(tabLayout.getContext(), 1);
                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);
                        //拿到tabView的mTextView属性
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);
                        TextView mTextView = (TextView) mTextViewField.get(tabView);
                        tabView.setPadding(0, 0, 0, 0);
                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);
                        tabView.invalidate();
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    public static int dip2px1(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}

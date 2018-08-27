package com.dl.ms.mspro1.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.app.dl.uilibrary.tab.CustomTabView;
import com.app.dl.uilibrary.tab.Tab;
import com.dl.ms.mspro1.R;
import com.dl.ms.mspro1.main.fragment.MyFragment;
import com.dl.ms.mspro1.main.fragment.Tab1Fragment;
import com.dl.ms.mspro1.main.fragment.Tab2Fragment;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements
        CustomTabView.OnTabCheckListener {

    private Tab1Fragment mTab1Fragment;
    private Tab2Fragment mTab2Fragment;
    private MyFragment mTab3Fragment;

    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lc_test);
        initView();
        initFragment();
    }

    private void initView() {
        CustomTabView mCustomTabView = findViewById(R.id.tabView);

        Tab tab1 = new Tab().setText("品牌推荐")
                .setNormalIcon(R.drawable.ic_tab_strip_icon_category)
                .setPressedIcon(R.drawable.ic_tab_strip_icon_category_selected);
        mCustomTabView.addTab(tab1);

        Tab tab2 = new Tab().setText("品牌热点")
                .setNormalIcon(R.drawable.ic_tab_strip_icon_pgc)
                .setPressedIcon(R.drawable.ic_tab_strip_icon_pgc_selected);
        mCustomTabView.addTab(tab2);

        Tab tab3 = new Tab().setText("我的")
                .setNormalIcon(R.drawable.ic_tab_strip_icon_profile)
                .setPressedIcon(R.drawable.ic_tab_strip_icon_profile_selected);
        mCustomTabView.addTab(tab3);

        mCustomTabView.setCurrentItem(0);
        mCustomTabView.setOnTabCheckListener(this);
    }

    private void initFragment() {
        mTab1Fragment = new Tab1Fragment();
        addFragment(mTab1Fragment);
        showFragment(mTab1Fragment);
    }

    private void showFragment(Fragment fragment) {
        for (Fragment frag: mFragments) {
            //隐藏其它fragment
            if (frag != fragment) {
                getSupportFragmentManager().beginTransaction().hide(frag).commit();
            }
        }
        getSupportFragmentManager().beginTransaction().show(fragment).commit();
    }

    private void addFragment(Fragment fragment) {
        //判断该fragment是否添加，没有添加则添加
        if (!fragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.fl_content, fragment).commit();
            addToList(fragment);
        }
    }

    private void addToList(Fragment fragment) {
        if (fragment != null) {
            mFragments.add(fragment);
        }
    }

    @Override
    public void onTabSelected(View v, int position) {
        switch (position) {
            case 0:
                if (mTab1Fragment == null) {
                    mTab1Fragment = new Tab1Fragment();
                }
                addFragment(mTab1Fragment);
                showFragment(mTab1Fragment);
                break;
            case 1:
                if (mTab2Fragment == null) {
                    mTab2Fragment = new Tab2Fragment();
                }
                addFragment(mTab2Fragment);
                showFragment(mTab2Fragment);
                break;
            case 2:
                if (mTab3Fragment == null) {
                    mTab3Fragment = new MyFragment();
                }
                addFragment(mTab3Fragment);
                showFragment(mTab3Fragment);
                break;
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (mTab1Fragment == null && fragment instanceof Tab1Fragment) {
            mTab1Fragment = (Tab1Fragment) fragment;
            mFragments.add(mTab1Fragment);
        } else if (mTab2Fragment == null && fragment instanceof Tab2Fragment) {
            mTab2Fragment = (Tab2Fragment) fragment;
            mFragments.add(mTab2Fragment);
        } else if (mTab3Fragment == null && fragment instanceof MyFragment) {
            mTab3Fragment = (MyFragment) fragment;
            mFragments.add(mTab3Fragment);
        }
    }

    public static final String TAG = "dlTest";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.e(TAG, "onSaveInstanceState: ");
        super.onSaveInstanceState(outState);
    }

}
